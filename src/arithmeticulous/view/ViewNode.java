package arithmeticulous.view;

import arithmeticulous.model.Node;
import arithmeticulous.model.Natural;
import arithmeticulous.model.Operator;
import arithmeticulous.model.PrintableTree;
import arithmeticulous.model.TreePrinter;

import java.awt.Component;
import javax.swing.JLabel;

public class ViewNode implements PrintableTree {
    private ViewNode leftChild;
    private ViewNode rightChild;
    private EditMode editMode;
    private Node node;
    private JLabel leftParen;
    private JLabel rightParen;

    public static boolean YES_PARENS = true;
    public static boolean NO_PARENS = false;

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

    public void setLeftParen(JLabel leftParen) {
        this.leftParen = leftParen;
    }

    public void setRightParen(JLabel rightParen) {
        this.rightParen = rightParen;
    }

    public int computeWidth(boolean shouldIncludeParens) {
        int thisWidth = getComponent().getWidth();
        int leftWidth = ( leftChild != null ) ? leftChild.computeWidth(YES_PARENS) : 0;
        int rightWidth = ( rightChild != null ) ? rightChild.computeWidth(YES_PARENS) : 0;
        int computedWidth = leftWidth + thisWidth + rightWidth;
        if (shouldIncludeParens == YES_PARENS) {
            if (leftParen != null) {
                computedWidth += leftParen.getWidth();
            }
            if (rightParen != null) {
                computedWidth += rightParen.getWidth();
            }
        }
        return computedWidth;
    }

    public Component getComponent() {
        throw new RuntimeException("GetComponent not implemented for " + this);
    }

    public String getDataAsHTML() {
        throw new RuntimeException("getDataAsHTML not implemented for: " + this);
    }

    public String getHint() {
        Node leftNode = getNode().getLeftChild();
        Node rightNode = getNode().getRightChild();
        if (leftNode == null || !(leftNode instanceof Natural) ||
            rightNode == null || !(rightNode instanceof Natural)) {
            return "Try a simpler expression.";
        }
        if (!(getNode() instanceof Operator)) {
            throw new RuntimeException("Can only get hint for Operator nodes.");
        }
        Operator operator = (Operator) getNode();
        String fullOpName = getFullOperationName(operator);
        String briefOpName = getBriefOperationName(operator);
        int operand1 = ((Natural) leftNode).getValue();
        int operand2 = ((Natural) rightNode).getValue();

        // Construct a hint string, e.g. 'Try multiplying 2 times 3.'
        String hint = String.format("Try %s %d %s %d", fullOpName, operand1, briefOpName, operand2);
        return hint;
    }

    private static String getFullOperationName(Operator operator) {
        switch (operator.getType()) {
        case "*":
            return "multiplying";
        case "+":
            return "adding";
        default:
            throw new RuntimeException("Unrecognized operator: " + operator.getType());
        }
    }

    private static String getBriefOperationName(Operator operator) {
        switch (operator.getType()) {
        case "*":
            return "times";
        case "+":
            return "plus";
        default:
            throw new RuntimeException("Unrecognized operator: " + operator.getType());
        }
    }
}
