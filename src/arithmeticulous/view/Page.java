package arithmeticulous.view;

import arithmeticulous.model.Node;
import arithmeticulous.model.Operator;
import arithmeticulous.model.TreePrinter;
import arithmeticulous.controller.ControllerInterface;
import arithmeticulous.view.EditableSet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Font defaultFont;
    private GroupLayout groupLayout;
    private SequentialGroup horizontalGroup;
    private ParallelGroup verticalGroup;
    private ControllerInterface controllerInterface;
    private ViewNode rootViewNode;
    private EditableSet editableSet;
    private int editableLevel;
    private NodeTextField textField;
    private JDialog modal;
    private boolean allCorrect;

    public Page(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
        JLabel temp = new JLabel();
        Font exampleFont = temp.getFont();
        int labelFontStyle = exampleFont.getStyle();
        int labelFontSize = 2 * exampleFont.getSize();
        String labelFontName = exampleFont.getName();
        defaultFont = new Font(labelFontName, labelFontStyle, labelFontSize);
    }

    public Page(ControllerInterface controllerInterface, Page page) {
        this(controllerInterface);
        initializeFromViewNode(page.rootViewNode);
    }

    public void initializeFromNode(Node rootNode) {
        initializeLayout();
        editableSet = new EditableSet();
        rootViewNode = traverseNode(rootNode, 1);
        if (editableSet.count() > 0) {
            editableSet.prepareForEditing(controllerInterface);
        } else {
            markAllCorrect();
        }
    }

    private void markAllCorrect() {
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
        if (node == null) {
            return null;
        }

        // Pre-order traversal for editability computation:
        // Find the two first, lowest level operators.
        ViewNode thisViewNode = (ViewNode) createNodeLabel(node);
        if (node instanceof Operator) {
            if (level > editableLevel) {
                editableSet.newEasiest(thisViewNode);
                editableLevel = level;
            }
        }

        // In-order traversal for horizontal display.
        JLabel leftParen = null;
        JLabel rightParen = null;
        leftParen = openParenthesis(node.getLeftChild(), node);
        ViewNode leftViewNode = traverseNode(node.getLeftChild(), level + 1);
        rightParen = closeParenthesis(node.getLeftChild(), node);
        if (leftViewNode != null) {
            leftViewNode.setLeftParen(leftParen);
            leftViewNode.setRightParen(rightParen);
        }

        horizontalGroup.addComponent(thisViewNode.getComponent());
        verticalGroup.addComponent(thisViewNode.getComponent());

        leftParen = openParenthesis(node.getRightChild(), node);
        ViewNode rightViewNode = traverseNode(node.getRightChild(), level + 1);
        rightParen = closeParenthesis(node.getRightChild(), node);
        if (rightViewNode != null) {
            rightViewNode.setLeftParen(leftParen);
            rightViewNode.setRightParen(rightParen);
        }

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
            int width = viewNode.computeWidth(ViewNode.NO_PARENS);
            textField = (NodeTextField) thisViewNode;
            horizontalGroup.addComponent(thisViewNode.getComponent(),
                                         width,
                                         width,
                                         width
                                         );
            verticalGroup.addComponent(thisViewNode.getComponent());
            return thisViewNode;
        }
        JLabel leftParen = null;
        JLabel rightParen = null;
        leftParen = openParenthesis(viewNode.getNode().getLeftChild(), viewNode.getNode());
        ViewNode leftViewNode = traverseViewNode(viewNode.getLeftChild());
        rightParen = closeParenthesis(viewNode.getNode().getLeftChild(), viewNode.getNode());
        if (leftViewNode != null) {
            leftViewNode.setLeftParen(leftParen);
            leftViewNode.setRightParen(rightParen);
        }

        horizontalGroup.addComponent(thisViewNode.getComponent());
        verticalGroup.addComponent(thisViewNode.getComponent());

        leftParen = openParenthesis(viewNode.getNode().getRightChild(), viewNode.getNode());
        ViewNode rightViewNode = traverseViewNode(viewNode.getRightChild());
        rightParen = closeParenthesis(viewNode.getNode().getRightChild(), viewNode.getNode());
        if (rightViewNode != null) {
            rightViewNode.setLeftParen(leftParen);
            rightViewNode.setRightParen(rightParen);
        }

        thisViewNode.setLeftChild(leftViewNode);
        thisViewNode.setRightChild(rightViewNode);
        return thisViewNode;
    }

    private JLabel openParenthesis(Node child, Node parent) {
        if (child != null && parent != null
            && child.getPrecedence() < parent.getPrecedence()) {
            JLabel label = new JLabel();
            label.setText("(");
            label.setFont(defaultFont);
            horizontalGroup.addComponent(label);
            verticalGroup.addComponent(label);
            return label;
        } else {
            return null;
        }
    }

    private JLabel closeParenthesis(Node child, Node parent) {
        if (child != null && parent != null
            && child.getPrecedence() < parent.getPrecedence()) {
            JLabel label = new JLabel();
            label.setText(")");
            label.setFont(defaultFont);
            horizontalGroup.addComponent(label);
            verticalGroup.addComponent(label);
            return label;
        } else {
            return null;
        }
    }

    private NodeLabel createNodeLabel(Node node) {
        NodeLabel nodeLabel = new NodeLabel(node);
        nodeLabel.getComponent().setFont(defaultFont);
        return nodeLabel;
    }

    private ViewNode createViewNode(ViewNode viewNode) {
        if (viewNode.getEditMode() != EditMode.EDITING) {
            return viewNode;
        }
        NodeTextField newTextField = new NodeTextField(viewNode);
        newTextField.getComponent().setFont(defaultFont);
        return newTextField;
    }

    public void whenShown() {
        if (textField != null) {
            textField.getComponent().requestFocusInWindow();
        }
    }
    public void cleanUp() {
        if (editableSet != null) {
            editableSet.cleanUp();
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
