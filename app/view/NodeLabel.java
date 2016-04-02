package view;

import interfaces.ViewNode;

import model.Node;

import javax.swing.JLabel;

public class NodeLabel extends JLabel implements ViewNode {
    private Node node;
    private ViewNode leftChild;
    private ViewNode rightChild;
    private EditMode editMode = EditMode.READ_ONLY;

    private static String dataTemplate = "%s<br />%nEditMode: %s<br />Type: NodeLabel";

    public NodeLabel(Node node) {
        this.node = node;
        initialize();
    }

    public Node getNode() {
        return node;
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

    public EditMode getEditMode() {
        return editMode;
    }

    public void setEditMode(EditMode editMode) {
        this.editMode = editMode;
    }

    public int computeWidth() {
        int thisWidth = getWidth();
        int leftWidth = ( leftChild != null ) ? leftChild.computeWidth() : 0;
        int rightWidth = ( rightChild != null ) ? rightChild.computeWidth() : 0;
        int computedWidth = leftWidth + thisWidth + rightWidth;

        return computedWidth;
    }

    private void initialize() {
        setText(node.getData());
    }

    public void printAsHTML() {
        String html = getHTML();
        System.out.println(html);
    }

    public String getHTML() {
        String template = "<table><tr><td colspan=\"2\" style=\"text-align:center;\">%s</td></tr><tr>%s</tr></table>";
        String thisData = getDataAsHTML();
        String childHTML = getChildrenAsTableCells();
        String htmlString = String.format(template, thisData, childHTML);
        return htmlString;
    }

    private String getDataAsHTML() {
        String dataAsHTML = String.format(dataTemplate,
                                          node.getData(),
                                          getEditMode()
                                          );
        return dataAsHTML;
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


