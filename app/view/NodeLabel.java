package view;

import model.Node;

import javax.swing.JLabel;

public class NodeLabel extends JLabel {
    private Node node;

    public NodeLabel(Node node) {
        this.node = node;
        initialize();
    }

    public Node getNode() {
        return node;
    }

    private void initialize() {
        setText(node.getData());
    }
}
