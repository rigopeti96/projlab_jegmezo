cd game
./compile.sh
cd ..
cd test
./compile.sh
java -cp ./class/test.jar test.Program ../game/class/jegmezo.jar cases/$1 -Dfile.encoding=UTF-8
cd ..