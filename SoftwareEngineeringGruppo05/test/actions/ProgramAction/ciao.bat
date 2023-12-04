@echo off
cls
echo.
if "%1"=="" (
    set /p nome=Inserisci il tuo nome: 
) else (
    set "nome=%1"
)
echo.
echo Ciao, %nome%!
echo.
pause
