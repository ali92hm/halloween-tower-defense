package controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener to display a brief description of the game
 *
 * @author Scorpion
 */

public class InfoListener implements ActionListener {
    private String info =
            "A very spooky tower defense game with a " +
                    "questionably organized code base. Created by Jack Skellington";

    public void actionPerformed(ActionEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        JTextArea infoText = new JTextArea(info);
        JDialog infoDialogue = new JDialog();

        infoText.setLineWrap(true);
        infoText.setWrapStyleWord(true);

        infoDialogue.setSize(300, 100);
        int xpos, ypos;
        xpos = (screenSize.width - infoDialogue.getWidth()) / 2;
        ypos = (screenSize.height - infoDialogue.getHeight()) / 2;
        infoDialogue.setLocation(xpos, ypos);
        infoDialogue.setContentPane(infoText);
        infoDialogue.show();
    }

    public String getInfo() {
        return info;
    }
}
