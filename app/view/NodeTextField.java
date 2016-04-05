package view;

//import interfaces.ViewNode;

import model.Node;

import java.awt.Component;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.text.DefaultCaret;

//public class NodeTextField extends JTextField implements ViewNode, FocusListener {
public class NodeTextField extends ViewNode implements FocusListener {
    //    private Node node;
    //    private ViewNode leftChild;
    //    private ViewNode rightChild;
    //    private EditMode editMode = EditMode.EDITING;
    //    private String initialText;
    private JTextField textField;
    private static String htmlDataTemplate = "%s<br />%nEditMode: %s<br />Type: NodeTextField";


    public NodeTextField(ViewNode viewNode) {
        super(viewNode.getNode());
        //        this.node = viewNode.getNode();
        //        this.component = new JTextField();
        this.textField = new JTextField();
        initialize(viewNode);
    }
    /*
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
    */
    private void initialize(ViewNode viewNode) {
        textField.setCaret(new DefaultCaret());
        textField.getCaret().setBlinkRate(500);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.addFocusListener(this);
    }

    public void printAsHTML() {
        String html = getHTML();
        System.out.println(html);
    }

    public String getHTML() {
        //        return toString();
        String html = String.format(getHtmlDataTemplate(),
                                    textField.getText(),
                                    getEditMode()
                                    );
        return html;
    }

    public int computeWidth() {
        return textField.getWidth();
    }

    public void focusLost(FocusEvent focusEvent) {
        System.out.println("The text field lost focus!\n");
    }

    public void focusGained(FocusEvent focusEvent) {
        System.out.println("the text field gained focus!!!\n");
        System.out.println("The underlying node is:\n");
        getNode().printAsHTML();
        System.out.println("The node.getText() is:\n" + getNode().getText());
        textField.setText(getNode().getText());
        System.out.println("After setting the text, the text is:\n");
        System.out.println(textField.getText());
        textField.selectAll();
        System.out.println("After selecting all, the NodeTextField has Font:\n");
        System.out.println(textField.getFont());
    }

    public String getHtmlDataTemplate() {
        return htmlDataTemplate;
    }

    public Component getComponent() {
        return textField;
    }
}
