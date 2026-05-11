# Chicago Taxi Price Prediction
In this reposetory you will see all the work we have done for **Big Data Cousrce Project**

As our goal we decided to predict `price` of taxi trip based on some parameters, like pick up location and date. 

## Dashboard
You can check quick summary of that we have done in our [**Superset**](http://hadoop-03.uni.innopolis.ru:8808/superset/dashboard/246/)

## Dataset
For our project we have choosen open `Chicago Taxi Rides 2016` dataset
* Completly open and free to use
* 20 featuresh
* 4000000 records (our split)
* Both datetime and geospatioal features are present

As first optimization we diceded to work with only portion of dataset:
* chicago_taxi_trips_2016_01.csv
* chicago_taxi_trips_2016_04.csv
* chicago_taxi_trips_2016_07.csv
* chicago_taxi_trips_2016_10.csv

## Usage
To run this project you should install all dependencies from `requirements.txt`:
```bash
pip install -r requirements.txt
```

And after run full pipeline script
```bash
sh main.sh
```

This will execute all stages squentially.

## Repository Structure
Our reposetory have strict structure and naming conventions:
```bash
data/                         # Directory with all our data
---- archive/                 # Our raw dataset
---- test.json                # Test split
---- train.json               # Train split
models/                       # Directory containing models chechpoints
---- model1                   # LinearRegression
---- model2                   # RandomForest
---- model2                   # DecisionTree
---- best_model               # Best model of all 3
notebooks/                    # Directory with our experements
---- model_experements.ipynb  # Model training experements
output/                       # Outputs of our scripts
scripts/                      # All scripts of our project
sql/                          # SQL and HQL scripts of our project
main.sh                       # Main script
```


## Authors

* Marsel Berheev (m.berheev@innopolis.university) - ML Specialist
* Vlad Strelkov (vl.strelkov@innopolis.uniceristy) - Tester and Technical Writer
* Makar Egorov (makar.egorov.04@mail.ru) - Data Engineer
* Rail Sharipov () - Data Sciencist
