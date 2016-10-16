all: ControllerTest.class ArithmeticApp.class

ControllerTest.class: ./arithmeticulous/controller/ControllerTest.java Page Controller ControllerInterface
	javac ./arithmeticulous/controller/ControllerTest.java

ArithmeticApp.class: ./arithmeticulous/ArithmeticApp.java Page Controller ControllerInterface
	javac ./arithmeticulous/ArithmeticApp.java

Page: ./arithmeticulous/view/Page.java ControllerInterface
	javac ./arithmeticulous/view/Page.java

Controller: ./arithmeticulous/controller/Controller.java
	javac ./arithmeticulous/controller/Controller.java

ControllerInterface: ./arithmeticulous/controller/ControllerInterface.java
	javac ./arithmeticulous/controller/ControllerInterface.java
