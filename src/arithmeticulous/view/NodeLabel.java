package arithmeticulous.view;

import arithmeticulous.model.Node;

import java.awt.Component;

import javax.swing.JLabel;

public class NodeLabel extends ViewNode {
    private JLabel label;

    private static String htmlDataTemplate = "%s<br />%nEditMode: %s<br />Type: NodeLabel";

    public NodeLabel(Node node) {
        super(node);
        this.label = new JLabel();
        initialize();
    }

    private void initialize() {
        this.label.setText(getNode().getData());
    }

    @Override
    public String getDataAsHTML() {
        String dataAsHTML = String.format(htmlDataTemplate,
                                          getNode().getData(),
                                          getEditMode()
                                          );
        return dataAsHTML;
    }

    public Component getComponent() {
        return label;

    }
}


