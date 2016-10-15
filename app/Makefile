all: ControllerTest.class ArithmeticApp.class ArithmeticAppTest.class

ControllerTest.class: Page Controller ControllerInterface
	javac ./controller/ControllerTest.java

ArithmeticApp.class: ArithmeticApp.java
	javac ./ArithmeticApp.java

ArithmeticAppTest.class: ArithmeticAppTest.java ArithmeticApp.class
	javac ./ArithmeticAppTest.java

Page: ./view/Page.java ControllerInterface
	javac ./view/Page.java

Controller: ./controller/Controller.java
	javac ./controller/Controller.java

ControllerInterface: ./controller/ControllerInterface.java
	javac ./controller/ControllerInterface.java
