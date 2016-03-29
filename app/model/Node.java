package model;

public class Node {
    private Node leftChild;
    private Node rightChild;
    private EditMode editMode = EditMode.READ_ONLY;

    protected static String dataTemplate = "%s<br />%nEdit Mode: %s";

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

    public EditMode getEditMode() {
        return editMode;
    }

    public void setEditMode(EditMode editMode) {
        this.editMode = editMode;
    }

    public void printAsHTML() {
        String htmlString = getHTML();
        System.out.println(htmlString);
    }

    public String getHTML() {
        String template = "<table><tr><td colspan=\"2\" style=\"text-align:center;\">%s</td></tr><tr>%s</tr></table>";
        String thisData = getDataAsHTML();
        String childHTML = getChildrenAsTableCells();
        String htmlString = String.format(template, thisData, childHTML);
        return htmlString;
    }

    public String getDataAsHTML() {
        throw new RuntimeException("printDataAsHTML not implemented for: " + this);
    }

    public String getData() {
        throw new RuntimeException("printData not implemented for: " + this);
    }

    public String getChildrenAsTableCells() {
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