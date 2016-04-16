package controller;

import view.ViewNode;

public interface ControllerInterface {
    void edit(ViewNode editable);
    void reset();
    void replace(ViewNode viewNode, ViewNode replacement);
    void handleIncorrect(ViewNode viewNode);
}
