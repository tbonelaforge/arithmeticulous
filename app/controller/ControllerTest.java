package controller;

import model.Node;
import model.Natural;
import model.Operator;
import model.TreePrinter;

import java.awt.EventQueue;

public class ControllerTest {
    public static void main(String[] args) {
        System.out.printf("Constructing a node:\n");
        Node node1 = new Natural(2);
        Node node3 = new Natural(3);
        Node node2 = new Operator("*");
        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        Node node4 = new Natural(5);
        Node node5 = new Operator("+");
        node5.setLeftChild(node4);
        node5.setRightChild(node2);
        System.out.println("Just constructed test node:<br />\n");
        TreePrinter.printAsHTML(node5);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Controller controller = new Controller();
                controller.initialize(node5);
            }
        });
    }
}
