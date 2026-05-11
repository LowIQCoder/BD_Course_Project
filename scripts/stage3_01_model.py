"""
Prepares data, performs Cross Validation on 3 different models, and save results
"""
import argparse
import math
import os
import shutil
import subprocess
import time

from pyspark.ml import Pipeline
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.feature import Imputer, OneHotEncoder
from pyspark.ml.feature import StringIndexer, VectorAssembler
from pyspark.ml.regression import DecisionTreeRegressor
from pyspark.ml.regression import LinearRegression, RandomForestRegressor
from pyspark.ml.tuning import CrossValidator
from pyspark.ml.tuning import ParamGridBuilder
from pyspark.sql import SparkSession
from pyspark.sql import functions as F


parser = argparse.ArgumentParser()
parser.add_argument("--sample_size", type=int, default=1_000_000)
args = parser.parse_args()

if args.sample_size <= 0:
    raise ValueError("sample_size must be positive")

print("Starting Stage III model training")
print("Sample size:", args.sample_size)

for path in ["data", "models", "output"]:
    os.makedirs(path, exist_ok=True)

spark = (
    SparkSession.builder.appName("27 - spark ML")
    .master("yarn")
    .config("hive.metastore.uris", "thrift://hadoop-02.uni.innopolis.ru:9883")
    .config("spark.sql.warehouse.dir", "project/hive/warehouse")
    .config("spark.sql.avro.compression.codec", "snappy")
    .enableHiveSupport()
    .getOrCreate()
)
spark.sparkContext.setLogLevel("ERROR")

print(spark)

time.sleep(5)

spark.sql("USE team27_projectdb")

CATEGORICAL_COLS = [
    "payment_type",
    "pickup_community_area",
    "dropoff_community_area",
]
NUMERIC_COLS = ["trip_seconds", "trip_miles", "trip_year"]
BOOLEAN_COLS = ["is_valid_end_timestamp", "is_valid_duration", "is_valid_amounts"]
CYCLE_COLS = [
    "trip_month_sin",
    "trip_month_cos",
    "trip_day_sin",
    "trip_day_cos",
    "trip_hour_sin",
    "trip_hour_cos",
    "trip_weekday_sin",
    "trip_weekday_cos",
]

print("Reading source table")
data = spark.table("team27_projectdb.fact_trips_raw")
data = data.withColumn("label", F.col("fare").cast("double"))

for col_name in NUMERIC_COLS:
    data = data.withColumn(col_name, F.col(col_name).cast("double"))

for col_name in BOOLEAN_COLS:
    data = data.withColumn(
        col_name,
        F.when(F.col(col_name).cast("boolean"), 1.0).otherwise(0.0),
    )

for col_name in CATEGORICAL_COLS:
    data = data.withColumn(
        col_name,
        F.coalesce(F.col(col_name).cast("string"), F.lit("missing")),
    )

month_value = 2.0 * math.pi * F.col("trip_month").cast("double") / F.lit(12.0)
day_value = 2.0 * math.pi * F.col("trip_day").cast("double") / F.lit(31.0)
hour_value = 2.0 * math.pi * F.col("trip_hour").cast("double") / F.lit(24.0)
weekday_value = 2.0 * math.pi * F.col("trip_weekday").cast("double") / F.lit(7.0)

data = data.withColumn("trip_month_sin", F.sin(month_value))
data = data.withColumn("trip_month_cos", F.cos(month_value))
data = data.withColumn("trip_day_sin", F.sin(day_value))
data = data.withColumn("trip_day_cos", F.cos(day_value))
data = data.withColumn("trip_hour_sin", F.sin(hour_value))
data = data.withColumn("trip_hour_cos", F.cos(hour_value))
data = data.withColumn("trip_weekday_sin", F.sin(weekday_value))
data = data.withColumn("trip_weekday_cos", F.cos(weekday_value))

data = data.select(["label"] + NUMERIC_COLS + BOOLEAN_COLS + CYCLE_COLS + CATEGORICAL_COLS)
data = data.where((F.col("label").isNotNull()) & (F.col("label") > 0.0))
data = data.orderBy(F.rand(42)).limit(args.sample_size).cache()
print("Sampled records:", data.count())

print("Splitting data")
train_data, test_data = data.randomSplit([0.8, 0.2], seed=42)
print("Training set size:", train_data.count())
print("Test set size:", test_data.count())

indexed_cols = [col_name + "_idx" for col_name in CATEGORICAL_COLS]
encoded_cols = [col_name + "_vec" for col_name in CATEGORICAL_COLS]
numeric_inputs = NUMERIC_COLS + BOOLEAN_COLS + CYCLE_COLS
imputed_cols = [col_name + "_imp" for col_name in numeric_inputs]

