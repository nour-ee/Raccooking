package View;

import javax.swing.*;
import java.awt.*;

/** Panel for the raccoon */
public class RaccoonPanel extends JPanel {
    private JProgressBar progressBar; // can be used to manage the progress of the raccoon in the frame (time before disappearing)

    public static final int HEIGHT = BakerPanel.HEIGHT;
    public static final int WIDTH = BakerPanel.WIDTH;

    public RaccoonPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(182, 179, 175));


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

    public JProgressBar getProgressBar() {
        return progressBar;
    }


    private void createInfoPanel() {
        // === info Panel  ===
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setPreferredSize(new Dimension(WIDTH - 40, 300));
        infoPanel.setBackground(new Color(182, 179, 175));
        GridBagConstraints gbc2 = new GridBagConstraints();


        // time label with image and progress bar
        ImageIcon timeIcon = new ImageIcon(getClass().getResource("/img/time.png"));
        int newWidthTime = 50;
        int newHeightTime = 50;
        Image scaledImageTime = timeIcon.getImage().getScaledInstance(newWidthTime, newHeightTime, Image.SCALE_SMOOTH);
        ImageIcon scaledTimeIcon = new ImageIcon(scaledImageTime);
        JLabel timeLabel = new JLabel(scaledTimeIcon);
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.insets = new Insets(2, 2, 10, 10);

        // Progress bar
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(150, 20));
        progressBar.setValue(100); // Valeur initiale
        timeLabel.add(progressBar);

        // jpanel with time image and progress bar
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new FlowLayout());
        timePanel.setOpaque(false);
        timePanel.add(timeLabel);
        timePanel.add(progressBar);
        infoPanel.add(timePanel, gbc2);


        // bread label with image and number
        ImageIcon breadIcon = new ImageIcon(getClass().getResource("/img/bread.png"));
        int newWidthBread = 50;
        int newHeightBread = 50;
        Image scaledImageBread = breadIcon.getImage().getScaledInstance(newWidthBread, newHeightBread, Image.SCALE_SMOOTH);
        ImageIcon scaledBreadIcon = new ImageIcon(scaledImageBread);
        JLabel breadLabel = new JLabel(scaledBreadIcon);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(2, 2, 10, 10);
        breadLabel.setText("50"); // add attribut for number of bread in raccoon ==> get it from raccoon

        infoPanel.add(breadLabel, gbc2);
        this.add(infoPanel);

    }
}
