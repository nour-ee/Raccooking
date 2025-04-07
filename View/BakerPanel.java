package View;

import Controller.BakerControl;
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
    private JLabel breadLabel;
    private ArrayList<JLabel> ingredientsLabelsList;    //to store our ingredients labels
                                                        //to update them easily
    /*****************
     *   CONSTANTS  *
     *****************/

    public static final int HEIGHT = 650;
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

        // bread label
        breadLabel = new JLabel("Bread : 50");
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(2, 2, 10, 10);
        ressourcesPanel.add(breadLabel, gbc2);


        // ingredients labels
        JLabel ressourcesLabel = new JLabel("Ingrédients :");
        gbc2.gridx = 0; gbc2.gridy =  2;
        gbc2.gridwidth = 2;
        gbc2.insets = new Insets(2, 2, 10, 10);
        ressourcesPanel.add(ressourcesLabel, gbc2);

        // display the ingredients
        for (int i = 0; i < RESSOURCES.length; i++) {
            JLabel ingredientLabel = new JLabel(RESSOURCES[i] + ": " + bakerControl.getBakery().getPlayer().getRessources().get(RESSOURCES[i]));
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




        // Create Bread
        ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/cooked.png"));
        Image scaledImage = bakerIcon.getImage().getScaledInstance(BUTTON_HEIGHT,BUTTON_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon breadIcon = new ImageIcon(scaledImage);
        JButton bakeBread = new JButton(breadIcon);
        bakeBread.setPreferredSize(new Dimension(BUTTON_HEIGHT, BUTTON_HEIGHT));
        gbc.gridy++;
        bakeBread.setFocusable(false);
        bakeBread.addActionListener(bakerControl);
        buttonPanel.add(bakeBread, gbc);
        JLabel bakeBreadLabel = new JLabel("1 flour, 1 yeast");
        gbc.gridx++;
        buttonPanel.add(bakeBreadLabel, gbc);
        gbc.gridx--;


        // Add the buttonPanel to the Bakerpanel
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Update the ressources panel
     */
    public void update(){
        moneyLabel.setText("Money:  " + bakerControl.getBakery().getPlayer().getMoney()+"$");
        breadLabel.setText("Bread: " + bakerControl.getBakery().getPlayer().getSoldBread());
        for ( JLabel j : ingredientsLabelsList) {
            j.setText(RESSOURCES[ingredientsLabelsList.indexOf(j)]
                    + ": " + bakerControl.getBakery().getPlayer().getRessources(). //HashMap des ingrédients du baker
                    get(RESSOURCES[ingredientsLabelsList.indexOf(j)]));
        }
    }
}