indexers = [
    StringIndexer(inputCol=col_name, outputCol=idx, handleInvalid="keep")
    for col_name, idx in zip(CATEGORICAL_COLS, indexed_cols)
]
encoder = OneHotEncoder(inputCols=indexed_cols, outputCols=encoded_cols)
imputer = Imputer(inputCols=numeric_inputs, outputCols=imputed_cols).setStrategy("median")
assembler = VectorAssembler(
    inputCols=imputed_cols + encoded_cols,
    outputCol="features",
    handleInvalid="keep",
)

print("Fitting preprocessing pipeline")
feature_pipeline = Pipeline(stages=indexers + [encoder, imputer, assembler])
feature_model = feature_pipeline.fit(train_data)
train_ready = feature_model.transform(train_data).select("features", "label")
test_ready = feature_model.transform(test_data).select("features", "label")

evaluator = RegressionEvaluator(labelCol="label", predictionCol="prediction", metricName="rmse")
rmse_evaluator = RegressionEvaluator(
    labelCol="label",
    predictionCol="prediction",
    metricName="rmse",
)
mae_evaluator = RegressionEvaluator(labelCol="label", predictionCol="prediction", metricName="mae")
r2_evaluator = RegressionEvaluator(labelCol="label", predictionCol="prediction", metricName="r2")

lr = LinearRegression(labelCol="label", featuresCol="features", regParam=1.0, elasticNetParam=0.5)
lr_grid = (
    ParamGridBuilder()
    .addGrid(lr.regParam, [0.1, 1.0])
    .addGrid(lr.elasticNetParam, [0.5, 1.0])
    .build()
)

rf = RandomForestRegressor(labelCol="label", featuresCol="features", seed=42)
rf_grid = (
    ParamGridBuilder()
    .addGrid(rf.numTrees, [20, 50])
    .addGrid(rf.maxDepth, [5, 10])
    .build()
)

dt = DecisionTreeRegressor(labelCol="label", featuresCol="features", seed=42)
dt_grid = (
    ParamGridBuilder()
    .addGrid(dt.maxDepth, [5, 10])
    .addGrid(dt.minInstancesPerNode, [1, 5])
    .build()
)

print("Training default Linear Regression model")
lr_default_model = lr.fit(train_ready)
lr_default_predictions = lr_default_model.transform(test_ready)
lr_default_rmse = rmse_evaluator.evaluate(lr_default_predictions)
lr_default_mae = mae_evaluator.evaluate(lr_default_predictions)
lr_default_r2 = r2_evaluator.evaluate(lr_default_predictions)
print(
    f"LinearRegression default RMSE={lr_default_rmse:.4f}, "
    f"MAE={lr_default_mae:.4f}, R2={lr_default_r2:.4f}"
)

print("Starting CV for Linear Regression model")
lr_cv = CrossValidator(
    estimator=lr,
    estimatorParamMaps=lr_grid,
    evaluator=evaluator,
    numFolds=3,
    parallelism=2,
    seed=42,
)
lr_cv_model = lr_cv.fit(train_ready)
model1 = lr_cv_model.bestModel
print("Evaluating best Linear Regression model")
predictions1 = model1.transform(test_ready)
metrics1 = {
    "rmse": rmse_evaluator.evaluate(predictions1),
    "mae": mae_evaluator.evaluate(predictions1),
    "r2": r2_evaluator.evaluate(predictions1),
}
print("LinearRegression tuned metrics:", metrics1)

print("Training default Random Forest model")
rf_default_model = rf.fit(train_ready)
rf_default_predictions = rf_default_model.transform(test_ready)
rf_default_rmse = rmse_evaluator.evaluate(rf_default_predictions)
rf_default_mae = mae_evaluator.evaluate(rf_default_predictions)
rf_default_r2 = r2_evaluator.evaluate(rf_default_predictions)
print(
    f"RandomForestRegressor default RMSE={rf_default_rmse:.4f}, "
    f"MAE={rf_default_mae:.4f}, R2={rf_default_r2:.4f}"
)

print("Starting CV for Random Forest model")
rf_cv = CrossValidator(
    estimator=rf,
    estimatorParamMaps=rf_grid,
    evaluator=evaluator,
    numFolds=3,
    parallelism=2,
    seed=42,
)
rf_cv_model = rf_cv.fit(train_ready)
model2 = rf_cv_model.bestModel
print("Evaluating best Random Forest model")
predictions2 = model2.transform(test_ready)
metrics2 = {
    "rmse": rmse_evaluator.evaluate(predictions2),
    "mae": mae_evaluator.evaluate(predictions2),
    "r2": r2_evaluator.evaluate(predictions2),
}
print("RandomForestRegressor tuned metrics:", metrics2)

