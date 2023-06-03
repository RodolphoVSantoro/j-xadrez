ifeq ($(OS),Windows_NT)     # is Windows_NT on XP, 2000, 7, Vista, 10...
	detected_OS := Windows
else
	detected_OS := $(shell uname)  # same as "uname -s"
endif

default:
	javac ./gui/*.java
	javac ./pecas/*.java
	javac ./maquinaDeRegras/*.java
	javac ./utils/*.java
	javac ./config/*.java
run:
	java Tela.java

ifeq ($(detected_OS),Windows)
clean:
	del /S gui\*.class
	del /S pecas\*.class
	del /S maquinaDeRegras\*.class
	del /S utils\*.class
	del /S config\*.class
build_tests:
	javac -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" ./tests/config/SetupPecasTest.java
	javac -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" ./tests/config/SetupTabuleiroTest.java
	javac -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" ./tests/config/maquinaDeRegras/IATest.java

run_tests:
	java -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" tests/TestRunner.java 
else
clean:
	rm -rf ./gui/*.class
	rm -rf ./pecas/*.class
	rm -rf ./maquinaDeRegras/*.class
	rm -rf ./utils/*.class
	rm -rf ./config/*.class

build_tests:
	javac -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" ./tests/config/SetupPecasTest.java
	javac -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" ./tests/config/SetupTabuleiroTest.java
	javac -cp ".:lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" ./tests/config/maquinaDeRegras/IATest.java

run_tests:
	java -cp ".:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" tests/TestRunner.java 
endif