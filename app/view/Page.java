package view;

import model.Node;
import model.Operator;
import model.TreePrinter;

import controller.ControllerInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Page extends JPanel {
    private Font labelFont;
    private int labelFontSize;
    private int labelFontStyle;
    private String labelFontName;
    private GroupLayout groupLayout;
    private SequentialGroup horizontalGroup;
    private ParallelGroup verticalGroup;
    private ControllerInterface controllerInterface;
    private ViewNode rootViewNode;
    private ViewNode editable;
    private int editableLevel;
    private NodeTextField textField;

    private static Color clickableColor = new Color(200, 0, 255);

    public Page() {
        JLabel temp = new JLabel();
        Font exampleFont = temp.getFont();
        labelFontStyle = exampleFont.getStyle();
        labelFontSize = 2 * exampleFont.getSize();
        labelFontName = exampleFont.getName();
    }

    public Page(Page page) {
        this();
        initializeFromViewNode(page.rootViewNode);
    }

    public void initializeFromNode(Node rootNode) {
        initializeLayout();
        rootViewNode = traverseNode(rootNode, 1);
        if (editable != null) {
            editable.setEditMode(EditMode.EDITABLE);
            editable.getComponent().setForeground(clickableColor);
            editable.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
            editable.getComponent().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    System.out.println("Inside mouseClicked, got caleld with mouseEvent!!!!:\n");
                    System.out.println(mouseEvent);
                    System.out.println("It has source!!!!111\n");
                    System.out.println(mouseEvent.getSource());
                    if (controllerInterface != null) {
                        System.out.println("Inside mouseClicked, the controllerInterface is not null, about to call controllerInterface.edit\n");
                        controllerInterface.edit(editable);
                    } else {
                        System.out.println("Inside mouseClicked, the controllerInterface is null...\n");
                    }
                }
            });
        } else {
            System.out.println("There is no editable viewNode...\n");
        }
    }

    private void initializeFromViewNode(ViewNode oldRootViewNode) {
        initializeLayout();
        rootViewNode = traverseViewNode(oldRootViewNode);
        System.out.println("Inside initializeFromViewNode, after traversing, the rootViewNode now looks like:\n");
        TreePrinter.printAsHTML(rootViewNode);
        if (textField != null) {
            System.out.println("Inside Page.initializeFromViewNode, after traverseing, we know we have a textField, about to set it's onSubmit...\n");
            textField.setOnSubmit(new Runnable() {
                public void run() {
                    System.out.println("Inside Page, the submit handler is running!!!\n");
                    String submitted = textField.getText();
                    Node expected = textField.getNode().evaluate();
                    System.out.printf("Inside The submit handler, about to compare %s and %s%n", submitted, expected.getData());
                    if (submitted.equals(expected.getData())) {
                        ViewNode replacement = (ViewNode) createNodeLabel(expected);
                        if (controllerInterface != null) {
                            controllerInterface.replace(textField, replacement);
                        }
                    } else {
                        System.out.printf("INCORRECT: submitted %s expected %s%n", submitted, expected.getData());
                    }
                }
            });
        }
    }


    private void initializeLayout() {
        groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(false);
        groupLayout.setAutoCreateContainerGaps(false);
        horizontalGroup = groupLayout.createSequentialGroup();
        groupLayout.setHorizontalGroup(horizontalGroup);
        verticalGroup = groupLayout.createParallelGroup();
        groupLayout.setVerticalGroup(verticalGroup);
    }

    private ViewNode traverseNode(Node node, int level) {

        // In-order traversal for horizontal ordering.
        if (node == null) {
            return null;
        }
        ViewNode leftViewNode = traverseNode(node.getLeftChild(), level + 1);
        ViewNode thisViewNode = (ViewNode) createNodeLabel(node);
        horizontalGroup.addComponent(thisViewNode.getComponent());
        verticalGroup.addComponent(thisViewNode.getComponent());

        // Find the "max" (the first, lowest level operator)
        if (node instanceof Operator) {
            if (level > editableLevel) {
                editable = thisViewNode;
            }
        }
        ViewNode rightViewNode = traverseNode(node.getRightChild(), level + 1);
        thisViewNode.setLeftChild(leftViewNode);
        thisViewNode.setRightChild(rightViewNode);
        return thisViewNode;
    }

    private ViewNode traverseViewNode(ViewNode viewNode) {

        // In-order traversal for horizontal ordering.
        if (viewNode == null) {
            return null;
        }
        ViewNode thisViewNode = createViewNode(viewNode);
        if (thisViewNode instanceof NodeTextField) {
            int width = viewNode.computeWidth();
            textField = (NodeTextField) thisViewNode;
            horizontalGroup.addComponent(thisViewNode.getComponent(),
                                         width,
                                         width,
                                         width
                                         );
            verticalGroup.addComponent(thisViewNode.getComponent());
            return thisViewNode;
        }
        ViewNode leftViewNode = traverseViewNode(viewNode.getLeftChild());
        horizontalGroup.addComponent(thisViewNode.getComponent());
        verticalGroup.addComponent(thisViewNode.getComponent());
        ViewNode rightViewNode = traverseViewNode(viewNode.getRightChild());
        thisViewNode.setLeftChild(leftViewNode);
        thisViewNode.setRightChild(rightViewNode);
        return thisViewNode;
    }


    private NodeLabel createNodeLabel(Node node) {
        NodeLabel nodeLabel = new NodeLabel(node);
        nodeLabel.getComponent().setFont(new Font(
                                                 labelFontName,
                                                 labelFontStyle,
                                                 labelFontSize
                                                 )
                                        );
        return nodeLabel;
    }

    private ViewNode createViewNode(ViewNode viewNode) {
        System.out.println("Inside createViewNode, got called on viewNode..\n");
        System.out.println(viewNode);
        if (viewNode.getEditMode() != EditMode.EDITING) {
            return viewNode;
        }
        NodeTextField newTextField = new NodeTextField(viewNode);
        System.out.printf("Inside Page.createViewNode, about to setFont on the newTextField, using labelFontName %s, labelFontStyle %s, labelFontSize %d\n", labelFontName, labelFontStyle, labelFontSize);
        newTextField.getComponent().setFont(new Font(labelFontName, labelFontStyle, labelFontSize));
        return newTextField;
    }

    public void setControllerInterface(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }

    public void whenShown() {
        System.out.println("Inside Page.java, the whenShown function, GOT CALLEEDDD!!!\n");
        if (textField != null) {
            textField.getComponent().requestFocusInWindow();
        }
    }
    public void cleanUp() {
        if (editable != null) {
            editable.getComponent().setEnabled(false);
        }
    }

    public void printDebug() {
        TreePrinter.printAsHTML(rootViewNode);
    }
}
