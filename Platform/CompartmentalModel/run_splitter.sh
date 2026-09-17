#!/bin/bash

# SEIR Model Group Splitter Script
# Usage: ./run_splitter.sh [model-file.seirmodel]

echo "=== SEIR Model Group Splitter ==="
echo ""

# Check if model file is provided
if [ $# -eq 0 ]; then
    echo "Usage: ./run_splitter.sh <model-file.seirmodel>"
    echo "Example: ./run_splitter.sh covid.seirmodel"
    echo ""
    echo "Available model files:"
    ls -1 *.seirmodel 2>/dev/null || echo "No .seirmodel files found in current directory"
    exit 1
fi

MODEL_FILE="$1"

# Check if model file exists
if [ ! -f "$MODEL_FILE" ]; then
    echo "❌ Error: Model file '$MODEL_FILE' not found!"
    echo ""
    echo "Available model files:"
    ls -1 *.seirmodel 2>/dev/null || echo "No .seirmodel files found in current directory"
    exit 1
fi

echo "📁 Input model: $MODEL_FILE"
echo "🔧 Compiling Java classes..."

# Compile the Java file
cd src
javac -cp ".:../../../**/*.jar:$CLASSPATH" seir/utilities/DynamicDiagramGenerator.java

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful"
    echo ""
    echo "🚀 Running dynamic model splitter..."
    echo "----------------------------------------"
    
    # Run the program
    java -cp ".:../../../**/*.jar:$CLASSPATH" seir.utilities.DynamicDiagramGenerator "../$MODEL_FILE"
    
    echo "----------------------------------------"
    echo "✓ Done! Check for generated *_*.seirmodel files"
    
else
    echo "❌ Compilation failed. Make sure you have the required libraries in classpath."
    echo "Try running from Eclipse instead."
fi