import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

public class Page1 extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    public Page1() {
        setTitle("Page 1");
        initComponents();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Page1 page1 = new Page1();
                page1.setSize(400, 300);
                page1.setVisible(true);
            }
        });
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jLabel1.setText("5");
        jLabel2 = new JLabel();
        jLabel2.setText("+");
        jLabel3 = new JLabel();
        jLabel3.setText("3");
        jLabel4 = new JLabel();
        jLabel4.setText("*");
        jLabel5 = new JLabel();
        jLabel5.setText("2");
        Container contentPane = getContentPane();
        JPanel jPanel = new JPanel();
        //        GroupLayout groupLayout = new GroupLayout(contentPane);
        GroupLayout groupLayout = new GroupLayout(jPanel);
        jPanel.setLayout(groupLayout);
        setHorizontalLayout(groupLayout);
        setVerticalLayout(groupLayout);
        //        contentPane.setLayout(groupLayout);
        contentPane.setLayout(new GridBagLayout());
        contentPane.add(jPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setHorizontalLayout(GroupLayout groupLayout) {
        SequentialGroup sGroup1 = groupLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
            .addComponent(jLabel3)
            .addComponent(jLabel4)
            .addComponent(jLabel5)
            .addContainerGap();
        groupLayout.setHorizontalGroup(sGroup1);
    }

    private void setVerticalLayout(GroupLayout groupLayout) {
        ParallelGroup pGroup1 = groupLayout.createParallelGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
            .addComponent(jLabel3)
            .addComponent(jLabel4)
            .addComponent(jLabel5);
        groupLayout.setVerticalGroup(pGroup1);
    }
}
