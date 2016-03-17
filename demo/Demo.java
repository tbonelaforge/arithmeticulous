import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Demo extends JFrame {
    private Page page1;
    private Page page2;
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
        page1 = initializeNewPage(new Page1());
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
            page2 = initializeNewPage(new Page2(page1.getTextFieldWidth()));
            show(page2, "Page2");
            currentPage = 2;
            break;
        default:
            System.out.println("Inside nextPage, in the default case.\n");
        }
    }

    private void previousPage() {
        switch (currentPage) {
        case 2:
            page2.cleanUp();
            page1 = initializeNewPage(new Page1());
            show(page1, "Page1");
            currentPage = 1;
            break;
        default:
            System.out.println("Inside previousPage, nothing to go back to!\n");
        }
    }

    private Page initializeNewPage(Page page) {
        page.setForwardHandler(new Runnable() {
            public void run() {
                nextPage();
            }
        });
        page.setBackwardHandler(new Runnable() {
            public void run() {
                previousPage();
            }
        });
        return page;
    }
}
