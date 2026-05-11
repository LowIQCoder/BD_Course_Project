#!/usr/bin/env bash
set -e

cd "$(dirname "$0")/.."
spark-submit --master yarn scripts/stage3_01_model.py --sample_size 1000000
