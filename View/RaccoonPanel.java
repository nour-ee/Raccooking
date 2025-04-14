 package View;

import javax.swing.*;

import Model.Raccoon;

import java.awt.*;

    /** Panel for the raccoon */
    public class RaccoonPanel extends JPanel {
        /****************
         *  ATTRIBUTES  *
         ****************/
        private JProgressBar progressBar; // can be used to manage the progress of the raccoon in the frame (time before disappearing)
        private Raccoon raccoon ; // the raccoon is it connected to
        private JLabel breadLabel;
        private JLabel croissantLabel;
        private JLabel briocheLabel;

        /****************
         *  CONSTANTS   *
         ****************/
        public static final int HEIGHT = BakerPanel.HEIGHT;
        public static final int WIDTH = BakerPanel.WIDTH;

        /****************
         *  CONSTRUCTOR  *
         ****************/
        public RaccoonPanel(Raccoon r) {
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            this.setBackground(new Color(182, 179, 175));
            this.raccoon = r;

            // add image of raccoon in panel
            ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/raccoon.png")); // add as attribute for dissepearing raccoon

            // Resize the image
            int newWidth = 150;
            int newHeight = 150;
            Image scaledImage = bakerIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledBakerIcon = new ImageIcon(scaledImage);

            JLabel bakerLabel = new JLabel(scaledBakerIcon);
            this.add(bakerLabel, BorderLayout.NORTH);

            // === info Panel  ===
            createInfoPanel();

        }

        /****************
         *  METHODS      *
         ****************/
        /**
         * Method to create the info panel
         * It will create the panel with the time and the goods
         */

        private void createInfoPanel() {
            // === info Panel  ===
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new GridBagLayout());
            infoPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
            infoPanel.setBackground(new Color(182, 179, 175));
            GridBagConstraints gbc2 = new GridBagConstraints();

            // time label with image and progress bar
            JLabel timeLabel = new JLabel(createEntityIcon("time.png", 50, 50));
            gbc2.gridx = 0;
            gbc2.gridy = 0;
            gbc2.insets = new Insets(2, 2, 10, 10);

            // Progress bar
            progressBar = new JProgressBar();
            progressBar.setPreferredSize(new Dimension(150, 20));
            progressBar.setMaximum(Raccoon.MAX_AGE); // Valeur maximale
            progressBar.setValue(Raccoon.MAX_AGE-this.raccoon.getAge()); // Valeur initiale


            // jpanel with time image and progress bar
            JPanel timePanel = new JPanel();
            timePanel.setLayout(new FlowLayout());
            timePanel.setOpaque(false);
            timePanel.add(timeLabel);
            timePanel.add(progressBar);
            infoPanel.add(timePanel, gbc2);

            gbc2.gridy++;
            infoPanel.add(createGoodsPanel(), gbc2); // add the goods panel to the info panel

            // add the panel to the raccoon panel
            this.add(infoPanel, BorderLayout.CENTER);

        }
        /**
         * Method to create the goods panel
         * It will create the panel with the goods of the raccoon
         */
        public JPanel createGoodsPanel() {
            // === goods Panel  ===
            JPanel goodsPanel = new JPanel();
            goodsPanel.setLayout(new GridBagLayout());
            goodsPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
            goodsPanel.setBackground(new Color(182, 179, 175));
            GridBagConstraints gbcGoods = new GridBagConstraints();

            // bread label with image and number
            breadLabel = new JLabel(createEntityIcon("breadCooked.png", 50, 50));
            gbcGoods.gridx = 0;
            gbcGoods.gridy = 1;
            gbcGoods.insets = new Insets(2, 2, 10, 10);
            breadLabel.setText(""+raccoon.getBread()); // add attribut for number of bread in raccoon ==> get it from raccoon
            // add the label to the panel
            goodsPanel.add(breadLabel, gbcGoods);

            // croissant label with image and number
            croissantLabel = new JLabel(createEntityIcon("croissantCooked.png", 50, 50));
            gbcGoods.gridx = 1;
            gbcGoods.gridy = 1;
            gbcGoods.insets = new Insets(2, 2, 10, 10);
            croissantLabel.setText(""+raccoon.getCroissant());
            // add the label to the panel
            goodsPanel.add(croissantLabel, gbcGoods);

            // brioche label with image and number
            briocheLabel = new JLabel(createEntityIcon("briocheCooked.png", 50, 50));
            gbcGoods.gridx = 2;
            gbcGoods.gridy = 1;
            gbcGoods.insets = new Insets(2, 2, 10, 10);
            briocheLabel.setText(""+raccoon.getBrioche());
            // add the label to the panel
            goodsPanel.add(briocheLabel, gbcGoods);

            return goodsPanel;
        }
        /**
         * Method to create the background image
         * @param name : name of the image
         * @return : the image icon
         */
        private ImageIcon createEntityIcon(String name , int width, int height) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/" + name));
            // Resize the image
            Image imgIcon = icon.getImage();
            Image newImg = imgIcon.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        }

        /**
         * Method to update the raccoon panel
         */
        public void update() {
            // update the progress bar
            progressBar.setValue(Raccoon.MAX_AGE-this.raccoon.getAge());
            // update the goods panel
            breadLabel.setText(""+raccoon.getBread());
            croissantLabel.setText(""+raccoon.getCroissant());
            briocheLabel.setText(""+raccoon.getBrioche());
        }
    }


