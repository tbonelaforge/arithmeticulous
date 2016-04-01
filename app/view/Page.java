package view;

import model.Node;
import model.Operator;
//import model.EditMode;

import interfaces.ControllerInterface;
import interfaces.ViewNode;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Page extends JPanel {
    //    private int textFieldWidth;
    private Font labelFont;
    private int labelFontSize;
    private int labelFontStyle;
    private String labelFontName;
    private GroupLayout groupLayout;
    private SequentialGroup horizontalGroup;
    private ParallelGroup verticalGroup;
    //    private NodeEditor editHandler;
    //    private NodeReplacer correctHandler;
    //    private NodeEditor incorrectHandler;
    private ControllerInterface controllerInterface;
    //    private NodeLabel rootNodeLabel;
    private ViewNode rootViewNode;
    private NodeLabel editable;
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
        super();
        //        initialize(page.rootNodeLabel);
        initialize(page.rootViewNode);
    }

    public void initialize(Node rootNode) {
        initializeLayout();
        /*
        groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(false);
        groupLayout.setAutoCreateContainerGaps(false);
        horizontalGroup = groupLayout.createSequentialGroup();
        groupLayout.setHorizontalGroup(horizontalGroup);
        verticalGroup = groupLayout.createParallelGroup();
        groupLayout.setVerticalGroup(verticalGroup);
        */
        //        rootNodeLabel = traverse(rootNode, 1);
        rootViewNode = traverse(rootNode, 1);
    }
    
    //    private void initialize(NodeLabel rootNodeLabel) {
    private void initialize(ViewNode oldRootViewNode) {
        initializeLayout();
        //        rootNodeLabel = traverse(rootNodeLabel);
        rootViewNode = traverse(oldRootViewNode);
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

    private NodeLabel traverse(Node node, int level) {
        //        NodeLabel editable;

        // In-order traversal for horizontal ordering.
        if (node == null) {
            return null;
        }
        NodeLabel leftNodeLabel = traverse(node.getLeftChild(), level + 1);
        NodeLabel thisNodeLabel = createNodeLabel(node);
        horizontalGroup.addComponent(thisNodeLabel);
        verticalGroup.addComponent(thisNodeLabel);
        if (node instanceof Operator) {
            if (level > editableLevel) {
                editable = thisNodeLabel;
            }
        }
        NodeLabel rightNodeLabel = traverse(node.getRightChild(), level + 1);
        thisNodeLabel.setLeftChild(leftNodeLabel);
        thisNodeLabel.setRightChild(rightNodeLabel);
        if (level == 1) {
            editable.setEditMode(EditMode.EDITABLE);
            editable.setForeground(clickableColor);
            editable.setCursor(new Cursor(Cursor.HAND_CURSOR));
            editable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if (controllerInterface != null) {
                        NodeLabel source = (NodeLabel) mouseEvent.getSource();
                        controllerInterface.edit(source);
                    }
                }
            });
        }
        return thisNodeLabel;
    }

    //    private NodeLabel traverse(NodeLabel nodeLabel, int level) {
    private ViewNode traverse(ViewNode viewNode) {

        // In-order traversal for horizontal ordering.
        if (viewNode == null) {
            return null;
        }
        ViewNode thisViewNode = createViewNode(viewNode);
        if (thisViewNode instanceof NodeTextField) {
            textField = (NodeTextField) thisViewNode;
            horizontalGroup.addComponent((Component) thisViewNode);
            verticalGroup.addComponent((Component) thisViewNode);
            return thisViewNode;
        }
        ViewNode leftViewNode = traverse(viewNode.getLeftChild());
        horizontalGroup.addComponent((Component) thisViewNode);
        verticalGroup.addComponent((Component) thisViewNode);
        ViewNode rightViewNode = traverse(viewNode.getRightChild());
        thisViewNode.setLeftChild(leftViewNode);
        thisViewNode.setRightChild(rightViewNode);
        return thisViewNode;
    }


    private NodeLabel createNodeLabel(Node node) {
        System.out.println("Inside createComponent, got called on node..\n");
        NodeLabel nodeLabel = new NodeLabel(node);
        //        jLabel.setFont(new Font(
        nodeLabel.setFont(new Font(
                                labelFontName,
                                labelFontStyle,
                                labelFontSize
                                )
                       );
        /*
        if (node.getEditMode() == EditMode.EDITABLE) {
            //            jLabel.setForeground(new Color(200, 0, 255));
            nodeLabel.setForeground(new Color(200, 0, 255));
            //            jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            nodeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //            jLabel.addMouseListener(new MouseAdapter() {
            nodeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    System.out.println("Inside mouseClicked function, about to do something!!!\n");
                    NodeLabel source = (NodeLabel) mouseEvent.getSource();
                    System.out.println("The source of the event is:\n");
                    System.out.println(source);
                    if (controllerInterface != null) {
                        System.out.println("ABout to call the editHandler!\n");
                        controllerInterface.edit(source);
                    }
                }
            });
        }
        */
        return nodeLabel;
    }

    private ViewNode createViewNode(ViewNode viewNode) {
        System.out.println("Inside createViewNode, got called on viewNode..\n");
        System.out.println(viewNode);
        if (viewNode.getEditMode() != EditMode.EDITING) {
            return viewNode;
        }
        NodeTextField newTextField = new NodeTextField(viewNode);
        newTextField.setFont(new Font(labelFontName, labelFontStyle, labelFontSize));
        return newTextField;
    }


    /*
    public void setEditHandler(NodeEditor nodeEditor) {
        editHandler = nodeEditor;
    }

    public void setCorrectHandler(NodeReplacer nodeReplacer) {
        correctHandler = nodeReplacer;
    }

    public void setIncorrectHandler(NodeEditor nodeEditor) {
        incorrectHandler = nodeEditor;
    }
    */
    public void setControllerInterface(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }

    public void whenShown() {
        System.out.println("Inside Page.java, the whenShown function, GOT CALLEEDDD!!!\n");
        if (textField != null) {
            textField.requestFocusInWindow();
        }
    }
    public void cleanUp() {}
    /*
    public int getTextFieldWidth() {
        return -1; // To be overridden;
    }
    */

    public void printDebug() {
        rootViewNode.printAsHTML();
    }

}
