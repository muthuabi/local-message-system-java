#!/bin/bash

# Create bin directory if it doesn't exist
mkdir -p bin

# Find all Java files and compile them
find src -name "*.java" > sources.txt
javac -d bin @sources.txt
rm sources.txt

echo "Compilation complete. Class files are in bin/"