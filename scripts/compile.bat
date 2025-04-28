@echo off

:: Create bin directory if it doesn't exist
if not exist "bin" mkdir "bin"

:: Compile all Java files
dir /s /b src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

echo Compiled successfully. Class files are in bin\
pause