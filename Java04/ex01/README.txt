find . -name "*.java" > sources.txt

mkdir target
mkdir target/edu
mkdir target/edu/school21
mkdir target/edu/school21/printer
mkdir target/edu/school21/printer/app
mkdir target/edu/school21/printer/logic

javac -d target @sources.txt
java -cp target Program . 0 it.bmp