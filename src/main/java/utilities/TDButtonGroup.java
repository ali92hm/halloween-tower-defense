package utilities;

import views.TowerBuyButton;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;

/**
 * custom class for a tower defense button group
 *
 * @author Scorpion
 */

public class TDButtonGroup extends ButtonGroup {

    private static final long serialVersionUID = 1L;

    /**
     * returns which button is selected
     *
     * @return AbstractButton
     */

    public TowerBuyButton getSelectedButton() {
        Enumeration<AbstractButton> en = this.getElements();
        while (en.hasMoreElements()) {
            TowerBuyButton b = (TowerBuyButton) en.nextElement();
            if (b.isSelected()) {
                return b;
            }
        }
        return null;
    }

    /**
     * checks whether a button is in a button group
     *
     * @param b
     * @return boolean
     */

    public boolean isInGroup(final AbstractButton b) {
        Enumeration<AbstractButton> en = this.getElements();
        while (en.hasMoreElements()) {
            if (en.nextElement().equals(b)) {
                return true;
            }
        }
        return false;

    }

    /**
     * adds a mouse listener to all the buttons in the group
     *
     * @param ml
     */

    public void addMouseListenerToAll(MouseListener ml) {
        Enumeration<AbstractButton> en = this.getElements();
        while (en.hasMoreElements()) {
            en.nextElement().addMouseListener(ml);
        }
    }

    /**
     * adds a mouse motion listener to all the buttons in the group
     *
     * @param ml
     */

    public void addMouseMotionListenerToAll(MouseMotionListener l) {
        Enumeration<AbstractButton> en = this.getElements();
        while (en.hasMoreElements()) {
            en.nextElement().addMouseMotionListener(l);
        }
    }

    /**
     * adds a item listener to all the buttons in the group
     *
     * @param ml
     */

    public void addItemListenerToAll(ItemListener el) {
        Enumeration<AbstractButton> en = this.getElements();
        while (en.hasMoreElements()) {
            en.nextElement().addItemListener(el);
        }
    }

    /**
     * adds an action listener to all the buttons in the group
     *
     * @param ml
     */

    public void addActionListenerToAll(ActionListener l) {
        Enumeration<AbstractButton> en = this.getElements();
        while (en.hasMoreElements()) {
            en.nextElement().addActionListener(l);
        }
    }
}
