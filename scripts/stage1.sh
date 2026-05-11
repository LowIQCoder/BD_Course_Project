#!/usr/bin/env bash
#!/bin/bash
start=$SECONDS
mkdir -p ~/bigdata-final-project/data/archive/

curl -L -o ~/bigdata-final-project/data/archive/chicago-taxi-rides-2016.zip\
  https://www.kaggle.com/api/v1/datasets/download/chicago/chicago-taxi-rides-2016

set -euo pipefail

python3 scripts/stage1_01_run_cluster.py
bash scripts/stage1_02_sqoop_import.sh

duration=$(( SECONDS - start ))
echo "Stage I finished successfully. Execution time: $duration seconds"

echo "Stage I execution time: $duration seconds" >> output/execution_times.txt
