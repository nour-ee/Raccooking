package View;

import Controller.BakerControl;
import Controller.EndGame;
import Model.Bakery;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/** Manage interaction with the baker panel */
public class BakerPanel extends JPanel {

    /*****************
     *   ATTRIBUTES  *
     *****************/

    private BakerControl bakerControl; // manage buttons actions

    private JLabel moneyLabel;
    private JProgressBar timeProgressBar; // progress bar for the time
    private ArrayList<JLabel> ingredientsLabelsList;    //to store our ingredients labels
                                                        //to update them easily
    /*****************
     *   CONSTANTS  *
     *****************/

    public static final int HEIGHT = Display.FRAME_H;
    public static final int WIDTH = 300;

    public static final int BUTTON_HEIGHT = 40;
    public static final int BUTTON_WIDTH = 150;
    public static final String[] RESSOURCES = {"flour", "egg", "yeast", "butter"};

    public static final int ICON_W = 20; // width of the icon in the label
    public static final int ICON_H = 20; // height of the icon in the label

    public static final int ICON_W_BAKER = 100; // width of the icon in the label
    public static final int ICON_H_BAKER = 100; // height of the icon in the label

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public BakerPanel(Bakery bakery) {
        this.bakerControl = new BakerControl(this,bakery);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color( 182, 179, 175 ));


        // to store the ingredients labels
        this.ingredientsLabelsList = new ArrayList<>();

        // add image of baker in panel
        JLabel bakerLabel = new JLabel(ressourceImage("baker", ICON_W_BAKER, ICON_H_BAKER));
        bakerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(bakerLabel);

        // === ressources Panel ===
        createRessourcesPanel();

        // === buttons Panel ===
        createButtonsPanel();

