all: ControllerTest.class

ControllerTest.class: Page Controller ControllerInterface
	javac ./controller/ControllerTest.java

Page: ./view/Page.java ControllerInterface
	javac ./view/Page.java

Controller: ./controller/Controller.java
	javac ./controller/Controller.java

ControllerInterface: ./controller/ControllerInterface.java
	javac ./controller/ControllerInterface.java
