package views;

import models.DriverModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

/**
 * Displays information about their
 * current position in the game
 *
 * @author Scorpion
 */

public class InfoView extends ImagePanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private DriverModel model;
    private JLabel round;
    private JLabel lives;
    private JLabel gold;
    private JLabel talentPoints;
    private ImagePanel talentPointIcon;

    /**
     * Constructor for the info view. Contains all of the users data such as lives, round, gold, and talent points.
     */

    public InfoView() {

        super(DriverView.getImage("Black Background.jpg", 200, 80), 0, 0);


        this.setLayout(new GridLayout(2, 1));

        round = new JLabel("0");
        lives = new JLabel("0");
        gold = new JLabel("0");
        talentPoints = new JLabel("0");

        round.setFont(new Font("Serif", Font.BOLD, 18));
        round.setForeground(Color.LIGHT_GRAY);
        lives.setFont(new Font("Serif", Font.BOLD, 18));
        lives.setForeground(Color.LIGHT_GRAY);
        gold.setFont(new Font("Serif", Font.BOLD, 18));
        gold.setForeground(Color.LIGHT_GRAY);
        talentPoints.setFont(new Font("Serif", Font.BOLD, 18));
        talentPoints.setForeground(Color.LIGHT_GRAY);

        JPanel roundTalentPointPanel = new JPanel();
        JPanel healthGoldPanel = new JPanel();

        JPanel roundPanel = new JPanel(new BorderLayout(1, 2));
        JPanel talentPointPanel = new JPanel(new BorderLayout(1, 2));
        JPanel healthPanel = new JPanel(new BorderLayout(1, 2));
        JPanel goldPanel = new JPanel(new BorderLayout(1, 2));
        JPanel fillerSpace1 = new JPanel();
        JPanel fillerSpace2 = new JPanel();

        ImagePanel roundIcon = new ImagePanel(DriverView.getImage("Round Icon.png", 20, 20), 0, 7);
        this.talentPointIcon = new ImagePanel(DriverView.getImage("Talent Point Icon.png", 20, 20), 0, 7);
        ImagePanel healthIcon = new ImagePanel(DriverView.getImage("Health Icon.png", 20, 20), 0, 7);
        ImagePanel goldIcon = new ImagePanel(DriverView.getImage("Gold Coin Icon.png", 20, 20), 0, 7);

        roundTalentPointPanel.setOpaque(false);
        healthGoldPanel.setOpaque(false);
        roundPanel.setOpaque(false);
        talentPointPanel.setOpaque(false);
        healthPanel.setOpaque(false);
        goldPanel.setOpaque(false);
        fillerSpace1.setOpaque(false);
        fillerSpace2.setOpaque(false);
        roundIcon.setOpaque(false);
        talentPointIcon.setOpaque(false);
        healthIcon.setOpaque(false);
        goldIcon.setOpaque(false);

        roundIcon.setPreferredSize(new Dimension(30, 30));
        talentPointIcon.setPreferredSize(new Dimension(30, 30));
        healthIcon.setPreferredSize(new Dimension(30, 30));
        goldIcon.setPreferredSize(new Dimension(30, 30));
        fillerSpace1.setPreferredSize(new Dimension(20, 30));
        fillerSpace2.setPreferredSize(new Dimension(30, 30));

        roundPanel.add(roundIcon, BorderLayout.WEST);
        roundPanel.add(round, BorderLayout.CENTER);
        talentPointPanel.add(talentPointIcon, BorderLayout.WEST);
        talentPointPanel.add(talentPoints, BorderLayout.CENTER);
        healthPanel.add(healthIcon, BorderLayout.WEST);
        healthPanel.add(lives, BorderLayout.CENTER);
        goldPanel.add(goldIcon, BorderLayout.WEST);
        goldPanel.add(gold, BorderLayout.CENTER);

        roundTalentPointPanel.add(roundPanel);
        roundTalentPointPanel.add(fillerSpace1);
        roundTalentPointPanel.add(talentPointPanel);
        healthGoldPanel.add(healthPanel);
        healthGoldPanel.add(fillerSpace2);
        healthGoldPanel.add(goldPanel);

        this.add(roundTalentPointPanel);
        this.add(healthGoldPanel);
    }

    /**
     * Sets the values of the round, lives, gold, and talent points.
     */

    public void setText() {

        gold.setText("" + model.getMoney());
        lives.setText("" + model.getLives());
        round.setText("Round: " + model.getLevel());
        talentPoints.setText("" + model.getTalentPoints());

    }

    /**
     * Setter for the model.
     *
     * @param model
     */

    public void setModel(DriverModel model) {
        this.model = model;
        this.model.addActionListener(this);
    }

    /**
     * Paints the background of the view.
     */

    public void paintComponent(final Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(image, 0, 0, this);
        g2.setPaint(Color.LIGHT_GRAY);
        g2.fill(new Rectangle2D.Double(0, 0, 200, 3));
        g2.fill(new Rectangle2D.Double(0, 40, 200, 3));
        g2.fill(new Rectangle2D.Double(0, 77, 200, 3));
        g2.fill(new Rectangle2D.Double(0, 0, 3, 80));
        g2.fill(new Rectangle2D.Double(191, 0, 3, 80));
        this.talentPointIcon.image = DriverView.getImage("Talent Point Icon.png", 20, 20);

        this.talentPointIcon.repaint();
    }

    /**
     * Updates the current values of round, lives, gold, and talent points as different actions take place.
     */

    public void actionPerformed(ActionEvent e) {
        setText();
    }

}
