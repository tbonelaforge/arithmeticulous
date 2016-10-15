package controller;

import model.Node;
import view.EditMode;
import view.Page;
import view.NodeLabel;
import view.ViewNode;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class Controller implements ControllerInterface {
    private Node model;
    private Page view;
    private JFrame frame;
    private Container contentPane;

    public Controller(JFrame frame) {
        this.frame = frame;
    }

    public void initialize(Node model) {
        this.model = model;
        contentPane = frame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        reset();
    }

    public void reset() {
        showView(createInitialView());
    }

    public void edit(ViewNode editable) {
        editable.setEditMode(EditMode.EDITING);
        renderNewView();
    }

    public void replace(ViewNode viewNode, ViewNode replacement) {
        model = model.replace(viewNode.getNode(), replacement.getNode());
        reset();
    }

    public void handleIncorrect(ViewNode viewNode) {
        System.out.println("Inside Controller.handleIncorrect, got called!\n");
    }

    public Frame getFrame() {
        return frame;
    }

    private void renderNewView() {
        Page newView = generateNewView(view);
        showView(newView);
    }

    private void showView(Page newView) {
        contentPane.removeAll();
        if (view != null) {
            view.cleanUp();
        }
        contentPane.add(newView);
        contentPane.revalidate();
        contentPane.repaint();
        frame.setVisible(true);
        newView.whenShown();
        view = newView;
    }

    private Page createInitialView() {
        Page view = new Page();
        view.initializeFromNode(model);
        view.setControllerInterface(this);
        return view;
    }

    private Page generateNewView(Page currentView) {
        Page view = new Page(currentView);
        view.setControllerInterface(this);
        return view;
    }
}
