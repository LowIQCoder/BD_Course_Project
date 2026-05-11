# Scripts

In this dirrectory you will find all our `.sh` and `.py` scripts. 

## Scripts Present

* stage<some_num>.sh - Script corresponding for running some stage
* Stage I scripts:
    * stage1.sh - Pulls data from kaggle and initiates scripts below
    * stage1_01_run_cluster.py - Created and populates Postgres DB
    * stage1_02_sqoop_import.sh - Sqoop import script
* Stage II scripts:
    * stage2.sh - Setups hive tables and runs stage related queries, used for EDA
* Stage III scripts:
    * stage3,sh - Subbmits spark application into cluster.
    * stage3_01_model.py - Contains data preprocessing pipelines, cross validation and saving results into hdfs and hive tables
* Stage IV scripts:
    * stage4.sh - Redundant, since all hive tables are created in stages II and III
