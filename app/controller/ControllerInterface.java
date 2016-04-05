package controller;

import view.ViewNode;

//import view.NodeLabel;

public interface ControllerInterface {
    void edit(ViewNode editable);
    //    void replace(NodeLabel nodeLabel, NodeLabel replacement);
    void replace(ViewNode viewNode, ViewNode replacement);
    //    void handleIncorrect(NodeLabel nodeLabel);
    void handleIncorrect(ViewNode viewNode);
}
