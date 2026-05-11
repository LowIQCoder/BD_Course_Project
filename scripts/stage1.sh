

#!/usr/bin/env bash
#!/bin/bash
curl -L -o ~/bigdata-final-project/data/archive/chicago-taxi-rides-2016.zip\
  https://www.kaggle.com/api/v1/datasets/download/chicago/chicago-taxi-rides-2016


set -euo pipefail

python3 scripts/stage1_01_run_cluster.py
bash scripts/stage1_02_sqoop_import.sh

echo "Stage I finished successfully."
