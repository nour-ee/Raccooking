package View;

import Model.Bakery;

import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel {
    private JLabel levelLabel;
    private JButton nextButton;
    private JButton previousButton;


    private int currentLevel = 0;
    public static final String[] LEVELS= {"level1", "level2", "level3"};


    public static final int LEVEL_W = 300;
    public static final int LEVEL_H = 100;
    private boolean next = false;
    private boolean prev = false;

    public LevelPanel() {
        this.setBounds(0, 0, LEVEL_W, LEVEL_H);
        this.setLayout(null);
        this.setOpaque(false);

        createLevelLabel();
        createNavigationButton();
        this.setVisible(true);

        Animation anim = new Animation(this);
        anim.start();
    }
    /****************
     *    GETTERS   *
     ****************/
    public JLabel getLevelLabel() {return levelLabel;}
    public  boolean getNext() { return next; }
    public  boolean getPrev() { return prev; }
    public String getCurrentLevel() { return LEVELS[currentLevel]; }
    public void setNext(boolean b) { next = b; }
    public void setPrev(boolean b) { prev = b; }

    private void createNavigationButton() {
        nextButton = new JButton("→");
        nextButton.setBounds(LEVEL_W / 2 + 100, 0, 50, LEVEL_H);
        nextButton.addActionListener(e -> {
            next = true;  // Déclencher animation vers la droite
        });

        previousButton = new JButton("←");
        previousButton.setBounds(0, 0, 50, LEVEL_H);
        previousButton.addActionListener(e -> {
            prev = true; // Déclencher animation vers la gauche

        });
        this.add(nextButton);
        this.add(previousButton);
    }
    private void createLevelLabel() {
        levelLabel = new JLabel(LEVELS[currentLevel]);
        levelLabel.setBounds(LEVEL_W / 2 -50, LEVEL_H / 4, 100, 50);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 23));
        this.add(levelLabel);
    }

    public void updateLevel(int direction) {
        currentLevel= (currentLevel+direction)%(LEVELS.length)==-1 ? LEVELS.length-1 : (currentLevel+direction)%(LEVELS.length); //to get the last elt of the array instead of -1
        levelLabel.setText(LEVELS[currentLevel]);
        levelLabel.setBounds(LEVEL_W / 2 - 50, LEVEL_H / 4, 100, 50); // Reset position
    }

}