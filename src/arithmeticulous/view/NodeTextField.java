package arithmeticulous.view;

import arithmeticulous.model.Node;
import arithmeticulous.model.TreePrinter;

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
        textField.setText(getNode().getText());
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
        textField.setText(getNode().getText());
        textField.selectAll();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (onSubmit != null) {
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
