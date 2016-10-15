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

import controller.Controller;
import model.Node;
import model.Natural;
import model.Operator;

public class ArithmeticApp extends JFrame {
    
    private JButton startButton;
    private JButton quitButton;

    public ArithmeticApp() {
        initComponents();
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

    private Node makeExpression() {
        Node node1 = new Natural(2);
        Node node3 = new Natural(3);
        Node node2 = new Operator("*");
        node2.setLeftChild(node1);
        node2.setRightChild(node3);
        Node node4 = new Natural(5);
        Node node5 = new Operator("+");
        node5.setLeftChild(node4);
        node5.setRightChild(node2);
        return node5;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Inside ArithmeticApp main run method...\n");
                ArithmeticApp arithmeticApp = new ArithmeticApp();
                arithmeticApp.setVisible(true);
            }
        });
    }
}
