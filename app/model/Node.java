package model;

public class Node implements PrintableTree {
    private Node leftChild;
    private Node rightChild;

    protected static String dataTemplate = "%s";

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public String getDataAsHTML() {
        throw new RuntimeException("printDataAsHTML not implemented for: " + this);
    }

    public String getData() {
        throw new RuntimeException("printData not implemented for: " + this);
    }

    public String getText() {
        throw new RuntimeException("getText not implemented for: " + this);
    }

    public Node evaluate() {
        throw new RuntimeException("evaluate not implemented for: " + this);
    }

    public Node replace(Node target, Node replacement) {
        if (this == target) {
            return replacement;
        }
        if (getLeftChild() != null) {
            setLeftChild(getLeftChild().replace(target, replacement));
        }
        if (getRightChild() != null) {
            setRightChild(getRightChild().replace(target, replacement));
        }
        return this;
    }
}