print("Training default Decision Tree model")
dt_default_model = dt.fit(train_ready)
dt_default_predictions = dt_default_model.transform(test_ready)
dt_default_rmse = rmse_evaluator.evaluate(dt_default_predictions)
dt_default_mae = mae_evaluator.evaluate(dt_default_predictions)
dt_default_r2 = r2_evaluator.evaluate(dt_default_predictions)
print(
    f"DecisionTreeRegressor default RMSE={dt_default_rmse:.4f}, "
    f"MAE={dt_default_mae:.4f}, R2={dt_default_r2:.4f}"
)

print("Starting CV for Decision Tree model")
dt_cv = CrossValidator(
    estimator=dt,
    estimatorParamMaps=dt_grid,
    evaluator=evaluator,
    numFolds=3,
    parallelism=2,
    seed=42,
)
dt_cv_model = dt_cv.fit(train_ready)
model3 = dt_cv_model.bestModel
print("Evaluating best Decision Tree model")
predictions3 = model3.transform(test_ready)
metrics3 = {
    "rmse": rmse_evaluator.evaluate(predictions3),
    "mae": mae_evaluator.evaluate(predictions3),
    "r2": r2_evaluator.evaluate(predictions3),
}
print("DecisionTreeRegressor tuned metrics:", metrics3)

models = [
    ("LinearRegression", model1, predictions1, metrics1),
    ("RandomForestRegressor", model2, predictions2, metrics2),
    ("DecisionTreeRegressor", model3, predictions3, metrics3),
]
best_model_name, best_model, best_predictions, best_metrics = max(
    models,
    key=lambda item: item[3]["r2"],
)
print("Best model by R2:", best_model_name)
print("Best model metrics:", best_metrics)

best_param_rows = [
    [best_model_name, param.name, str(value)]
    for param, value in best_model.extractParamMap().items()
]
best_param_rows = sorted(best_param_rows, key=lambda row: row[1])
best_model_parameters = spark.createDataFrame(
    best_param_rows,
    ["model", "parameter", "value"],
)

print("Predicting fare for real world example")
example_data = spark.createDataFrame(
    [[720.0, 3.4, 2026, 5, 11, 2, 1, "Cash", None, None, True, True, True]],
    "trip_seconds double, trip_miles double, trip_year int, trip_month int, "
    "trip_day int, trip_hour int, trip_weekday int, payment_type string, "
    "pickup_community_area string, dropoff_community_area string, "
    "is_valid_end_timestamp boolean, is_valid_duration boolean, "
    "is_valid_amounts boolean",
)

for col_name in NUMERIC_COLS:
    example_data = example_data.withColumn(col_name, F.col(col_name).cast("double"))

for col_name in BOOLEAN_COLS:
    example_data = example_data.withColumn(
        col_name,
        F.when(F.col(col_name).cast("boolean"), 1.0).otherwise(0.0),
    )

for col_name in CATEGORICAL_COLS:
    example_data = example_data.withColumn(
        col_name,
        F.coalesce(F.col(col_name).cast("string"), F.lit("missing")),
    )

example_month_value = 2.0 * math.pi * F.col("trip_month").cast("double") / F.lit(12.0)
example_day_value = 2.0 * math.pi * F.col("trip_day").cast("double") / F.lit(31.0)
example_hour_value = 2.0 * math.pi * F.col("trip_hour").cast("double") / F.lit(24.0)
example_weekday_value = 2.0 * math.pi * F.col("trip_weekday").cast("double") / F.lit(7.0)

example_data = example_data.withColumn("trip_month_sin", F.sin(example_month_value))
example_data = example_data.withColumn("trip_month_cos", F.cos(example_month_value))
example_data = example_data.withColumn("trip_day_sin", F.sin(example_day_value))
example_data = example_data.withColumn("trip_day_cos", F.cos(example_day_value))
example_data = example_data.withColumn("trip_hour_sin", F.sin(example_hour_value))
example_data = example_data.withColumn("trip_hour_cos", F.cos(example_hour_value))
example_data = example_data.withColumn("trip_weekday_sin", F.sin(example_weekday_value))
example_data = example_data.withColumn("trip_weekday_cos", F.cos(example_weekday_value))
example_data = example_data.select(NUMERIC_COLS + BOOLEAN_COLS + CYCLE_COLS + CATEGORICAL_COLS)
example_ready = feature_model.transform(example_data).select("features")
example_prediction = best_model.transform(example_ready).select("prediction").first()[0]
print(f"Real world example predicted fare: {example_prediction:.4f}")

rows = [
    ["LinearRegression", metrics1["rmse"], metrics1["mae"], metrics1["r2"]],
    ["RandomForestRegressor", metrics2["rmse"], metrics2["mae"], metrics2["r2"]],
    ["DecisionTreeRegressor", metrics3["rmse"], metrics3["mae"], metrics3["r2"]],
]
evaluation = spark.createDataFrame(rows, ["model", "RMSE", "MAE", "R2"])
evaluation.show(truncate=False)

