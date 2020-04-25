javac src/test/*.java -d class
cd class
jar cvfe test.jar test.Program test/
cd ..