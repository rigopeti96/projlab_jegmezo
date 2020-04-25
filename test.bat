cd game
call compile.bat
cd ..
cd test
call compile.bat
cd ..
java -cp test/class/test.jar test.Program game/class/jegmezo.jar test/cases