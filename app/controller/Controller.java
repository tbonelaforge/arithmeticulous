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
import javax.swing.WindowConstants;


public class Controller extends JFrame implements ControllerInterface {
    private Node model;
    private Page view;
    private Container contentPane;

    public void initialize(Node model) {
        this.model = model;
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arithmetic");
        reset();
    }

    public void edit(ViewNode editable) {
        System.out.println("Inside the Controller.edit function, got CALLED, about to set the edit mode on thd nodeLabel.!%n");
        System.out.println("Before setting the editMode, the viewModel looks like:\n");
        view.printDebug();
        editable.setEditMode(EditMode.EDITING);
        System.out.println("After setting the edit Mode, of that node, the viewModel looks like:\n");
        view.printDebug();
        System.out.println("About to render the view!!!!\n");
        System.out.println("TAKE NOTE!!!!1 the size I think it should be is:\n");
        System.out.println(editable.computeWidth());
        renderNewView();
    }
    
    public void reset() {
        showView(createInitialView());
    }

    public void replace(ViewNode viewNode, ViewNode replacement) {
        System.out.println("INSIDE CONTROLLER.HANDLEREPLACEMENT, GOT CALLED!\n");
        model = model.replace(viewNode.getNode(), replacement.getNode());
        showView(createInitialView());
    }

    public void handleIncorrect(ViewNode viewNode) {
        System.out.println("Inside Controller.handleIncorrect, got called!\n");
    }

    public Frame getFrame() {
        return this;
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
        setVisible(true);
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
        view.createModal();
        return view;
    }

}
