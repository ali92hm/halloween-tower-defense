package views;

import models.DriverModel;
import towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Pops up an alert for the user
 * when they try to start the game
 * with unspent talent points
 *
 * @author Scorpion
 */

public class TalentPointAlert extends JDialog {

    private static final long serialVersionUID = 1L;
    private final int width = 250;
    private final int height = 190;

    private DriverModel model;
    private DriverView view;

    /**
     * constructor for the TalentPointAlert
     *
     * @param model
     * @param view
     */

    public TalentPointAlert(final DriverModel model, final DriverView view) {
        super(view, "", Dialog.ModalityType.DOCUMENT_MODAL);
        this.model = model;
        this.view = view;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImagePanel centralPanel = new ImagePanel(DriverView.getImage("Alert Background.jpg", width, height), 0, 0);
        centralPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Warning!");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(Color.RED);
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");
        yesButton.addActionListener(new YesListener());
        noButton.addActionListener(new NoListener());
        JLabel pictureLabel = new JLabel(new ImageIcon(DriverView.getImage("Talent Point Icon.png", 30, 30)));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pictureLabel.setPreferredSize(new Dimension(30, 30));

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        JPanel closePanel = new JPanel();
        JPanel infoContainer = new JPanel(new BorderLayout());

        JLabel label1 = new JLabel("You have unspent talent points!");
        JLabel label2 = new JLabel("Are you sure you want to start");
        JLabel label3 = new JLabel("the next round?");

        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        infoPanel.add(label1);
        infoPanel.add(label2);
        infoPanel.add(label3);

        JPanel fillerPanel = new JPanel();
        fillerPanel.setOpaque(false);
        fillerPanel.setPreferredSize(new Dimension(30, 30));
        closePanel.add(noButton);
        closePanel.add(fillerPanel);
        closePanel.add(yesButton);

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
     * closes the window and starts the game
     * if the yes button is pressed
     *
     * @author Scorpion
     */

    private class YesListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            model.gameRuntimeLoop();
            model.setCancelConfirmOption(0);
            if (model.allTowers() != null) {
                for (Tower t : model.allTowers()) {
                    t.setShowRange(false);
                }
            }
            view.setTowerPanel();
            view.getMapView().repaint();
            view.getSidePanelView().getButtonView().getStartButton().setToggleImage();
            model.setStartButtonState(view.getSidePanelView().getButtonView().getStartButton().getButtonState());
            dispose();
        }
    }

    /**
     * closes the window without starting
     * the game if the no button is pressed
     *
     * @author Scorpion
     */

    private class NoListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
        }
    }
}
