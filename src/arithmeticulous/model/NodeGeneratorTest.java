package arithmeticulous.model;

public class NodeGeneratorTest {
    public static void main(String[] args) {
        Node randomExpression = NodeGenerator.generateRandomExpression(4);
        System.out.println("Inside NodeGeneratorTest, just generated random expression:\n");
        TreePrinter.printAsHTML(randomExpression);
    }
}
