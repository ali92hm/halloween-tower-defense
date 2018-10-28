package views;



import towers.Tower;

import javax.swing.*;

public class TowerBuyButton extends JToggleButton {
    private Class<? extends Tower> towerType;
    private String description;
    private String iconPath;
    public static final int ICON_SIZE = 40;

    public TowerBuyButton(Class<? extends Tower> towerType, String description, String iconPath){
        if(towerType == null)
            throw new IllegalArgumentException("Tower type cannot be null");

        if(description == null)
            throw new IllegalArgumentException("Description cannot be null");

        if(iconPath == null)
            throw new IllegalArgumentException("Icon path cannot be null");

        this.towerType = towerType;
        this.description = description;
        this.iconPath = iconPath;

        this.setName(towerType.getSimpleName());
        this.setIcon(new ImageIcon(DriverView.getImage(iconPath, ICON_SIZE, ICON_SIZE)));
    }

    public Class<? extends Tower> getTowerClass(){
        return this.towerType;
    }
}
