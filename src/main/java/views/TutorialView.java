package views;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TutorialView extends JFrame {

    private static final long serialVersionUID = 1L;
    public final int TUTORIAL_HEIGHT = 400;
    public final int TUTORIAL_WIDTH = 300;

    private JButton jbtExit = new JButton("Exit");
    private JButton jbtNext = new JButton("Next");
    private JPanel buttonPanel = new JPanel();
    private JTabbedPane jtabbedPane = new JTabbedPane();

    /**
     * constructs the tutorial view
     */

    public TutorialView() {
        jtabbedPane.addTab("Instructions", new TutorialPanel());
        jtabbedPane.addTab("Game", new TutorialPanel());
        jtabbedPane.addTab("Towers", new TutorialPanel());
        jtabbedPane.addTab("Mob", new TutorialPanel());

        //Panel for buttons
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(jbtExit);
        buttonPanel.add(jbtNext);

        //Settings on JFrame
        this.setLayout(new BorderLayout());
        this.add(jtabbedPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setTitle("Tutorial");
        this.setSize(TUTORIAL_WIDTH, TUTORIAL_HEIGHT + 40);
        this.setLocation(new Point(100, 100));
        this.setVisible(true);
    }

    /**
     * creates an image in a panel
     *
     * @author Scorpion
     */

    private class TutorialPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        BufferedImage background;

        public TutorialPanel() {
            this.background = new BufferedImage(300, 400, BufferedImage.TYPE_INT_RGB);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, null);
        }

    }

}
