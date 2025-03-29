
package View;

import javax.swing.*;

public class Animation extends Thread {
    private LevelPanel levelPanel;
    private static final int MOVE = 5;
    private static final int DELAY = 10;

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
                moveButton("right");
                levelPanel.setNext(false);
                levelPanel.updateLevel(1);
            }
            else if (levelPanel.getPrev()) {
                moveButton("left");
                levelPanel.setPrev(false);
                levelPanel.updateLevel(-1);
            }
        }
    }

    // Move the level button to the right or left
    private void moveButton(String direction) {
        JButton levelButton = levelPanel.getLevelButton();

        // Move the button until it reaches the end of the panel
        // or the beginning of the panel
        while (true) {
            int x = levelButton.getX();
            if (direction.equals("right") && x < LevelPanel.LEVEL_W) {
                levelButton.setBounds(x + MOVE, levelButton.getY(), levelButton.getWidth(), levelButton.getHeight());
            } else if (direction.equals("left") && x > 0) {
                levelButton.setBounds(x - MOVE, levelButton.getY(), levelButton.getWidth(), levelButton.getHeight());
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