
package View.Threads;

import javax.swing.*;

import View.LevelPanel;
/**
 * Animation class to manage the animation of the level label
 * It will move the label to the left or right depending on the user input
 * It will also update the level of the game
 */
public class Animation extends Thread {
    /****************
     *  ATTRIBUTES  *
     ****************/
    private LevelPanel levelPanel;

    /****************
     *  CONSTANTS   *
     ****************/
    private static final int MOVE = 5; // pixels
    private static final int DELAY = 1000; // milliseconds

    private static final int DELAY_MOVE = 15; // milliseconds

    /****************
     *  CONSTRUCTOR  *
     ****************/
    public Animation(LevelPanel panel) {
        this.levelPanel = panel;
    }

    /**
     * Method to run the animation
     * It will move the label to the left or right depending on the user input
     * It will also update the level of the game
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (levelPanel.getNext()) {
                moveLabel("left");
                levelPanel.setNext(false);
                levelPanel.updateLevel(1);
            }
            else if (levelPanel.getPrev()) {
                moveLabel("right");
                levelPanel.setPrev(false);
                levelPanel.updateLevel(-1);
            }
        }
    }

    /**
     * Method to move the level label to the left or right
     * @param direction the direction to move the label
     */
    private void moveLabel(String direction) {
        JLabel levelLabel = levelPanel.getLevelLabel();

        // Move the label until it reaches the end of the panel
        // or the beginning of the panel
        while (true) {
            int x = levelLabel.getX();
            if (direction.equals("right") && x < LevelPanel.LEVEL_W) {
                levelLabel.setBounds(x + MOVE, levelLabel.getY(), levelLabel.getWidth(), levelLabel.getHeight());
            } else if (direction.equals("left") && x > 0) {
                levelLabel.setBounds(x - MOVE, levelLabel.getY(), levelLabel.getWidth(), levelLabel.getHeight());
            } else {
                break;
            }

            levelPanel.repaint();

            try {
                Thread.sleep(DELAY_MOVE); // Delay between each move
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}