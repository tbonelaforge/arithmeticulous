import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.JTextField;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import javax.swing.text.DefaultCaret;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class Page2 extends Page implements FocusListener, ActionListener {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private int textFieldWidth;


    public Page2(int textFieldWidth) {
        this.textFieldWidth = textFieldWidth;
        jLabel1 = new JLabel();
        Font labelFont = jLabel1.getFont();
        int labelFontStyle = labelFont.getStyle();
        int labelFontSize = labelFont.getSize();
        String labelFontName = labelFont.getName();
        jLabel1.setText("5");
        jLabel1.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jLabel2 = new JLabel();
        jLabel2.setText("+");
        jLabel2.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jTextField1 = new JTextField();
        jTextField1.setCaret(new DefaultCaret());
        jTextField1.getCaret().setBlinkRate(500);
        jTextField1.setBorder(BorderFactory.createEmptyBorder());
        jTextField1.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jTextField1.addFocusListener(this);
        jTextField1.addActionListener(this);
        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(false);
        groupLayout.setAutoCreateContainerGaps(false);
        setHorizontalLayout(groupLayout);
        setVerticalLayout(groupLayout);
    }

    public void whenShown() {
        jTextField1.requestFocusInWindow();
    }

    private void setHorizontalLayout(GroupLayout groupLayout) {
        SequentialGroup sGroup1 = groupLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
            .addComponent(
                          jTextField1,
                          textFieldWidth,
                          textFieldWidth,
                          textFieldWidth
                          );
        groupLayout.setHorizontalGroup(sGroup1);
    }

    private void setVerticalLayout(GroupLayout groupLayout) {
        ParallelGroup pGroup1 = groupLayout.createParallelGroup()
            .addComponent(jLabel1)
            .addComponent(jLabel2)
            .addComponent(jTextField1);
        groupLayout.setVerticalGroup(pGroup1);
    }

    public void focusGained(FocusEvent focusEvent) {
        jTextField1.setText("3*2");
        jTextField1.selectAll();
    }

    public void focusLost(FocusEvent focusEvent) {}

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Inside Page2.actionPerformed, got called\n");
        System.out.println("Inside Page2.actionPerformed, the value of the text is:\n");
        System.out.println(jTextField1.getText());
        if (jTextField1.getText().equals("6")) {
            System.out.println("CORRECT!!! (need to move to page 3)\n");
        } else {
            System.out.println("INCORRECT!!! (need to move back to page1)\n");
            if (backwardHandler != null) {
                backwardHandler.run();
            }
        }
    }
}
