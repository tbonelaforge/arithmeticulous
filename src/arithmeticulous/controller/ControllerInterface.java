package arithmeticulous.controller;

import java.awt.Frame;

import arithmeticulous.view.ViewNode;

public interface ControllerInterface {
    void edit(ViewNode editable);
    void reset();
    void replace(ViewNode viewNode, ViewNode replacement);
    Frame getFrame();
}
