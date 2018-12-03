package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * pops up to tell the user about a new tower the unlocked
 *
 * @author Scorpion
 */

public class Alert extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 250;
    private final int height = 200;

    public Alert(final JFrame mainFrame, final BufferedImage image, final String title, final String... strings) {
        super(mainFrame, "", Dialog.ModalityType.DOCUMENT_MODAL);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImagePanel centralPanel = new ImagePanel(DriverView.getImage("Alert Background.jpg", width, height), 0, 0);
        centralPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 15));
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(new CloseListener());
        JLabel pictureLabel = new JLabel(new ImageIcon(image));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        JPanel closePanel = new JPanel();
        JPanel infoContainer = new JPanel(new BorderLayout());

        for (String str : strings) {
            JLabel tmp = new JLabel(str);
            tmp.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(tmp);
        }

        closePanel.add(closeButton);

        infoContainer.add(infoPanel, BorderLayout.CENTER);
        infoContainer.add(closePanel, BorderLayout.SOUTH);

        centralPanel.add(titleLabel, BorderLayout.NORTH);
        centralPanel.add(pictureLabel, BorderLayout.CENTER);
        centralPanel.add(infoContainer, BorderLayout.SOUTH);

        infoContainer.setOpaque(false);
        infoPanel.setOpaque(false);
        centralPanel.setOpaque(false);
        closePanel.setOpaque(false);

        this.getContentPane().add(centralPanel, BorderLayout.CENTER);
        this.setSize(this.width, this.height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * Listens for the close button to be pushed
     *
     * @author Scorpion
     */

    private class CloseListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
        }
    }

}
