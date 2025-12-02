@echo off
echo Compilando proyecto de Ordenamiento Concurrente...
echo.

if not exist bin mkdir bin

javac -d bin -encoding UTF-8 src\sorting\*.java src\concurrent\*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilacion exitosa! Los archivos .class estan en el directorio bin\
    echo.
    echo Para ejecutar el programa, usa: run.bat [tiempo_en_segundos]
) else (
    echo.
    echo Error en la compilacion. Revisa los mensajes de error arriba.
)

pause

