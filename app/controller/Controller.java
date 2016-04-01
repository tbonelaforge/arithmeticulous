package controller;

//import model.EditMode;
import model.Node;
//import interfaces.NodeEditor;
//import interfaces.NodeReplacer;
import interfaces.ControllerInterface;
import view.EditMode;
import view.Page;
import view.NodeLabel;

import java.awt.Container;
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
        //        renderView();
        view = createInitialView();
        showView();
    }

        public void edit(NodeLabel nodeLabel) {
        System.out.println("Inside the handleEdit function, got CALLED, about to set the edit mode on thd nodeLabel.!%n");
        System.out.println("Before setting the editMode, the viewModel looks like:\n");
        view.printDebug();
        nodeLabel.setEditMode(EditMode.EDITING);
        System.out.println("After setting the edit Mode, of that node, the viewModel looks like:\n");
        view.printDebug();
        System.out.println("About to render the view!!!!\n");
        renderNewView();
    }
    
    public void replace(NodeLabel nodeLabel, NodeLabel replacement) {
        System.out.println("Inside Controller.handleReplacement, got called!\n");
    }

    public void handleIncorrect(NodeLabel nodeLabel) {
        System.out.println("Inside Controller.handleIncorrect, got called!\n");
    }

    private void renderNewView() {
        view = generateNewView(view);
        showView();
    }

    private void showView() {
        contentPane.removeAll();
        contentPane.add(view);
        contentPane.revalidate();
        contentPane.repaint();
        setVisible(true);
        view.whenShown();
    }

    private Page createInitialView() {
        Page view = new Page();
        view.initialize(model);
        return view;
    }

    private Page generateNewView(Page currentView) {
        Page view = new Page(currentView);
        //        view.initialize(model);
        // view.setEditHandler(this.editHandler);
        // view.setCorrectHandler(this.correctHandler);
        // view.setIncorrectHandler(this.incorrectHandler);
        view.setControllerInterface(this);
        return view;
    }



}
