package view;

import interfaces.ViewNode;

import model.Node;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.text.DefaultCaret;

public class NodeTextField extends JTextField implements ViewNode, FocusListener {
    private Node node;
    private ViewNode leftChild;
    private ViewNode rightChild;
    private EditMode editMode = EditMode.EDITING;
    private String initialText;
    private static String dataTemplate = "%s<br />%nEditMode: %s";

    public NodeTextField(ViewNode viewNode) {
        this.node = viewNode.getNode();
        initialize(viewNode);
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

    private void initialize(ViewNode viewNode) {
        setCaret(new DefaultCaret());
        getCaret().setBlinkRate(500);
        setBorder(BorderFactory.createEmptyBorder());
        /*
        setFont(
                viewNode.getFontName(),
                viewNode.getFontStyle(),
                viewNode.getFontSize()
                );
        */
        addFocusListener(this);
    }

    public void printAsHTML() {
        String html = getHTML();
        System.out.println(html);
    }

    public String getHTML() {
        return toString();
    }

    public int computeWidth() {
        return getWidth();
    }

    public void focusLost(FocusEvent focusEvent) {
        System.out.println("The text field lost focus!\n");
    }

    public void focusGained(FocusEvent focusEvent) {
        System.out.println("the text field gained focus!!!\n");
        setText(node.getText());
    }
}