evaluation_hive = spark.createDataFrame(rows, ["model", "rmse", "mae", "r2"])
predictions_hive = predictions1.select(
    F.lit("LinearRegression").alias("model"),
    F.col("label").cast("double").alias("label"),
    F.col("prediction").cast("double").alias("prediction"),
).unionByName(
    predictions2.select(
        F.lit("RandomForestRegressor").alias("model"),
        F.col("label").cast("double").alias("label"),
        F.col("prediction").cast("double").alias("prediction"),
    )
).unionByName(
    predictions3.select(
        F.lit("DecisionTreeRegressor").alias("model"),
        F.col("label").cast("double").alias("label"),
        F.col("prediction").cast("double").alias("prediction"),
    )
)

print("Saving Hive result tables")
evaluation_hive.write.mode("overwrite").format("parquet").saveAsTable(
    "team27_projectdb.pda_models_evaluation"
)
predictions_hive.write.mode("overwrite").format("parquet").saveAsTable(
    "team27_projectdb.pda_models_predictions"
)
best_model_parameters.write.mode("overwrite").format("parquet").saveAsTable(
    "team27_projectdb.pda_best_model_parameters"
)

print("Saving train and test datasets")
train_ready.coalesce(1).write.mode("overwrite").format("json").save("project/data/train")
subprocess.run("hdfs dfs -cat project/data/train/*.json > data/train.json", shell=True, check=True)
test_ready.coalesce(1).write.mode("overwrite").format("json").save("project/data/test")
subprocess.run("hdfs dfs -cat project/data/test/*.json > data/test.json", shell=True, check=True)

print("Saving models")
model1.write().overwrite().save("project/models/model1")
shutil.rmtree("models/model1", ignore_errors=True)
subprocess.run("hdfs dfs -get project/models/model1 models/model1", shell=True, check=True)

model2.write().overwrite().save("project/models/model2")
shutil.rmtree("models/model2", ignore_errors=True)
subprocess.run("hdfs dfs -get project/models/model2 models/model2", shell=True, check=True)

model3.write().overwrite().save("project/models/model3")
shutil.rmtree("models/model3", ignore_errors=True)
subprocess.run("hdfs dfs -get project/models/model3 models/model3", shell=True, check=True)

best_model.write().overwrite().save("project/models/best_model")
shutil.rmtree("models/best_model", ignore_errors=True)
subprocess.run("hdfs dfs -get project/models/best_model models/best_model", shell=True, check=True)

print("Saving predictions")
predictions1.select("label", "prediction").coalesce(1).write.mode("overwrite").format(
    "csv"
).option("header", "true").save("project/output/model1_predictions")
subprocess.run(
    "hdfs dfs -cat project/output/model1_predictions/*.csv > output/model1_predictions.csv",
    shell=True,
    check=True,
)

predictions2.select("label", "prediction").coalesce(1).write.mode("overwrite").format(
    "csv"
).option("header", "true").save("project/output/model2_predictions")
subprocess.run(
    "hdfs dfs -cat project/output/model2_predictions/*.csv > output/model2_predictions.csv",
    shell=True,
    check=True,
)

predictions3.select("label", "prediction").coalesce(1).write.mode("overwrite").format(
    "csv"
).option("header", "true").save("project/output/model3_predictions")
subprocess.run(
    "hdfs dfs -cat project/output/model3_predictions/*.csv > output/model3_predictions.csv",
    shell=True,
    check=True,
)

best_predictions.select("label", "prediction").coalesce(1).write.mode("overwrite").format(
    "csv"
).option("header", "true").save("project/output/best_model_predictions")
subprocess.run(
    "hdfs dfs -cat project/output/best_model_predictions/*.csv > output/best_model_predictions.csv",
    shell=True,
    check=True,
)

print("Saving evaluation")
evaluation.coalesce(1).write.mode("overwrite").format("csv").option(
    "header",
    "true",
).save("project/output/evaluation")
subprocess.run(
    "hdfs dfs -cat project/output/evaluation/*.csv > output/evaluation.csv",
    shell=True,
    check=True,
)

best_row = spark.createDataFrame(
    [[best_model_name, best_metrics["rmse"], best_metrics["mae"], best_metrics["r2"]]],
    ["model", "RMSE", "MAE", "R2"],
)
best_row.coalesce(1).write.mode("overwrite").format("csv").option(
    "header",
    "true",
).save("project/output/best_model_evaluation")
subprocess.run(
    "hdfs dfs -cat project/output/best_model_evaluation/*.csv > output/best_model_evaluation.csv",
    shell=True,
    check=True,
)

print("Stage III model training finished")
spark.stop()
