@echo off
echo ================================================
echo   SUBIENDO PROYECTO A GITHUB
echo ================================================
echo.

REM Verificar si git está instalado
git --version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Git no está instalado o no está en el PATH
    echo Por favor instala Git desde https://git-scm.com/
    pause
    exit /b 1
)

echo 1. Inicializando repositorio Git...
if not exist .git (
    git init
    echo    ✓ Repositorio inicializado
) else (
    echo    ✓ Repositorio ya existe
)

echo.
echo 2. Configurando remote de GitHub...
git remote remove origin 2>nul
git remote add origin https://github.com/ytmness/proyectofinalestructuras.git
echo    ✓ Remote configurado

echo.
echo 3. Agregando archivos...
git add .
echo    ✓ Archivos agregados

echo.
echo 4. Creando commit...
git commit -m "Proyecto Final: Ordenamiento Concurrente con Seis Algoritmos Clásicos" 2>nul
if %ERRORLEVEL% EQU 0 (
    echo    ✓ Commit creado
) else (
    echo    ⚠ No hay cambios nuevos para commitear
)

echo.
echo 5. Configurando rama main...
git branch -M main 2>nul
echo    ✓ Rama configurada

echo.
echo 6. Subiendo a GitHub...
echo    (Puede pedirte credenciales de GitHub)
git push -u origin main

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ================================================
    echo   ✓ PROYECTO SUBIDO EXITOSAMENTE
    echo ================================================
    echo.
    echo Visita: https://github.com/ytmness/proyectofinalestructuras
) else (
    echo.
    echo ================================================
    echo   ⚠ ERROR AL SUBIR
    echo ================================================
    echo.
    echo Posibles causas:
    echo - No estás autenticado en GitHub
    echo - Las credenciales son incorrectas
    echo - El repositorio no existe o no tienes permisos
    echo.
    echo Soluciones:
    echo 1. Usa GitHub Desktop o GitHub CLI
    echo 2. Configura un Personal Access Token
    echo 3. Verifica que el repositorio existe y tienes acceso
)

echo.
pause

