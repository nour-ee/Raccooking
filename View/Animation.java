
package View;

import javax.swing.*;

public class Animation extends Thread {
    private LevelPanel levelPanel;
    private static final int MOVE = 5; // pixels
    private static final int DELAY = 1000; // milliseconds

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
                moveButton("left");
                levelPanel.setNext(false);
                levelPanel.updateLevel(1);
            }
            else if (levelPanel.getPrev()) {
                moveButton("right");
                levelPanel.setPrev(false);
                levelPanel.updateLevel(-1);
            }
        }
    }

    // Move the level button to the right or left
    private void moveButton(String direction) {
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
                Thread.sleep(DELAY); // Delay between each move
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}