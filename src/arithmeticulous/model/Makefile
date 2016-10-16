all: Node.class Operator.class Natural.class PrintableTree.class TreePrinter.class NodeGenerator.class NodeGeneratorTest.class

Node.class: Node.java
	javac -cp ../.. Node.java

Operator.class: Operator.java
	javac -cp ../../ Operator.java

Natural.class: Natural.java
	javac -cp ../../ Natural.java

PrintableTree.class: PrintableTree.java
	javac -cp ../../ PrintableTree.java

TreePrinter.class: TreePrinter.java PrintableTree.class
	javac -cp ../../ TreePrinter.java

NodeGenerator.class: NodeGenerator.java
	javac -cp ../../ NodeGenerator.java

NodeGeneratorTest.class: NodeGeneratorTest.java TreePrinter.class
	javac -cp ../../ NodeGeneratorTest.java
