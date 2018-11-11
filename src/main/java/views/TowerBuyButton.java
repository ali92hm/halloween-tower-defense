package views;



import towers.Tower;

import javax.swing.*;

public class TowerBuyButton extends JToggleButton {
    private String description;
    public static final int ICON_SIZE = 40;

    public TowerBuyButton(String name, String description, String iconPath){
        if(description == null)
            throw new IllegalArgumentException("Description cannot be null");

        if(iconPath == null)
            throw new IllegalArgumentException("Icon path cannot be null");

        this.description = description;

        this.setName(name);
        this.setIcon(new ImageIcon(DriverView.getImage(iconPath, ICON_SIZE, ICON_SIZE)));
    }
}
