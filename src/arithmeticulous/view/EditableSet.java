package arithmeticulous.view;

import arithmeticulous.controller.ControllerInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditableSet {
    private ViewNode easyEditable;
    private ViewNode hardEditable;

    private static Color easyColor = new Color(20, 255, 20);
    private static Color hardColor = new Color(200, 0, 255);
   
    public int count() {
        int result = 0;
        if (easyEditable != null) {
            result += 1;
        }
        if (hardEditable != null) {
            result += 1;
        }
        return result;
    }

    public void newEasiest(ViewNode viewNode) {
        hardEditable = easyEditable;
        easyEditable = viewNode;
    }

    public void prepareForEditing(ControllerInterface controllerInterface) {
        if (easyEditable != null) {
            easyEditable.getComponent().setForeground(easyColor);
            makeEditable(easyEditable, controllerInterface);
        }
        if (hardEditable != null) {
            hardEditable.getComponent().setForeground(hardColor);
            makeEditable(hardEditable, controllerInterface);
        }
    }

    public void cleanUp() {
        if (easyEditable != null) {
            easyEditable.getComponent().setEnabled(false);
        }
        if (hardEditable != null) {
            hardEditable.getComponent().setEnabled(false);
        }
    }

    private void makeEditable(ViewNode viewNode, ControllerInterface controllerInterface) {
        viewNode.setEditMode(EditMode.EDITABLE);
        viewNode.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewNode.getComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (controllerInterface != null) {
                    controllerInterface.edit(viewNode);
                } else {
                    System.out.println("Inside the click handler for viewNode, the provided controllerInterface was null!\n");
                }
            }
        });
    }
}
