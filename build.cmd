@echo off

echo " - mvn version"
call mvn -version
if %errorlevel% gtr 0 (
    echo "An error occurred to retrieve maven version."
    pause
    exit /b -1
)

echo " - mvn clean"
call mvn clean
if %errorlevel% gtr 0 (
    echo "An error occurred to clean this repo."
    pause
    exit /b -2
)

echo " - build castiel"
call mvn package
if %errorlevel% gtr 0 (
    echo "An error occurred to build castiel."
    pause
    exit /b -3
)