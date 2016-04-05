package interfaces;

import model.Node;

import view.EditMode;

public interface ViewNode {
    String getHTML();
    ViewNode getLeftChild();
    void setLeftChild(ViewNode viewNode);
    ViewNode getRightChild();
    void setRightChild(ViewNode viewNode);
    void printAsHTML();
    EditMode getEditMode();
    int computeWidth();
    Node getNode();
}