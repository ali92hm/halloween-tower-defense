package com.halloween_tower_defense.views;

import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.utilities.TDButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonView extends ImagePanel implements ActionListener {

  private static final long serialVersionUID = 1L;
  private GameModel model;
  private GameView view;
  private final TDButton startButton;
  private final JButton talentTreeButton;
  private final TDButton homeButton;

  /**
   * Constructor for the button view. Contains the start, talent tree, and home buttons.
   */

  public ButtonView() {
    super(GameView.getImage("Black Background.jpg", 200, 105), 0, 0);
    this.setLayout(new BorderLayout());

    JPanel top = new JPanel();
    JPanel bottom = new JPanel();

    this.setOpaque(false);
    top.setOpaque(false);
    bottom.setOpaque(false);

    JPanel jptmp = new JPanel();
    jptmp.setOpaque(false);
    talentTreeButton = new TDButton(GameView.getImage("TalentTreeButton.png", 110, 30));
    homeButton = new TDButton(GameView.getImage("HomeButton.png", 30, 30));
    talentTreeButton.setPreferredSize(new Dimension(110, 30));
    homeButton.setPreferredSize(new Dimension(30, 30));
    homeButton.setToolTipText("<html>" + "Home" + "</html>");
    top.add(talentTreeButton);
    top.add(jptmp);
    top.add(homeButton);
    talentTreeButton.setEnabled(false);

    startButton = new TDButton(GameView.getImage("StartButton.png", 165, 50),
        GameView.getImage("2xButton.png", 165, 50),
        GameView.getImage("1xButton.png", 165, 50));
    startButton.setPreferredSize(new Dimension(165, 50));
    bottom.add(startButton);
    startButton.setEnabled(false);

    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER);

    this.setPreferredSize(new Dimension(194, 102));
    this.setMaximumSize(this.getPreferredSize());
    this.setMinimumSize(this.getPreferredSize());
  }

  /**
   * sets the model
   *
   * @param model
   * @param view
   */

  public void setModel(final GameModel model, final GameView view) {
    this.model = model;
    this.model.addActionListener(this);
    this.view = view;
  }

  /**
   * Getter for the talent tree button.
   *
   * @return
   */
  public JButton getTalentTreeButton() {
    return talentTreeButton;
  }

  /**
   * Getter for the home button.
   *
   * @return
   */
  public JButton getHomeButton() {
    return this.homeButton;
  }

  /**
   * Getter for the start button.
   *
   * @return
   */
  public TDButton getStartButton() {
    return this.startButton;
  }

  /**
   * updates the screen when the model triggers it
   */

  public void actionPerformed(ActionEvent e) {
    if (this.model.getStartButtonState() == 0) {
      this.startButton.setImage1();
    } else if (this.model.getStartButtonState() == 1) {
      this.startButton.setImage2();
    } else if (this.model.getStartButtonState() == 2) {
      this.startButton.setImage3();
    }
    this.repaint();
    if (this.view.getMapView() != null) {
      this.view.getMapView().repaint();
    }
  }
}
