package com.halloween_tower_defense.views;

import com.halloween_tower_defense.models.GameModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Driver for all of the view. The driver view itself extends JFrame and is the main panel for our game.
 *
 * @author Reggie
 */
public class GameView extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  private GameModel model;

  private static final String imageResourceFolder = "images";

  private boolean shownStartingTutorial = false;
  private boolean shownTalentTreeTutorial = false;
  private boolean shownBatTutorial = false;
  private boolean shownGiantPumpkinTutorial = false;

  private final String titleScreenImage = "HalloweenTD.jpg";
  private final String mainScreenImage = "halloween3.jpg";

  private final int windowWidth = 900;
  private final int windowHeight = 675;

  /*
   ***************
   * View Panels *
   ***************
   */

  private final TitleView titleView;
  private final MainView mainView;
  private final JPanel gameView;
  private final CardLayout windowCards;
  private JPanel mapTalentView;
  private CardLayout mapTalentCards;
  private MapView mapView;
  private TalentTreeView talentTreeView;
  private final SidePanelView sidePanelView;
  private final JMenuBar menuBar;
  private final JMenu fileMenu;
  private final JMenu viewMenu;
  private final JMenu helpMenu;
  private final JMenuItem jmiNewGame;
  private final JMenuItem jmiSave;
  private final JMenuItem jmiLoad;
  private final JMenuItem jmiSettings;
  private final JMenuItem jmiExit;
  private final JMenuItem jmiTalentTree;
  private final JMenuItem jmiTutorial;
  private final JMenuItem jmiInfo;


  /*
   ***************
   * Constructor *
   ***************
   */

  /**
   * Constructor for the Driver View.
   */

  public GameView(final GameModel model) {
    final BufferedImage titleImage = getImage(this.titleScreenImage,
        this.windowWidth, this.windowHeight);
    final BufferedImage mainImage = getImage(this.mainScreenImage,
        this.windowWidth, this.windowHeight);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        int confirmed = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to exit?", "User Confirmation",
            JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
          dispose();
          System.exit(0);
        }
      }
    });

    this.model = model;
    this.model.addActionListener(this);

    this.titleView = new TitleView(titleImage);
    this.mainView = new MainView(mainImage);
    this.gameView = new JPanel(new BorderLayout());
    this.sidePanelView = new SidePanelView(model, this);
    this.menuBar = new JMenuBar();

    //Creating the menu
    //File
    this.fileMenu = new JMenu("File");
    this.jmiNewGame = new JMenuItem("New Game");
    this.jmiSave = new JMenuItem("Save Game");
    this.jmiLoad = new JMenuItem("Load Game");
    this.jmiSettings = new JMenuItem("Settings");
    this.jmiExit = new JMenuItem("Exit");

    this.fileMenu.add(this.jmiNewGame);
    this.fileMenu.addSeparator();
    this.fileMenu.add(this.jmiSave);
    this.fileMenu.add(this.jmiLoad);
    this.fileMenu.addSeparator();
    this.fileMenu.add(this.jmiSettings);
    this.fileMenu.addSeparator();
    this.fileMenu.add(this.jmiExit);

    //View
    this.viewMenu = new JMenu("View");
    this.jmiTalentTree = new JMenuItem("Open Talent Tree");

    this.viewMenu.add(jmiTalentTree);

    //Help
    this.helpMenu = new JMenu("Help");
    this.jmiTutorial = new JMenuItem("Show Tuturial");
    this.jmiInfo = new JMenuItem("Info");

    this.helpMenu.add(this.jmiTutorial);
    this.helpMenu.add(this.jmiInfo);

    //Adding to the menuBar
    this.menuBar.add(this.fileMenu);
    this.menuBar.add(this.viewMenu);
    this.menuBar.add(this.helpMenu);
    this.setSize(this.windowWidth, this.windowHeight);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setResizable(false);
    this.windowCards = new CardLayout();
    this.getContentPane().setLayout(this.windowCards);
    this.getContentPane().add(this.titleView, "TitleView");
    this.getContentPane().add(this.mainView, "MainView");
    this.getContentPane().add(this.gameView, "GameView");
  }

  /**
   * Switches the view to the main screen.
   */

  public void switchToMainScreen() {
    this.mainView.resetMainView();
    this.windowCards.show(this.getContentPane(), "MainView");
  }

  /*
   ***********************
   * Change view methods *
   ***********************
   */

  /**
   * Sets the map for the game.
   *
   * @param mapView
   */

  public void setMap(final MapView mapView) {
    this.mapView = mapView;
    this.talentTreeView = new TalentTreeView(this.model);
    this.mapTalentCards = new CardLayout();
    this.mapTalentView = new JPanel(mapTalentCards);
    this.mapTalentView.add(mapView, "MapView");
    this.mapTalentView.add(talentTreeView, "TalentTreeView");
    this.gameView.removeAll();
    this.gameView.add(this.mapTalentView, BorderLayout.CENTER);
    this.gameView.add(this.sidePanelView, BorderLayout.EAST);
    this.windowCards.show(this.getContentPane(), "GameView");
    this.model.resetValues();
    this.mapView.repaint();
    if (!this.shownStartingTutorial) {
      this.mapView.startTutorial(0);
      this.setEnabledAll(this.sidePanelView.getComponents(), false);
      this.talentTreeView.disableButtons();
    }
    this.model.setActiveScreen(2);
  }

  /**
   * Enables all of the components given to it.
   *
   * @param components
   * @param enable
   */

  public void setEnabledAll(Component[] components, boolean enable) {
    for (Component p : components) {
      if (p instanceof JPanel) {
        setEnabledAll((((JPanel) p).getComponents()), enable);
      } else if (p.getName() != null && !p.getName().equals("UnderConstruction")) {
        p.setEnabled(enable);
      }
    }
  }

  /**
   * Determines whether or not to show the talent tree tutorial.
   */

  public void maybeShowTalentTreeTutorial() {
    if (this.model.getLevel() == 5 && !this.shownTalentTreeTutorial) {
      this.sidePanelView.getTowerView().enableTowerButtons(false);
      this.sidePanelView.getButtonView().getStartButton().setEnabled(false);
      this.sidePanelView.getButtonView().getTalentTreeButton().setEnabled(false);
      this.mapView.startTutorial(1);
    } else if (this.model.getLevel() == 10 && !this.shownBatTutorial) {
      this.sidePanelView.getTowerView().enableTowerButtons(false);
      this.sidePanelView.getButtonView().getStartButton().setEnabled(false);
      this.sidePanelView.getButtonView().getTalentTreeButton().setEnabled(false);
      this.mapView.startTutorial(2);
    } else if (this.model.getLevel() == 15 && !this.shownGiantPumpkinTutorial) {
      this.sidePanelView.getTowerView().enableTowerButtons(false);
      this.sidePanelView.getButtonView().getStartButton().setEnabled(false);
      this.sidePanelView.getButtonView().getTalentTreeButton().setEnabled(false);
      this.mapView.startTutorial(3);
    }
  }

  /**
   * sets the active tutorial as read
   */

  public void markTutorialRead() {
    if (this.mapView.getTutorialActive() == 0) {
      this.shownStartingTutorial = true;
    } else if (this.mapView.getTutorialActive() == 1) {
      this.shownTalentTreeTutorial = true;
    } else if (this.mapView.getTutorialActive() == 2) {
      this.shownBatTutorial = true;
    } else if (this.mapView.getTutorialActive() == 3) {
      this.shownGiantPumpkinTutorial = true;
    }
  }

  /**
   * Makes the talent tree panel active in the center of the screen.
   */

  public void triggerTalentTree() {
    if (this.mapView.isTalentTreeActive()) {
      this.mapView.setTalentTreeActive(false);
      this.sidePanelView.getButtonView().getStartButton().setEnabled(true);
      this.sidePanelView.switchToTowerPanel();
      this.model.getRuntimeThread().setTalentTreeActive(false);
      this.mapTalentCards.show(this.mapTalentView, "MapView");
      this.mapView.repaint();
    } else {
      this.mapView.setTalentTreeActive(true);
      this.sidePanelView.getButtonView().getStartButton().setEnabled(false);
      this.sidePanelView.switchToTalentPanel();
      this.model.getRuntimeThread().setTalentTreeActive(true);
      this.mapTalentCards.show(this.mapTalentView, "TalentTreeView");
      this.talentTreeView.repaint();
    }
  }

  /**
   * Setter for the upgrade panel.
   */

  public void setUpgradePanel() {

    this.sidePanelView.setVisible(false);
    this.sidePanelView.switchToUpgradePanel();
    this.sidePanelView.getUpgradeView().swapPanels();
    this.sidePanelView.setVisible(true);
  }

  /**
   * Setter for the tower panel.
   */

  public void setTowerPanel() {
    this.sidePanelView.switchToTowerPanel();
  }

  /*
   ***********************
   * Component Accessors *
   ***********************
   */

  /**
   * Getter for the title view.
   *
   * @return TitleView
   */

  public TitleView getTitleView() {
    return this.titleView;
  }

  /**
   * Getter for the main view.
   *
   * @return MainView
   */

  public MainView getMainView() {
    return this.mainView;
  }

  /**
   * Getter for the driver view.
   *
   * @return JFrame
   */

  public JFrame getDriverView() {
    return this;
  }

  /**
   * Getter for the map view.
   *
   * @return MapView
   */

  public MapView getMapView() {
    return this.mapView;
  }

  /**
   * Getter for the side panel view.
   *
   * @return SidePanelView
   */

  public SidePanelView getSidePanelView() {
    return this.sidePanelView;
  }

  /**
   * Getter for the new game menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getNewGameMenuItem() {
    return this.jmiNewGame;
  }

  /**
   * Getter for the save game menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getSaveGameMenuItem() {
    return this.jmiNewGame;
  }

  /**
   * Getter for the load game menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getLoadGameMenuItem() {
    return this.jmiLoad;
  }

  /**
   * Setter for the menu items.
   *
   * @return JMenuItem
   */

  public JMenuItem getSettingsMenuItem() {
    return this.jmiSettings;
  }

  /**
   * Getter for the info menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getInfoMenuItem() {
    return this.jmiInfo;
  }

  /**
   * Getter for the tutorial menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getTutorialMenuItem() {
    return this.jmiTutorial;
  }

  /**
   * Getter for the exit menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getExitMenuItem() {
    return this.jmiExit;
  }

  /**
   * Getter for the talent tree menu item.
   *
   * @return JMenuItem
   */

  public JMenuItem getTalentTreeMenuItem() {
    return this.jmiTalentTree;
  }

  /**
   * gets the talent tree view
   *
   * @return TalentTreeView
   */

  public TalentTreeView getTalentTreeView() {
    return talentTreeView;
  }

  /**
   * returns whether the starting tutorial has been shown
   *
   * @return boolean
   */

  public boolean isShownStartingTutorial() {
    return shownStartingTutorial;
  }

  /**
   * updates the talent tree with the
   * new tier depth
   *
   * @param tierDepth
   */

  public void updateTalentTree(final int tierDepth) {
    this.talentTreeView = new TalentTreeView(this.model);
  }

  /*
   **********************
   * Utility methods *
   **********************
   */

  /**
   * Getter to return a buffered image from our images folder.
   *
   * @param imageName
   * @param width
   * @param height
   * @return BufferedImage
   */

  public static BufferedImage getImage(final String imageName,
                                       final int width, final int height) {
    try {
      InputStream inputStream = GameView.class.getClassLoader()
          .getResourceAsStream(imageResourceFolder + "/" + imageName);

      if (inputStream == null) {
        throw new IllegalArgumentException("Image not found " + imageName);
      }

      return resizeImage(ImageIO.read(inputStream), width, height);
    } catch (IOException e) {
      System.err.println(e);
      System.exit(128);
    }
    return null;
  }

  /**
   * Resizes an image from our images folder.
   *
   * @param image
   * @param newWidth
   * @param newHeight
   * @return BufferedImage
   */

  public static BufferedImage resizeImage(final BufferedImage image, final int newWidth,
                                          final int newHeight) {
    final int imageWidth = image.getWidth();
    final int imageHeight = image.getHeight();
    final BufferedImage newImage = new BufferedImage(newWidth,
        newHeight, image.getType());
    final Graphics2D graphicsRenderer = newImage.createGraphics();
    graphicsRenderer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphicsRenderer.drawImage(image, 0, 0, newWidth, newHeight, 0, 0,
        imageWidth, imageHeight, null);
    graphicsRenderer.dispose();
    return newImage;
  }

  /**
   * Rotates an image from our images folder.
   *
   * @param image
   * @param rotationAngle
   * @return BufferedImage
   */

  public static BufferedImage rotateImage(final BufferedImage image, final int rotationAngle) {
    double rotationRequired = Math.toRadians(rotationAngle);
    return rotateImage(image, rotationRequired);
  }

  /**
   * Rotates an image from our images folder.
   *
   * @param image
   * @param rotationRequired
   * @return BufferedImage
   */

  public static BufferedImage rotateImage(final BufferedImage image,
                                          final double rotationRequired) {

    double locationX = image.getWidth() / 2;
    double locationY = image.getHeight() / 2;
    AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
    return op.filter(image, null);
  }

  /**
   * Sets the icon for when the icon is disabled.
   *
   * @param button
   */

  public static void setDisableIcon(AbstractButton button) {
    BufferedImage disableIcon = getImage("Locked.png", AbstractButton.WIDTH, AbstractButton.HEIGHT);
    button.setDisabledIcon(new ImageIcon(disableIcon));
  }

  /**
   * Action Performed method.
   */

  public void actionPerformed(ActionEvent e) {
    this.maybeShowTalentTreeTutorial();
    if (this.model.getActiveScreen() == 1) {
      this.switchToMainScreen();
    } else {
      this.mapView.repaint();
    }
  }
}
