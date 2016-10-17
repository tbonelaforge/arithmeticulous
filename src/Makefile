all: ArithmeticApp.class

ArithmeticApp.class: ./arithmeticulous/ArithmeticApp.java Model View Controller
	javac ./arithmeticulous/ArithmeticApp.java

.PHONY: Model

Model:
	cd ./arithmeticulous/model; make

.PHONY: View

View:
	cd ./arithmeticulous/view; make

.PHONY: Controller

Controller:
	cd ./arithmeticulous/controller; make
