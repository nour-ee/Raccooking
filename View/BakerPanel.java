package View;

import Controller.BakerControl;
import Controller.EndGame;
import Model.Bakery;
import Model.Raccoon;

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
    private ArrayList<JLabel> ingredientsLabelsList;    //to store our ingredients labels
                                                        //to update them easily
    private JProgressBar progressBar; //progress bar to show time left in game
    public int TIME = EndGame.TIME;

    /*****************
     *   CONSTANTS  *
     *****************/

    public static final int HEIGHT = Display.FRAME_H;
    public static final int WIDTH = 300;

    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_WIDTH = 200;
    public static final String[] RESSOURCES = {"flour", "egg", "yeast", "butter"};


    /********************
     *    CONSTRUCTOR   *
     ********************/
    public BakerPanel(Bakery bakery) {
        this.bakerControl = new BakerControl(this,bakery);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color( 182, 179, 175 ));

        // to store the ingredients labels
        this.ingredientsLabelsList = new ArrayList<>();

        // add image of baker in panel
        ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/baker.png"));

        // Resize the image
        int newWidth = 150;
        int newHeight = 150;
        Image scaledImage = bakerIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBakerIcon = new ImageIcon(scaledImage);

        JLabel bakerLabel = new JLabel(scaledBakerIcon);
        this.add(bakerLabel, BorderLayout.NORTH);

        // === ressources Panel ===
        createRessourcesPanel();

        // === buttons Panel ===
        createButtonsPanel();

        createMapPanel();
    }

    /***************
     *   METHODS   *
     ***************/

    /**
     * Creates the ressources panel to display money, bread and ingredients
      */
    private void createRessourcesPanel() {
        // === Panel ressources ===
        JPanel ressourcesPanel = new JPanel();
        ressourcesPanel.setLayout(new GridBagLayout());
        ressourcesPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
        ressourcesPanel.setBackground(new Color( 182, 179, 175 ));
        GridBagConstraints gbc2 = new GridBagConstraints();

        // money label
        moneyLabel = new JLabel("Money :  " + bakerControl.getBakery().getPlayer().getMoney()+"$");
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(2, 2, 10, 10);
        ressourcesPanel.add(moneyLabel, gbc2);

        // ingredients labels
        JLabel ressourcesLabel = new JLabel("Ingrédients :");
        gbc2.gridx = 0; gbc2.gridy =  2;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(2, 2, 10, 10);
        ressourcesPanel.add(ressourcesLabel, gbc2);

        // display the ingredients
        for (int i = 0; i < RESSOURCES.length; i++) {
            ImageIcon ingredientIcon;
            switch(RESSOURCES[i]){
                case "flour":
                    ingredientIcon = new ImageIcon(getClass().getResource("/img/flour.png"));
                    break;
                case "egg":
                    ingredientIcon = new ImageIcon(getClass().getResource("/img/egg.png"));
                    break;
                case "yeast":
                    ingredientIcon = new ImageIcon(getClass().getResource("/img/yeast.png"));
                    break;
                default : // butter
                    ingredientIcon = new ImageIcon(getClass().getResource("/img/butter.png"));
                    break;
            }
            // Resize the image
            Image scaledImage = ingredientIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ingredientIcon = new ImageIcon(scaledImage);
            // Create the label with the icon and next to the icon " : "+bakerControl.getBakery().getPlayer().getRessources().get(RESSOURCES[i])
            JLabel ingredientLabel = new JLabel(ingredientIcon);
            ingredientLabel.setText(" : " + bakerControl.getBakery().getPlayer().getRessources().get(RESSOURCES[i]));
            ingredientLabel.setIconTextGap(10);
            ingredientLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
            ingredientLabel.setVerticalTextPosition(SwingConstants.CENTER);

            if( i%2 == 0 ) {
                gbc2.gridx = 0;
                gbc2.gridy = 3 + i;
            }
            else {
                gbc2.gridx = 1;
                gbc2.gridy = 3 + (i-1);
            }
            gbc2.gridwidth = 1;
            gbc2.insets = new Insets(2, 2, 10, 10);
            ingredientsLabelsList.add(ingredientLabel);
            ressourcesPanel.add(ingredientLabel, gbc2);
        }
        this.add(ressourcesPanel);
    }

    /**
     * Creates the buttons panel to collect and sell, buy and add bread
     */
    private void createButtonsPanel(){
        // === Panel buttons ===
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
        buttonPanel.setBackground(new Color(  202, 200, 196 ));

        GridBagConstraints gbc = new GridBagConstraints();
        //spaces between buttons
        gbc.insets = new Insets(0, 10, 30, 10);

        // Create buttons
        JButton collectButton = new JButton("Collect & Sell");
        collectButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc.gridx = 0;
        gbc.gridy = 0;
        collectButton.setFocusable(false);
        collectButton.addActionListener(bakerControl);
        buttonPanel.add(collectButton, gbc);

        JButton buyButton = new JButton("Buy");
        buyButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc.gridy++;
        buyButton.setFocusable(false);
        buyButton.addActionListener(bakerControl);
        buttonPanel.add(buyButton, gbc);

        JButton AddButton = new JButton("Add Bread");
        AddButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc.gridy++;
        AddButton.setFocusable(false);
        AddButton.addActionListener(bakerControl);
        buttonPanel.add(AddButton, gbc);


        // Add the buttonPanel to the Bakerpanel
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createMapPanel(){
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridBagLayout());
        mapPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
        mapPanel.setBackground(new Color( 182, 179, 175 ));

        GridBagConstraints gbc = new GridBagConstraints();
        //spaces between buttons
        gbc.insets = new Insets(0, 10, 30, 10);

        //Label showing GOAL of the bakery
        JLabel goalLabel = new JLabel("Goal : " + bakerControl.getBakery().GOAL);
        goalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        goalLabel.setBounds(0, 0, 200, 50);
        goalLabel.setForeground(Color.BLACK);
        goalLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // time label with image and progress bar
        ImageIcon timeIcon = new ImageIcon(getClass().getResource("/img/time.png"));
        int newWidthTime = 50;
        int newHeightTime = 50;
        Image scaledImageTime = timeIcon.getImage().getScaledInstance(newWidthTime, newHeightTime, Image.SCALE_SMOOTH);
        ImageIcon scaledTimeIcon = new ImageIcon(scaledImageTime);
        JLabel timeLabel = new JLabel(scaledTimeIcon);

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setMaximum(TIME); // Valeur maximale
        progressBar.setValue(EndGame.timeLeft); // Valeur initiale
        timeLabel.add(progressBar);

        // jpanel with time image and progress bar
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setOpaque(false);
        timePanel.add(timeLabel);
        timePanel.add(progressBar);


        //this.add(mapPanel);


    }

    /**
     * Update the ressources panel
     */
    public void update(){
        moneyLabel.setText("Money:  " + bakerControl.getBakery().getPlayer().getMoney()+"€");
        for ( JLabel j : ingredientsLabelsList) {
            j.setText(" : " + bakerControl.getBakery().getPlayer().getRessources(). //HashMap des ingrédients du baker
                    get(RESSOURCES[ingredientsLabelsList.indexOf(j)]));
        }
    }
}