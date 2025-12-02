@echo off
echo ================================================
echo   LIMPIEZA DE ARCHIVOS INNECESARIOS
echo ================================================
echo.

echo Eliminando archivos compilados (bin/)...
if exist bin (
    rmdir /s /q bin
    echo    ✓ Carpeta bin eliminada
) else (
    echo    ✓ Carpeta bin no existe
)

echo.
echo Eliminando archivos temporales...
del /q *.tmp 2>nul
del /q *.bak 2>nul
del /q *.log 2>nul
del /q *~ 2>nul
del /q *.swp 2>nul
echo    ✓ Archivos temporales limpiados

echo.
echo ================================================
echo   LIMPIEZA COMPLETADA
echo ================================================
echo.
echo Archivos mantenidos:
echo   - src/ (código fuente)
echo   - *.bat (scripts útiles)
echo   - README.md (documentación)
echo   - RESUMEN_PROYECTO.md (documentación detallada)
echo   - .gitignore (configuración Git)
echo.
echo Nota: Los archivos compilados se regenerarán al ejecutar compile.bat
echo.
pause

