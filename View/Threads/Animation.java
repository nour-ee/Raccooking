
package View.Threads;

import javax.swing.*;

import View.LevelPanel;

public class Animation extends Thread {
    private LevelPanel levelPanel;
    private static final int MOVE = 5; // pixels
    private static final int DELAY = 1000; // milliseconds

    private static final int DELAY_MOVE = 15; // milliseconds

    public Animation(LevelPanel panel) {
        this.levelPanel = panel;
    }

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

    // Move the level label to the right or left
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