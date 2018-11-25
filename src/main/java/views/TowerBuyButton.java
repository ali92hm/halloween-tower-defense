package views;


import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class TowerBuyButton extends JToggleButton {
    public static final int ICON_SIZE = 40;

    public TowerBuyButton(String name, String description, String iconPath) {
        if (description == null)
            throw new IllegalArgumentException("Description cannot be null");

        if (iconPath == null)
            throw new IllegalArgumentException("Icon path cannot be null");

        this.setName(name);
        this.setToolTipText(description);
        UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(new Color(76, 79, 83)));
        this.setIcon(new ImageIcon(DriverView.getImage(iconPath, ICON_SIZE, ICON_SIZE)));
    }
}
