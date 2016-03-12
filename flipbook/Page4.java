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

public class Page4 extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField jTextField1;
    
    public Page4() {
        setTitle("Page 4");
        initComponents();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Page4 page4 = new Page4();
                page4.setSize(400, 300);
                page4.setVisible(true);
                page4.printSizes();
            }
        });
    }

    public void printSizes() {
        /*
        int x = jTextField1.getWidth();
        int y = jTextField1.getHeight();
        System.out.printf("The sizes are: %d X %d%n", x, y);
        System.out.printf("The textfield has a border object:\n");
        System.out.println(jTextField1.getBorder());
        */
    }
    
    private void initComponents() {
        jTextField1 = new JTextField();
        jTextField1.setBorder(BorderFactory.createEmptyBorder());
        jTextField1.setText("5+6");
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
            .addComponent(jTextField1, 26, 26, 26);
        groupLayout.setHorizontalGroup(sGroup1);
    }

    private void setVerticalLayout(GroupLayout groupLayout) {
        ParallelGroup pGroup1 = groupLayout.createParallelGroup()
            .addComponent(jTextField1, 16, 16, 16);
        groupLayout.setVerticalGroup(pGroup1);
    }
    
}
