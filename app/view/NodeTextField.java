package view;

import model.Node;
import model.TreePrinter;

import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.text.DefaultCaret;

public class NodeTextField extends ViewNode implements FocusListener, ActionListener {
    private JTextField textField;
    private Runnable onSubmit;

    private static String htmlDataTemplate = "%s<br />%nEditMode: %s<br />Type: NodeTextField";

    public NodeTextField(ViewNode viewNode) {
        super(viewNode.getNode());
        this.textField = new JTextField();
        initialize(viewNode);
    }

    private void initialize(ViewNode viewNode) {
        textField.setCaret(new DefaultCaret());
        textField.getCaret().setBlinkRate(500);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.addFocusListener(this);
        textField.addActionListener(this);
    }

    public String getDataAsHTML() {
        String dataAsHTML = String.format(htmlDataTemplate,
                                          getNode().getData(),
                                          getEditMode()
                                          );
        return dataAsHTML;
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
        TreePrinter.printAsHTML(getNode());
        System.out.println("The node.getText() is:\n" + getNode().getText());
        textField.setText(getNode().getText());
        System.out.println("After setting the text, the text is:\n");
        System.out.println(textField.getText());
        textField.selectAll();
        System.out.println("After selecting all, the NodeTextField has Font:\n");
        System.out.println(textField.getFont());
    }

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Inside actionPerformed, got called!!!!\n");
        if (onSubmit != null) {
            System.out.println("Inside actionPerformed, determined that onSubmit is not null, about to run it!\n");
            onSubmit.run();
        }
    }

    public Component getComponent() {
        return textField;
    }

    public void setOnSubmit(Runnable runnable) {
        onSubmit = runnable;
    }

    public String getText() {
        return textField.getText();
    }
}
