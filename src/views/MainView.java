
package views;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import utilities.SelectButtonGroup;

/**
 * The main view of the game displaying the
 * maps, difficulty, and start button to the user
 *
 * @author Scorpion
 *
 */

public class MainView extends ImagePanel {

	private static final long serialVersionUID = 1L;
	private Border selectionBorder = new LineBorder(Color.YELLOW, 5);
	private Border deselectionBorder = new LineBorder(Color.WHITE, 5);
	private JToggleButton map1 = new JToggleButton();
	private JToggleButton map2 = new JToggleButton();
	private JToggleButton map3 = new JToggleButton();
	private JToggleButton easy = new JToggleButton(new ImageIcon(DriverView.getImage("EasyButton.png", 65, 28)));
	private JToggleButton medium = new JToggleButton(new ImageIcon(DriverView.getImage("MediumButton.png", 60, 28)));
	private JToggleButton hard = new JToggleButton(new ImageIcon(DriverView.getImage("HardButton.png", 65, 28)));
	private JButton start = new JButton(new ImageIcon(DriverView.getImage("StartButton.png", 125, 50)));
	private JButton exit = new JButton(new ImageIcon(DriverView.getImage("ExitButton.png", 90, 40)));
	private JButton info = new JButton(new ImageIcon(DriverView.getImage("InfoButton.png", 90, 40)));
	private SelectButtonGroup mapGroup = new SelectButtonGroup();
	private SelectButtonGroup difficultyGroup = new SelectButtonGroup();
	private JPanel buttonPanel = new JPanel();

	/**
	 * Constructor for the main view. Takes an image to set as the background.
	 * @param image
	 */

	public MainView(BufferedImage image) {

    	super(image, null, null);

    	map1.setBorder(deselectionBorder);
    	map2.setBorder(deselectionBorder);
    	map3.setBorder(deselectionBorder);
    	easy.setBorder(deselectionBorder);
    	medium.setBorder(deselectionBorder);
    	hard.setBorder(deselectionBorder);
    	mapGroup.add(map1);
       	mapGroup.add(map2);
       	mapGroup.add(map3);
    	difficultyGroup.add(easy);
    	difficultyGroup.add(medium);
    	difficultyGroup.add(hard);
    	easy.setVisible(false);
    	medium.setVisible(false);
    	hard.setVisible(false);

    	start.setContentAreaFilled(false);
    	exit.setContentAreaFilled(false);
    	info.setContentAreaFilled(false);
    	start.setBorder(BorderFactory.createEmptyBorder());
    	exit.setBorder(BorderFactory.createEmptyBorder());
    	info.setBorder(BorderFactory.createEmptyBorder());

    	this.start.setEnabled(false);
    	this.layoutView();

    }

	/**
	 * Adds the buttons and layouts the view.
	 */

	public void layoutView() {

		this.setLayout(new GridBagLayout());
		this.setOpaque(true);

		JLabel mapLabel = new JLabel("Select A Map");
		mapLabel.setFont(new Font("Serif", Font.BOLD, 48));
		mapLabel.setForeground(Color.yellow);

		JPanel filler1 = new JPanel();
		JPanel filler2 = new JPanel();

		filler1.setPreferredSize(new Dimension(0, 30));
		filler2.setPreferredSize(new Dimension(0, 30));
		easy.setPreferredSize(new Dimension(50, 30));
		medium.setPreferredSize(new Dimension(60, 30));
		hard.setPreferredSize(new Dimension(50, 30));

		buttonPanel.add(easy);
		buttonPanel.add(filler1);
		buttonPanel.add(medium);
		buttonPanel.add(filler2);
		buttonPanel.add(hard);
		buttonPanel.setOpaque(false);

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(mapLabel);


        this.add(map1);

        JPanel maps = new JPanel();
        maps.setLayout(new BoxLayout(maps,BoxLayout.X_AXIS));
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        this.add(maps);
        this.add(buttonPanel);
        this.setOpaque(false);

		map2.setEnabled(false);
		map3.setEnabled(false);

		map1.setIcon(new ImageIcon("images/map1thumbnail.png"));

		map2.setIcon(new ImageIcon("images/map2thumbnail.png"));

		map3.setIcon(new ImageIcon("images/map3thumbnail.png"));
		//this.add(map3, c);


		JPanel startPanel = new JPanel(new GridBagLayout());
		startPanel.setOpaque(false);
		this.add(start);

		this.add(exit);

		this.add(info);

		//this.add(startPanel, c);

		disableDifficulty();
	}

	/**
	 * Disables all of the selections the user has made on the main view.
	 */

	public void resetMainView() {
		map1.setBorder(deselectionBorder);
    	map2.setBorder(deselectionBorder);
    	map3.setBorder(deselectionBorder);
    	this.disableDifficulty();
    	this.start.setEnabled(false);

	}

	/**
	 * Disables all of the selections the user has made on the difficulty.
	 */

	public void disableDifficulty(){
		difficultyGroup.clearSelection();
		easy.setVisible(false);
		medium.setVisible(false);
		hard.setVisible(false);
	}

	/**
	 * Enables the difficulty to be selected after the user has selected a map.
	 */

	public void enableDifficulty(){
		easy.setVisible(true);
		medium.setVisible(true);
		hard.setVisible(true);
	}

	/**
	 * Getter for the border.
	 *
	 * @return Border
	 */

	public Border getSelectionBorder() {
		return this.selectionBorder;
	}

	/**
	 * Getter for the deselected border.
	 *
	 * @return Border
	 */

	public Border getDeelectionBorder() {
		return this.deselectionBorder;
	}

	/**
	 * Getter for the first map
	 *
	 * @return JToggleButton
	 */

	public JToggleButton getMap1() {
		return this.map1;
	}

	/**
	 * Getter for the second map.
	 *
	 * @return JToggleButton
	 */

	public JToggleButton getMap2(){
		return this.map2;
	}

	/**
	 * Getter for the third map.
	 *
	 * @return JToggleButton
	 */

	public JToggleButton getMap3(){
		return this.map3;
	}

	/**
	 * Getter for the easy difficulty.
	 *
	 * @return JToggleButton
	 */

	public JToggleButton getEasyButton(){
		return this.easy;
	}

	/**
	 * Getter for the medium difficulty.
	 *
	 * @return JToggleButton
	 */

	public JToggleButton getMediumButton(){
		return this.medium;
	}

	/**
	 * Getter for the hard difficulty.
	 *
	 * @return JToggleButton
	 */

	public JToggleButton getHardButton(){
		return this.hard;
	}

	/**
	 * Getter for the start button.
	 *
	 * @return JButton
	 */

	public JButton getStartButton(){
		return this.start;
	}

	/**
	 * Getter for the exit button.
	 *
	 * @return JButton
	 */

	public JButton getExitButton(){
		return this.exit;
	}

	/**
	 * Getter for the info button.
	 *
	 * @return JButton
	 */

	public JButton getInfoButton(){
		return this.info;
	}

	/**
	 * Getter for the button group for the maps.
	 *
	 * @return SelectButtonGroup
	 */

	public SelectButtonGroup getMapGroup(){
		return mapGroup;
	}

	/**
	 * Getter for the button group for the difficulty
	 *
	 * @return SelectButtonGroup
	 */

	public SelectButtonGroup getDifficultyGroup(){
		return difficultyGroup;
	}

	/**
	 * Enables the start button once the user has selected a map and a difficulty.
	 */

	public void enableStartButton() {
		this.start.setEnabled(true);
	}
}
