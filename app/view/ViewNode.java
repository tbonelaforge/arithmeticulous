package view;

import model.Node;
import model.PrintableTree;

import java.awt.Component;

public class ViewNode implements PrintableTree {
    private ViewNode leftChild;
    private ViewNode rightChild;
    private EditMode editMode;
    private Node node;

    public ViewNode(Node node) {
        this.node = node;
    }
    
    public ViewNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ViewNode leftChild) {
        this.leftChild = leftChild;
    }

    public ViewNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(ViewNode rightChild) {
        this.rightChild = rightChild;
    }

    public Node getNode() {
        return node;
    }

    public EditMode getEditMode() {
        return editMode;
    }

    public void setEditMode(EditMode editMode) {
        this.editMode = editMode;
    }

    public int computeWidth() {
        int thisWidth = getComponent().getWidth();
        int leftWidth = ( leftChild != null ) ? leftChild.computeWidth() : 0;
        int rightWidth = ( rightChild != null ) ? rightChild.computeWidth() : 0;
        int computedWidth = leftWidth + thisWidth + rightWidth;

        return computedWidth;
    }

    public Component getComponent() {
        throw new RuntimeException("GetComponent not implemented for " + this);
    }

    public String getDataAsHTML() {
        throw new RuntimeException("getDataAsHTML not implemented for: " + this);
    }
}
