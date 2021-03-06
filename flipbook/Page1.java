import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class Page1 extends JFrame {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Page1 page1 = new Page1();
                page1.setSize(400, 300);
                page1.setVisible(true);
                page1.printSizes();
            }
        });
    }
    
    
    public Page1() {
        setTitle("Page 1");
        initComponents();
    }


    public void printSizes() {
        int x = jLabel3.getWidth() + jLabel4.getWidth() + jLabel5.getWidth();
        int y = jLabel3.getHeight();
        y = Math.max(y, jLabel4.getHeight());
        y = Math.max(y, jLabel5.getHeight());
        System.out.printf("The sizes are: %d X %d%n", x, y);
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        Font labelFont = jLabel1.getFont();
        int labelFontStyle = labelFont.getStyle();
        int labelFontSize = labelFont.getSize();
        String labelFontName = labelFont.getName();
        jLabel1.setText("5");
        jLabel1.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        System.out.println("The current FONT for jLabel1 is:\n");
        System.out.println(jLabel1.getFont());
        jLabel2 = new JLabel();
        jLabel2.setText("+");
        jLabel2.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jLabel2.setForeground(new Color(200, 0, 255));
        jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel3 = new JLabel();
        jLabel3.setText("3");
        jLabel3.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jLabel4 = new JLabel();
        jLabel4.setText("*");
        jLabel4.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jLabel4.setForeground(new Color(200, 0, 255));
        jLabel4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel5 = new JLabel();
        jLabel5.setText("2");
        jLabel5.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
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
            .addComponent(jLabel3)
            .addComponent(jLabel4)
            .addComponent(jLabel5);
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
