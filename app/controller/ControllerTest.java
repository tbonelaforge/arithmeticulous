package controller;

import model.Node;
import model.Natural;
import model.Operator;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class ControllerTest {
    public static void main(String[] args) {
        Node node1 = new Natural(2);
        Node node3 = new Natural(3);
        Node node2 = new Operator("*");
        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        Node node4 = new Natural(5);
        Node node5 = new Operator("+");
        node5.setLeftChild(node4);
        node5.setRightChild(node2);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame testFrame = new JFrame();
                testFrame.setSize(400, 300);
                testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Controller controller = new Controller(testFrame);
                controller.initialize(node5);
            }
        });
    }
}
