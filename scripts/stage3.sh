#!/usr/bin/env bash
set -e

start=$SECONDS

cd "$(dirname "$0")/.."
spark-submit --master yarn scripts/stage3_01_model.py --sample_size 1000000

duration=$(( SECONDS - start ))
echo "Stage III finished successfully. Execution time: $duration seconds"

echo "Stage III execution time: $duration seconds" >> output/execution_times.txt
