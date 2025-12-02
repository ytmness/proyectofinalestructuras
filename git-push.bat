@echo off
echo ================================================
echo   SUBIENDO PROYECTO A GITHUB
echo ================================================
echo.

REM Verificar si git está instalado
git --version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Git no está instalado
    pause
    exit /b 1
)

echo 1. Inicializando Git (si es necesario)...
if not exist .git (
    git init
    echo    ✓ Repositorio inicializado
) else (
    echo    ✓ Repositorio ya existe
)

echo.
echo 2. Configurando remote...
git remote remove origin 2>nul
git remote add origin https://github.com/ytmness/proyectofinalestructuras.git
git remote -v
echo    ✓ Remote configurado

echo.
echo 3. Agregando todos los archivos...
git add -A
git status --short
echo    ✓ Archivos agregados

echo.
echo 4. Creando commit...
git commit -m "Proyecto completo: Ordenamiento Concurrente - 6 algoritmos, documentacion completa, scripts de utilidad"
if %ERRORLEVEL% EQU 0 (
    echo    ✓ Commit creado exitosamente
) else (
    echo    ⚠ No hay cambios nuevos o error en commit
    git status
)

echo.
echo 5. Configurando rama main...
git branch -M main 2>nul
echo    ✓ Rama configurada

echo.
echo 6. Subiendo a GitHub...
echo    (Puede pedirte credenciales)
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
    echo.
    echo Soluciones:
    echo 1. Configura un Personal Access Token
    echo 2. Usa GitHub Desktop
    echo 3. Ejecuta: git config --global credential.helper wincred
)

echo.
pause

