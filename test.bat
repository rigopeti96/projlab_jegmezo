cd game
call compile.bat
cd ..
cd test
call compile.bat
java -cp ./class/test.jar test.Program ../game/class/jegmezo.jar cases/%1 -Dfile.encoding=UTF-8
cd ..