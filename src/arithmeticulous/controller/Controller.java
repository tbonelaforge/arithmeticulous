package arithmeticulous.controller;

import arithmeticulous.model.Node;
import arithmeticulous.view.EditMode;
import arithmeticulous.view.Page;
import arithmeticulous.view.NodeLabel;
import arithmeticulous.view.ViewNode;
import arithmeticulous.ArithmeticApp;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controller implements ControllerInterface {
    private Node model;
    private Page view;
    private ArithmeticApp app;
    private Container contentPane;
    private JButton playAgainButton;
    private JButton quitButton;

    public Controller(ArithmeticApp app) {
        this.app = app;
        contentPane = app.getContentPane();
    }

    public Controller(Container contentPane) {
        this.contentPane = contentPane;
    }

    public void initialize(Node model) {
        this.model = model;
        contentPane.setLayout(new GridBagLayout());
        reset();
    }

    public void reset() {
        showView(createInitialView());
    }

    private void showView(Page newView) {
        contentPane.removeAll();
        if (view != null) {
            view.cleanUp();
        }
        contentPane.add(newView, endRow());
        view = newView;
        addButtons();
        if (view.isAllCorrect()) {
            app.stopTiming();
        }
        contentPane.revalidate();
        contentPane.repaint();
        newView.whenShown();
    }

    private Page createInitialView() {
        Page view = new Page(this);
        view.initializeFromNode(model);
        return view;
    }

    public void edit(ViewNode editable) {
        editable.setEditMode(EditMode.EDITING);
        renderNewView();
    }

    public void replace(ViewNode viewNode, ViewNode replacement) {
        model = model.replace(viewNode.getNode(), replacement.getNode());
        reset();
    }

    public Frame getFrame() {
        return (Frame) app;
    }

    private void renderNewView() {
        Page newView = generateNewView(view);
        showView(newView);
    }

    private Page generateNewView(Page currentView) {
        Page view = new Page(this, currentView);
        return view;
    }

    private void addButtons() {
        GridLayout gridLayout = new GridLayout(2, 1);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(gridLayout);
        addPlayAgainButton(buttonPanel);
        addQuitButton(buttonPanel);
        contentPane.add(buttonPanel, endRow());
    }

    private void addPlayAgainButton(JPanel panel) {
        playAgainButton = new JButton();
        playAgainButton.setText("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (app != null) {
                    Node newExpression = app.makeExpression();
                    initialize(newExpression);
                    app.startTiming(newExpression.countOperators());
                }
            }
        });
        playAgainButton.setVisible(view.isAllCorrect());
        panel.add(playAgainButton);
    }

    private void addQuitButton(JPanel panel) {
        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            };
        });
        quitButton.setVisible(view.isAllCorrect());
        panel.add(quitButton);
    }

    private GridBagConstraints endRow() {
        GridBagConstraints rowEndConstraints = new GridBagConstraints();
        rowEndConstraints.gridwidth = GridBagConstraints.REMAINDER;
        return rowEndConstraints;
    }
}
