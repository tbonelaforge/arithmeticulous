package arithmeticulous;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import arithmeticulous.controller.Controller;
import arithmeticulous.model.Node;
import arithmeticulous.model.Natural;
import arithmeticulous.model.Operator;
import arithmeticulous.model.NodeGenerator;

public class ArithmeticApp extends JFrame {
    
    private JButton startButton;
    private JButton quitButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Inside ArithmeticApp main run method...\n");
                ArithmeticApp arithmeticApp = new ArithmeticApp();
                arithmeticApp.setVisible(true);
            }
        });
    }
    
    public ArithmeticApp() {
        initComponents();
    }

    public Node makeExpression() {
        Node expression = NodeGenerator.generateRandomExpression(2);
        /*
        Node node1 = new Natural(getRandomInt(2, 5));
        Node node3 = new Natural(getRandomInt(2, 5));
        Node node2 = new Operator("+");
        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        Node node4 = new Natural(getRandomInt(2, 5));
        Node node5 = new Operator("*");
        node5.setLeftChild(node4);
        node5.setRightChild(node2);
        expression = node5;
        */
        /*
        Node node1 = new Natural(getRandomInt(2, 5));
        Node node3 = new Natural(getRandomInt(2, 5));
        Node node2 = new Operator("*");
        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        Node node4 = new Natural(getRandomInt(1, 9));
        Node node5 = new Operator("+");
        node5.setLeftChild(node4);
        node5.setRightChild(node2);
        expression = node5;
        */
        return expression;
    }
    
    private void initComponents() {
        startButton = new JButton();
        quitButton = new JButton();
        setTitle("Arithmetic Game");
        startButton.setText("Start");
        quitButton.setText("Quit");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Controller controller = makeNewController();
                Node expression = makeExpression();
                controller.initialize(expression);
            }
        });
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        Container contentPane = getContentPane();
        GridBagLayout gridBagLayout = new GridBagLayout();
        contentPane.setLayout(gridBagLayout);
        JLabel label = new JLabel("Arithmetic Game");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(label, c);
        contentPane.add(startButton, c);
        contentPane.add(quitButton, c);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Controller makeNewController() {
        return new Controller(this);
    }

    private int getRandomInt(int lo, int hi) {
        int range = hi - lo;
        double randomInRange = (hi - lo + 1) * Math.random();
        double randomInInterval = lo + randomInRange;
        return (int) randomInInterval;
    }
}
