import javax.swing.JPanel;
import java.awt.Frame;

public class Page extends JPanel {
    protected Runnable forwardHandler;
    protected Runnable backwardHandler;

    public void setForwardHandler(Runnable runnable) {
        forwardHandler = runnable;
    }

    public void setBackwardHandler(Runnable runnable) {
        backwardHandler = runnable;
    }
    public void whenShown() {}
    public void cleanUp() {}
    public int getTextFieldWidth() {
        return -1; // To be overridden;
    }
    public void createModal(Frame parentFrame) {
    }
}
