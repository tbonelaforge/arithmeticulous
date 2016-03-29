package controller;

import model.EditMode;
import model.Node;
import interfaces.NodeEditor;
import interfaces.NodeReplacer;
import view.Page;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class Controller extends JFrame {
    private Node model;
    private Page view;
    private Container contentPane;
    private NodeEditor editHandler = new NodeEditor() {
        public void edit(Node node) {
            handleEdit(node);
        }
    };
    private NodeReplacer correctHandler = new NodeReplacer() {
        public void replace(Node node, Node replacement) {
            handleReplacement(node, replacement);
        }  
    };
    private NodeEditor incorrectHandler = new NodeEditor() {
        public void edit(Node node) {
            handleIncorrect(node);
        }
    };

    public void initialize(Node model) {
        this.model = model;
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Arithmetic");
        renderView();
    }

    private void renderView() {
        view = generateView();
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

    private Page generateView() {
        Page view = new Page();
        view.initialize(model);
        view.setEditHandler(this.editHandler);
        view.setCorrectHandler(this.correctHandler);
        view.setIncorrectHandler(this.incorrectHandler);
        return view;
    }

    private void handleEdit(Node node) {
        System.out.println("Inside the handleEdit function, got CALLED, about to set the edit mode on thd node.!%n");
        System.out.println("Before setting the editMode, the model looks like:\n");
        model.printAsHTML();
        node.setEditMode(EditMode.EDITING);
        System.out.println("After setting the edit Mode, of that node, the model now looks like:\n");
        model.printAsHTML();
        System.out.println("About to render the view!!!!\n");
        renderView();
        
    }
    
    private void handleReplacement(Node node, Node replacement) {
        System.out.println("Inside Controller.handleReplacement, got called!\n");
    }

    private void handleIncorrect(Node node) {
        System.out.println("Inside Controller.handleIncorrect, got called!\n");
    }

}
