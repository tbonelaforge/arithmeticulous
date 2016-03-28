public class NodeTest {
    public static void main(String[] args) {
        Node node1 = new Natural(1);
        Node node3 = new Natural(3);
        Node node2 = new Operator("*");
        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        System.out.println("Just constructed test node:<br />\n");
        node2.printAsHTML();
    }
}
