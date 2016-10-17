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
    private long t0;
    private long t1;
    private long totalTiming;
    private int howManyExercisesCompleted;
    private int howManyOperationsCompleted;
    private int operationCount;
    private double averageExerciseTiming;
    private double averageOperationTiming;

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
        Node expression;
        expression = NodeGenerator.generateEasyExpression();
        //expression = NodeGenerator.generateMediumExpression();
        //expression = NodeGenerator.generateEasyOrMediumExpression();
        //expression = NodeGenerator.generateEasyOrMediumOrHardExpression();
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

    public void startTiming(int operationCount) {
        this.operationCount = operationCount;
        t0 = System.currentTimeMillis();
    }

    public void stopTiming() {
        howManyExercisesCompleted += 1;
        howManyOperationsCompleted += operationCount;
        t1 = System.currentTimeMillis();
        long delta = t1 - t0;
        totalTiming += delta;
        System.out.printf("THAT EXERCISE HAD %d OPERATIONS AND TOOK:%n", operationCount);
        System.out.printf("%d milliseconds%n%n", delta);
        updateAverageExerciseTiming();
        updateAverageOperationTiming();
        System.out.println("THE AVERAGE TIMING PER EXERCISE IS NOW:");
        System.out.printf("%f seconds%n%n", averageExerciseTiming / 1000);
        System.out.println("THE AVERAGE TIMING PER OPERATION IS NOW:");
        System.out.printf("%f seconds%n%n", averageOperationTiming / 1000);
        System.out.printf("OPERATION RATE: %f OPS/second%n", 1000 / averageOperationTiming);
    }
    
    private void updateAverageExerciseTiming() {
        averageExerciseTiming = 1.0 * totalTiming / howManyExercisesCompleted;
    }

    private void updateAverageOperationTiming() {
        averageOperationTiming = 1.0 * totalTiming / howManyOperationsCompleted;
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
                startTiming(expression.countOperators());
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
