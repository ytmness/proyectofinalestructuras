@echo off
echo Limpiando archivos compilados anteriores...
if exist bin rmdir /s /q bin
mkdir bin

echo Compilando proyecto...
javac -d bin -encoding UTF-8 src\util\*.java src\sorting\*.java src\concurrent\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilacion exitosa!
    echo.
    echo Ejecuta con: java -cp bin concurrent.ConcurrentSortingTest [segundos]
) else (
    echo.
    echo Error en la compilacion.
)

pause

