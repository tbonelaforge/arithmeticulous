import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Demo extends JFrame {
    private Page1 page1;
    private Page2 page2;
    private Container contentPane;
    private int currentPage;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Demo demo = new Demo();
                demo.initialize();
            }
        });
    }

    private void initialize() {
        contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        page1 = new Page1();
        page1.initMouseListener(new Runnable() {
            public void run() {
                nextPage();
            }
        });
        show(page1, "Page 1");
        currentPage = 1;
    }

    private void show(Page page, String title) {
        contentPane.removeAll();
        contentPane.add(page);
        contentPane.revalidate();
        contentPane.repaint();
        setTitle(title);
        setVisible(true);
        page.whenShown();
    }

    private void nextPage() {
        switch (currentPage) {
        case 1:
            page1.cleanUp();
            page2 = new Page2(page1.getTextFieldWidth());
            show(page2, "Page2");
            break;
        default:
            System.out.println("Inside nextPage, in the default case.\n");
        }
    }
}
