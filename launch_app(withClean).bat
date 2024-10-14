@echo off
REM Store the current directory
set "ORIGINAL_DIR=%CD%"

REM Step 1: Run 'mvn clean package' and ensure terminal stays open
echo Running 'mvn clean package'...
call mvn clean package
if %errorlevel% neq 0 (
    echo 'mvn clean package' failed. Exiting script.
    pause
    exit /b
)

REM Step 2: Open Windows Terminal with split panes for 'mvn javafx:run' and 'mvn spring-boot:run'
echo Starting Windows Terminal with split panes...
start "" wt.exe -d "%ORIGINAL_DIR%" cmd /k "mvn javafx:run" ; split-pane -d "%ORIGINAL_DIR%" cmd /k "mvn spring-boot:run"

REM Step 3: Close the original terminal
echo All commands executed. Closing this terminal in 5 seconds...
timeout /t 5 >nul
exit