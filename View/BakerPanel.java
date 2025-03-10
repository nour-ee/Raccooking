package View;

import Controller.BakerControl;

import javax.swing.*;
import java.awt.*;


/** Manage interaction with the baker panel */
public class BakerPanel extends JPanel {
    private BakerControl bakerControl;

    public static final int HEIGHT = 600;
    public static final int WIDTH = 300;

    public static final int BUTTON_HEIGHT = 50;
    public static final int BUTTON_WIDTH = 200;
    public static final String[] RESSOURCES = {"Flour", "Egg", "Baker's yeast", "Butter"};

    public BakerPanel() {
        this.bakerControl = new BakerControl(this);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color( 182, 179, 175 ));

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

    // private methods to create the ressources panel and the buttons panel
    private void createRessourcesPanel() {
        // === Panel ressources ===
        JPanel ressourcesPanel = new JPanel();
        ressourcesPanel.setLayout(new GridBagLayout());
        ressourcesPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
        ressourcesPanel.setBackground(new Color( 182, 179, 175 ));
        GridBagConstraints gbc2 = new GridBagConstraints();

        // money label
        JLabel moneyLabel = new JLabel("Money: 1000$");
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(2, 2, 10, 10);
        ressourcesPanel.add(moneyLabel, gbc2);

        // bread label
        JLabel breadLabel = new JLabel("Bread: 50");
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(2, 2, 10, 10);
        ressourcesPanel.add(breadLabel, gbc2);
        this.add(ressourcesPanel);
    }

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
        collectButton.addActionListener(bakerControl);
        buttonPanel.add(collectButton, gbc);

        JButton buyButton = new JButton("Buy");
        buyButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        gbc.gridy++;
        buyButton.addActionListener(bakerControl);
        buttonPanel.add(buyButton, gbc);


        // Add the buttonPanel to the Bakerpanel
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}