@echo off
REM SEIR Model Group Splitter Script
REM Usage: run_splitter.bat [model-file.seirmodel]

echo === SEIR Model Group Splitter ===
echo.

REM Check if model file is provided
if "%~1"=="" (
    echo Usage: run_splitter.bat ^<model-file.seirmodel^>
    echo Example: run_splitter.bat covid.seirmodel
    echo.
    echo Available model files:
    dir /b *.seirmodel 2>nul || echo No .seirmodel files found in current directory
    exit /b 1
)

set MODEL_FILE=%~1

REM Check if model file exists
if not exist "%MODEL_FILE%" (
    echo ❌ Error: Model file '%MODEL_FILE%' not found!
    echo.
    echo Available model files:
    dir /b *.seirmodel 2>nul || echo No .seirmodel files found in current directory
    exit /b 1
)

echo 📁 Input model: %MODEL_FILE%
echo 🔧 Compiling Java classes...

REM Compile the Java file
cd src
javac -cp ".;..\..\..\**\*.jar;%CLASSPATH%" seir\utilities\DynamicDiagramGenerator.java

if %ERRORLEVEL% == 0 (
    echo ✓ Compilation successful
    echo.
    echo 🚀 Running dynamic model splitter...
    echo ----------------------------------------
    
    REM Run the program
    java -cp ".;..\..\..\**\*.jar;%CLASSPATH%" seir.utilities.DynamicDiagramGenerator "..\%MODEL_FILE%"
    
    echo ----------------------------------------
    echo ✓ Done! Check for generated *_*.seirmodel files
    
) else (
    echo ❌ Compilation failed. Make sure you have the required libraries in classpath.
    echo Try running from Eclipse instead.
)

pause