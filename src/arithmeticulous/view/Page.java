package arithmeticulous.view;

import arithmeticulous.model.Node;
import arithmeticulous.model.Operator;
import arithmeticulous.model.TreePrinter;

import arithmeticulous.controller.ControllerInterface;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


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
    private JDialog modal;
    private boolean allCorrect;

    private static Color clickableColor = new Color(200, 0, 255);

    public Page(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
        JLabel temp = new JLabel();
        Font exampleFont = temp.getFont();
        labelFontStyle = exampleFont.getStyle();
        labelFontSize = 2 * exampleFont.getSize();
        labelFontName = exampleFont.getName();
    }

    public Page(ControllerInterface controllerInterface, Page page) {
        this(controllerInterface);
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
                    if (controllerInterface != null) {
                        controllerInterface.edit(editable);
                    }
                }
            });
        } else {
            markCorrect();
        }
    }

    private void markCorrect() {
        ImageIcon correctIcon = null;
        String path = "images/check.png";
        String description = "answer is correct";
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            correctIcon = new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
        }
        JLabel correctLabel = new JLabel(correctIcon);
        horizontalGroup.addComponent(correctLabel);
        verticalGroup.addComponent(correctLabel);
        allCorrect = true;
    }

    private void initializeFromViewNode(ViewNode oldRootViewNode) {
        initializeLayout();
        rootViewNode = traverseViewNode(oldRootViewNode);
        TreePrinter.printAsHTML(rootViewNode);
        if (textField != null) {
            textField.setOnSubmit(new Runnable() {
                public void run() {
                    String submitted = textField.getText();
                    Node expected = textField.getNode().evaluate();
                    if (submitted.equals(expected.getData())) { // Correct answer
                        ViewNode replacement = (ViewNode) createNodeLabel(expected);
                        if (controllerInterface != null) {
                            controllerInterface.replace(textField, replacement);
                        }
                    } else { // Incorrect answer
                        createModal(textField);
                        Point location = getLocationOnScreen();
                        System.out.printf("The location of the Page is: (%d, %d)%n", location.x, location.y);
                        System.out.printf("The height of the Page is: %d%n", getHeight());
                        modal.setLocation(location.x, location.y + getHeight());
                        modal.setSize(200, 100);
                        modal.setVisible(true);
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
        if (viewNode.getEditMode() != EditMode.EDITING) {
            return viewNode;
        }
        NodeTextField newTextField = new NodeTextField(viewNode);
        newTextField.getComponent().setFont(new Font(labelFontName, labelFontStyle, labelFontSize));
        return newTextField;
    }

    /*
    public void setControllerInterface(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }
    */

    public void whenShown() {
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

    public void createModal(ViewNode viewNode) {
        if (controllerInterface == null) {
            System.out.println("Page cannot create modal without a controller interface\n");
            return;
        }
        Frame parentFrame = controllerInterface.getFrame();
        modal = new JDialog(parentFrame, "Incorrect", true);
        modal.setLocationRelativeTo(parentFrame);
        System.out.println("Inside Page.createModal, about to getHint, passing in viewNode:\n");
        TreePrinter.printAsHTML(viewNode);
        System.out.println("Inside Page.createModal, the viewNode's node looks like:\n");
        TreePrinter.printAsHTML(viewNode.getNode());
        String hint = viewNode.getHint();
        JLabel modalLabel = new JLabel(hint);
        modalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JButton modalButton = new JButton("OK");
        modalButton.setHorizontalAlignment(SwingConstants.CENTER);
        modalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                modal.setVisible(false);
                controllerInterface.reset();
            }
        });
        Container modalContentPane = modal.getContentPane();
        modalContentPane.setLayout(new BorderLayout());
        modalContentPane.add(modalLabel, BorderLayout.NORTH);
        JPanel modalButtonPanel = new JPanel();
        modalButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        modalButtonPanel.add(modalButton);
        modalContentPane.add(modalButtonPanel, BorderLayout.SOUTH);
    }

    public boolean isAllCorrect() {
        return allCorrect;
    }
}
