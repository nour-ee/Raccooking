package View;

import Model.Bakery;

import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel {
    private Bakery bakery;
    private JButton levelButton;
    private JButton nextButton;
    private JButton previousButton;
    private int currentLevel = 1;
    public static final int LEVEL_W = 300;
    public static final int LEVEL_H = 100;
    public static final int NB_LEVEL = 3;
    private boolean next = false;
    private boolean prev = false;

    public LevelPanel() {
        this.setBounds(0, 0, LEVEL_W, LEVEL_H);
        this.setLayout(null);

        createLevelButton();
        createNavigationButton();
        bakery = new Bakery(this);
        this.setVisible(true);
    }
    /****************
     *    GETTERS   *
     ****************/
    public JButton getLevelButton() {return levelButton;}
    public  boolean getNext() { return next; }
    public  boolean getPrev() { return prev; }
    public int getCurrentLevel() { return currentLevel; }
    public void setNext(boolean b) { next = b; }
    public void setPrev(boolean b) { prev = b; }

    private void createNavigationButton() {
        nextButton = new JButton("→");
        nextButton.setBounds(LEVEL_W / 2 + 100, 0, 50, LEVEL_H);
        nextButton.addActionListener(e -> {
            if (currentLevel < NB_LEVEL) {
                next = true;  // Déclencher animation vers la droite
            }
        });

        previousButton = new JButton("←");
        previousButton.setBounds(0, 0, 50, LEVEL_H);
        previousButton.addActionListener(e -> {
            if (currentLevel > 1) {
                prev = true; // Déclencher animation vers la gauche
            }
        });
        this.add(nextButton);
        this.add(previousButton);
    }

    private void createLevelButton() {
        levelButton = new JButton("Level " + currentLevel);
        levelButton.setBounds(LEVEL_W / 2 - 50, LEVEL_H / 4, 100, 50);
        this.add(levelButton);
    }

    public void updateLevel(int direction) {
        currentLevel += direction;
        levelButton.setText("Level " + currentLevel);
        bakery.setLevel(currentLevel);
        levelButton.setBounds(LEVEL_W / 2 - 50, LEVEL_H / 4, 100, 50); // Reset position
    }

}