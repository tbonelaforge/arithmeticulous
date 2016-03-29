package view;

import model.Node;
import model.EditMode;

import interfaces.NodeEditor;
import interfaces.NodeReplacer;

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
    private int textFieldWidth;
    private Font labelFont;
    private int labelFontSize;
    private int labelFontStyle;
    private String labelFontName;
    private GroupLayout groupLayout;
    private SequentialGroup horizontalGroup;
    private ParallelGroup verticalGroup;
    private NodeEditor editHandler;
    private NodeReplacer correctHandler;
    private NodeEditor incorrectHandler;

    public Page() {
        JLabel temp = new JLabel();
        Font exampleFont = temp.getFont();
        labelFontStyle = exampleFont.getStyle();
        labelFontSize = 2 * exampleFont.getSize();
        labelFontName = exampleFont.getName();
    }

    public void initialize(Node node) {
        groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(false);
        groupLayout.setAutoCreateContainerGaps(false);
        horizontalGroup = groupLayout.createSequentialGroup();
        groupLayout.setHorizontalGroup(horizontalGroup);
        verticalGroup = groupLayout.createParallelGroup();
        groupLayout.setVerticalGroup(verticalGroup);
        traverse(node);
    }

    private void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.getLeftChild());
        Component component = createComponent(node);
        horizontalGroup.addComponent(component);
        verticalGroup.addComponent(component);
        traverse(node.getRightChild());
    }

    private Component createComponent(Node node) {
        System.out.println("Inside createComponent, got called on node..\n");
        NodeLabel nodeLabel = new NodeLabel(node);
        //        jLabel.setFont(new Font(
        nodeLabel.setFont(new Font(
                                labelFontName,
                                labelFontStyle,
                                labelFontSize
                                )
                       );
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
                    Node sourceNode = source.getNode();
                    System.out.println("The sourceNode is:\n");
                    System.out.println(sourceNode);
                    if (editHandler != null) {
                        System.out.println("ABout to call the editHandler!\n");
                        System.out.println("With sourceNode:\n");
                        System.out.println(sourceNode);
                        editHandler.edit(sourceNode);
                    }
                }
            });
        }
        return nodeLabel;
    }

    public void setEditHandler(NodeEditor nodeEditor) {
        editHandler = nodeEditor;
    }

    public void setCorrectHandler(NodeReplacer nodeReplacer) {
        correctHandler = nodeReplacer;
    }

    public void setIncorrectHandler(NodeEditor nodeEditor) {
        incorrectHandler = nodeEditor;
    }
    public void whenShown() {
        System.out.println("Inside Page.java, the whenShown function, GOT CALLEEDDD!!!\n");
    }
    public void cleanUp() {}
    public int getTextFieldWidth() {
        return -1; // To be overridden;
    }
}
