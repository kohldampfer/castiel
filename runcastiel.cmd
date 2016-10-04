@echo off

set "CASTIEL_DIR=%~dp0"
set "TARGET_DATA_DIR=%CASTIEL_DIR%\data"
set "JAR_DIR=%CASTIEL_DIR%\target"

rem start castiel
java -jar %JAR_DIR%\castiel-1.0.jar %*