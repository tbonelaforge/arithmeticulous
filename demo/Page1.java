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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Page1 extends Page {
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;

    public Page1() {
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
        jLabel3 = new JLabel();
        jLabel3.setText("3");
        jLabel3.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jLabel4 = new JLabel();
        jLabel4.setText("*");
        jLabel4.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        jLabel4.setForeground(new Color(200, 0, 255));
        jLabel4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (forwardHandler != null) {
                    forwardHandler.run();
                }
            }
        });
        jLabel5 = new JLabel();
        jLabel5.setText("2");
        jLabel5.setFont(new Font(labelFontName, labelFontStyle, 2 * labelFontSize));
        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(false);
        groupLayout.setAutoCreateContainerGaps(false);
        setHorizontalLayout(groupLayout);
        setVerticalLayout(groupLayout);
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

    @Override
    public int getTextFieldWidth() {
        return jLabel3.getWidth() + jLabel4.getWidth() + jLabel5.getWidth();
    }
    
    public void cleanUp() {
        jLabel4.setEnabled(false);
    }
}
