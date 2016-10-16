package arithmeticulous.model;

public interface PrintableTree {
    String getDataAsHTML();
    PrintableTree getLeftChild();
    PrintableTree getRightChild();
}
