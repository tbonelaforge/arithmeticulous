package view;

import model.Node;

import java.awt.Component;

public class ViewNode {
    private ViewNode leftChild;
    private ViewNode rightChild;
    private EditMode editMode;
    private Node node;

    private static String htmlTemplate = "<table><tr><td colspan=\"2\" style=\"text-align:center;\">%s</td></tr><tr>%s</tr></table>";

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

    public void printAsHTML() {
        String html = getHTML();
        System.out.println(html);
    }

    public String getHTML() {
        String thisData = getDataAsHTML();
        String childHTML = getChildrenAsTableCells();
        String htmlString = String.format(htmlTemplate, thisData,childHTML);
        return htmlString;
    }

    
    public String getDataAsHTML() {
        throw new RuntimeException("getDataAsHTML not implemented for: " + this);
    }

    private String getChildrenAsTableCells() {
        String template = "<td>%s</td><td>%s</td>";
        String leftChildString = "";
        if (getLeftChild() != null) {
            leftChildString = getLeftChild().getHTML();
        }
        String rightChildString = "";
        if (getRightChild() != null) {
            rightChildString = getRightChild().getHTML();
        }
        String childrenAsHTML = String.format(template, leftChildString, rightChildString);
        return childrenAsHTML;
    }
}
