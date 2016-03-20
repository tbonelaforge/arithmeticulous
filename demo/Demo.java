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
    private Page page3;
    private Page page4;
    private Page page5;
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
            page2 = initializeNewPage(new Page2(page1.getTextFieldWidth()));
            page1.cleanUp();
            show(page2, "Page2");
            currentPage = 2;
            break;
        case 2:
            page2.cleanUp();
            page3 = initializeNewPage(new Page3());
            show(page3, "Page3");
            currentPage = 3;
            break;
        case 3:
            page4 = initializeNewPage(new Page4(page3.getTextFieldWidth()));
            page3.cleanUp();
            show(page4, "Page4");
            currentPage = 4;
            break;
        case 4:
            page5 = initializeNewPage(new Page5());
            page4.cleanUp();
            show(page5, "Page5");
            currentPage = 5;
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
        case 4:
            page4.cleanUp();
            page3 = initializeNewPage(new Page3());
            show(page3, "Page3");
            currentPage = 3;
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