        // === game panel ===
        this.add(createGamePanel());
    }


    /***************
     *   METHODS   *
     ***************/

    /**
     * Creates the ressources panel to display money, bread and ingredients
     */
    private JPanel createRessourcesPanel() {
        {
            // === Panel ressources ===
            JPanel ressourcesPanel = new JPanel();
            ressourcesPanel.setLayout(new GridBagLayout());
            ressourcesPanel.setSize(new Dimension(WIDTH - 40, 10));
            ressourcesPanel.setOpaque(false);
            GridBagConstraints gbc2 = new GridBagConstraints();

            // money label
            moneyLabel = new JLabel("Money :  " + bakerControl.getBakery().getPlayer().getMoney() + "€");
            gbc2.gridx = 0;
            gbc2.gridy = 0;
            gbc2.gridwidth = 2;
             gbc2.insets = new Insets(0, 2, 10, 10);
            ressourcesPanel.add(moneyLabel, gbc2);


            // ingredients labels
            JLabel ressourcesLabel = new JLabel("Ingredients :");
            gbc2.gridx = 0;
            gbc2.gridy = 2;
            gbc2.gridwidth = 2;
            gbc2.insets = new Insets(2, 2, 10, 10);
            ressourcesPanel.add(ressourcesLabel, gbc2);

            // display the ingredients
            for (int i = 0; i < RESSOURCES.length; i++) {
                JLabel ingredientLabel = new JLabel(ressourceImage(RESSOURCES[i], ICON_W, ICON_H) + ": " + bakerControl.getBakery().getPlayer().getRessources().get(RESSOURCES[i]));
                ingredientLabel.setIcon(ressourceImage(RESSOURCES[i], ICON_W, ICON_H));
                if (i % 2 == 0) {
                    gbc2.gridx = 0;
                    gbc2.gridy = 3 + i;
                } else {
                    gbc2.gridx = 1;
                    gbc2.gridy = 3 + (i - 1);
                }
                gbc2.gridwidth = 1;
                ingredientsLabelsList.add(ingredientLabel);
                ressourcesPanel.add(ingredientLabel, gbc2);
            }
            this.add(ressourcesPanel);
            return ressourcesPanel;
        }
    }

    /**
     * Creates the buttons panel to collect and sell, buy and add goods
     */
    private void createButtonsPanel(){
        // === Panel buttons ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(203, 202, 201));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 4;

        //spaces between buttons
        gbc.insets = new Insets(0, 10, 10, 10);

        // Create buttons
        JButton collectButton = new JButton("Collect & Sell");
        collectButton.setName("Collect & Sell");
        collectButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc.gridx = 0;
        gbc.gridy = 0;
        collectButton.setFocusable(false);
        collectButton.addActionListener(bakerControl);
        buttonPanel.add(collectButton, gbc);

        JButton buyButton = new JButton("Buy");
        buyButton.setName("Buy");
        buyButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc.gridy++;
        buyButton.setFocusable(false);
        buyButton.addActionListener(bakerControl);
        buttonPanel.add(buyButton, gbc);

        gbc.gridwidth=3;
        // Create Bread
        JPanel breadPanel = createPanelBread();
        gbc.gridy++;
        breadPanel.setFocusable(false);
        buttonPanel.add(breadPanel, gbc);


        // Create Croissant
        JPanel croissantPanel = createPanelCroissant();
        gbc.gridy++;
        croissantPanel.setFocusable(false);
        buttonPanel.add(croissantPanel, gbc);

        // Create Brioche
        JPanel briochePanel = createPanelBrioche();
        gbc.gridy++;
        briochePanel.setFocusable(false);
        buttonPanel.add(briochePanel, gbc);


        // Add the buttonPanel to the Bakerpanel
        this.add(buttonPanel);

    }

    /**
     * Gets the name of the image of the ingredient
     * @param name the name of the ingredient
     * @return the image of the ingredient
     */
    private ImageIcon ressourceImage(String name, int width, int height) {
        ImageIcon ressourceIcon = new ImageIcon(getClass().getResource("/img/"+name+".png"));
        Image scaledImage = ressourceIcon.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Create the bread panel
     * @return the bread panel
     */
    private JPanel createPanelBread(){
        JPanel breadPanel = new JPanel();
        breadPanel.setLayout(new GridBagLayout());
        breadPanel.setPreferredSize(new Dimension(WIDTH, BUTTON_HEIGHT));
        breadPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 55);

        //create button bread
        JButton bakeBread = new JButton(ressourceImage("breadCooked", BUTTON_HEIGHT, BUTTON_HEIGHT));
        bakeBread.setName("BakeBread");
        bakeBread.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_HEIGHT));
        bakeBread.setFocusable(false);
        bakeBread.addActionListener(bakerControl);
        breadPanel.add(bakeBread, gbc);


        //create label panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 2, 20, 0));
        labelPanel.setOpaque(false);

        JLabel ingredientLabel1 = new JLabel(ressourceImage("flour", ICON_W, ICON_H));
        ingredientLabel1.setText("1");
        JLabel ingredientLabel2 = new JLabel(ressourceImage("yeast", ICON_W, ICON_H));
        ingredientLabel2.setText("1");
        labelPanel.add(ingredientLabel1);
        labelPanel.add(ingredientLabel2);

        breadPanel.add(labelPanel, gbc);

        return breadPanel;
    }

    /**
     * Create the croissant panel
     * @return the croissant panel
     */
    private JPanel createPanelCroissant(){
        JPanel croissantPanel = new JPanel();
        croissantPanel.setLayout(new GridBagLayout());
        croissantPanel.setPreferredSize(new Dimension(WIDTH , BUTTON_HEIGHT));
        croissantPanel.setOpaque(false);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 30);

        //create button
        JButton bakeCroissant = new JButton(ressourceImage("croissantCooked", BUTTON_HEIGHT, BUTTON_HEIGHT));
        bakeCroissant.setName("BakeCroissant");
        bakeCroissant.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_HEIGHT));
        bakeCroissant.setFocusable(false);
        bakeCroissant.addActionListener(bakerControl);
        croissantPanel.add(bakeCroissant, gbc);

        //create label panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 3, 20, 0));
        labelPanel.setOpaque(false);

        // create the labels for the ingredients
        JLabel ingredientLabel1 = new JLabel(ressourceImage("flour", ICON_W, ICON_H));
        ingredientLabel1.setText("1");
        JLabel ingredientLabel2 = new JLabel(ressourceImage("egg", ICON_W, ICON_H));
        ingredientLabel2.setText("1");
        JLabel ingredientLabel3 = new JLabel(ressourceImage("butter", ICON_W, ICON_H));
        ingredientLabel3.setText("2");

        // add the labels to the panel
        labelPanel.add(ingredientLabel1);
        labelPanel.add(ingredientLabel2);
        labelPanel.add(ingredientLabel3);

        // add the label panel to the croissant panel
        croissantPanel.add(labelPanel, gbc);

        return croissantPanel;

    }

    /**
     * Create the brioche panel
     * @return the brioche panel
     */
    private JPanel createPanelBrioche(){
        JPanel briochePanel = new JPanel();
        briochePanel.setLayout(new GridBagLayout());
        briochePanel.setPreferredSize(new Dimension(WIDTH , BUTTON_HEIGHT));
        briochePanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 5);

        //create button
        JButton bakeBrioche = new JButton(ressourceImage("briocheCooked", BUTTON_HEIGHT, BUTTON_HEIGHT));
        bakeBrioche.setName("BakeBrioche");
        bakeBrioche.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_HEIGHT));
        bakeBrioche.setFocusable(false);
        bakeBrioche.addActionListener(bakerControl);
        briochePanel.add(bakeBrioche, gbc);

        //create label panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(1, 4, 20, 0));
        labelPanel.setOpaque(false);

        // create the labels for the ingredients
        JLabel ingredientLabel1 = new JLabel(ressourceImage("flour", ICON_W, ICON_H));
        ingredientLabel1.setText("1");
        JLabel ingredientLabel2 = new JLabel(ressourceImage("egg", ICON_W, ICON_H));
        ingredientLabel2.setText("1");
        JLabel ingredientLabel3 = new JLabel(ressourceImage("yeast", ICON_W, ICON_H));
        ingredientLabel3.setText("1");
        JLabel ingredientLabel4 = new JLabel(ressourceImage("butter", ICON_W, ICON_H));
        ingredientLabel4.setText("1");

        // add the labels to the panel
        labelPanel.add(ingredientLabel1);
        labelPanel.add(ingredientLabel2);
        labelPanel.add(ingredientLabel3);
        labelPanel.add(ingredientLabel4);

        // add the label panel to the brioche panel
        briochePanel.add(labelPanel, gbc);

        return briochePanel;
    }

    /**
     * Create the game panel contain goals and time
     * @return the game panel
     */
    private JPanel createGamePanel(){
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setPreferredSize(new Dimension(WIDTH, 250));
        gamePanel.setBackground(new Color(164, 163, 163));

        // create the label for the goals
        JLabel goalsLabel = new JLabel("Goal : "+Bakery.GOAL+"€");
        goalsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        goalsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
        gamePanel.add(goalsLabel);

        // create the panel for the time
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setPreferredSize(new Dimension(WIDTH, 50));
        timePanel.setOpaque(false);

        // create the label for the time
        JLabel timeLabel = new JLabel(ressourceImage("time", ICON_W*2, ICON_H*2));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timePanel.add(timeLabel);

        // create the progress bar for the time
        timeProgressBar = new JProgressBar(0, EndGame.TIME);
        System.out.println(EndGame.TIME);
        timeProgressBar.setValue(EndGame.TIME);
        timeProgressBar.setStringPainted(true);
        timeProgressBar.setForeground(new Color(76, 175, 80));
        timeProgressBar.setFont(new Font("Arial", Font.BOLD, 14));
        timeProgressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamePanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
        timePanel.add(timeProgressBar);
        gamePanel.add(timePanel);

        return gamePanel;

    }

    /**
     * Update the ressources panel
     */
    public void update(){
        moneyLabel.setText("Money:  " + bakerControl.getBakery().getPlayer().getMoney()+"€");
        for ( JLabel j : ingredientsLabelsList) {
            j.setText(RESSOURCES[ingredientsLabelsList.indexOf(j)]
                    + ": " + bakerControl.getBakery().getPlayer().getRessources(). //HashMap des ingrédients du baker
                    get(RESSOURCES[ingredientsLabelsList.indexOf(j)]));
        }
        // update the time progress bar

        timeProgressBar.setValue(EndGame.timeLeft);
        timeProgressBar.setString(EndGame.timeLeft+"s");
        timeProgressBar.setForeground(new Color(76, 175, 80));
        if(EndGame.timeLeft <= EndGame.TIME/3){
            timeProgressBar.setForeground(new Color(255, 165, 0));
        }
        if(EndGame.timeLeft <= EndGame.TIME/5){
            timeProgressBar.setForeground(new Color(255, 0, 0));
        }
    }
}