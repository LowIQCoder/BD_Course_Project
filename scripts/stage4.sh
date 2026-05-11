#!/bin/bash

echo "All tables for report were build on previous stages!"

echo "Execution times:"
cat output/execution_times.txt
echo ""

echo "Dataset information"
cat output/basic_overview.csv
echo ""

echo "Models metrics"
echo output/evaluation.csv
echo ""

echo "Best performing model metrics"
cat output/best_model_evaluation.csv
echo ""
