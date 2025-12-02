@echo off
echo Ejecutando Ordenamiento Concurrente...
echo.

if not exist bin (
    echo Error: El directorio bin\ no existe. Compila primero con compile.bat
    pause
    exit /b 1
)

if "%1"=="" (
    echo Usando tiempo limite por defecto: 10 segundos
    echo Para especificar un tiempo diferente, usa: run.bat [segundos]
    echo.
    java -cp bin concurrent.ConcurrentSortingTest
) else (
    echo Tiempo limite: %1 segundos
    echo.
    java -cp bin concurrent.ConcurrentSortingTest %1
)

pause

