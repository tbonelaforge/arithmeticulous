import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

public class Page3 extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Page3 page3 = new Page3();
                page3.setSize(400, 300);
                page3.setVisible(true);
                page3.printSizes();
            }
        });
    }
    
    
    public Page3() {
        setTitle("Page 3");
        initComponents();
    }


    public void printSizes() {
        int x = jLabel1.getWidth() + jLabel2.getWidth() + jLabel3.getWidth();
        int y = jLabel1.getHeight();
        y = Math.max(y, jLabel2.getHeight());
        y = Math.max(y, jLabel3.getHeight());
        System.out.printf("The sizes are: %d X %d%n", x, y);
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel1.setText("5");
        jLabel2 = new JLabel();
        jLabel2.setText("+");
        jLabel2.setForeground(new Color(125, 0, 255));
        jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel3 = new JLabel();
        jLabel3.setText("6");
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
            .addComponent(jLabel3);
        groupLayout.setHorizontalGroup(sGroup1);
    }

    private void setVerticalLayout(GroupLayout groupLayout) {
        ParallelGroup pGroup1 = groupLayout.createParallelGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
            .addComponent(jLabel3);
        groupLayout.setVerticalGroup(pGroup1);
    }
}
