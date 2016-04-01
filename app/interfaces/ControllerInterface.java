package interfaces;

import view.NodeLabel;

public interface ControllerInterface {
    void edit(NodeLabel nodeLabel);
    void replace(NodeLabel nodeLabel, NodeLabel replacement);
    void handleIncorrect(NodeLabel nodeLabel);
}
