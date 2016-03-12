import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.GridBagLayout;

public class Page2 extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField jTextField1;
    
    public Page2() {
        setTitle("Page 2");
        initComponents();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Page2 page2 = new Page2();
                page2.setSize(400, 300);
                page2.setVisible(true);
                page2.printSizes();
            }
        });
    }

    public void printSizes() {
        int x = jTextField1.getWidth();
        int y = jTextField1.getHeight();
        System.out.printf("The sizes are: %d X %d%n", x, y);
        System.out.printf("The textfield has a border object:\n");
        System.out.println(jTextField1.getBorder());
    }
    
    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel1.setText("5");
        jLabel2 = new JLabel();
        jLabel2.setText("+");
        jTextField1 = new JTextField();
        jTextField1.setBorder(BorderFactory.createEmptyBorder());
        jTextField1.setText("3*2");
        Container contentPane = getContentPane();
        JPanel jPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(jPanel);
        jPanel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(false);
        groupLayout.setAutoCreateContainerGaps(false);
        setHorizontalLayout(groupLayout);
        setVerticalLayout(groupLayout);
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(jPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setHorizontalLayout(GroupLayout groupLayout) {
        SequentialGroup sGroup1 = groupLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
                        .addComponent(jTextField1, 22, 22, 22);
        groupLayout.setHorizontalGroup(sGroup1);
    }

    private void setVerticalLayout(GroupLayout groupLayout) {
        ParallelGroup pGroup1 = groupLayout.createParallelGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
                        .addComponent(jTextField1, 16, 16, 16);
        groupLayout.setVerticalGroup(pGroup1);
    }
    
}
