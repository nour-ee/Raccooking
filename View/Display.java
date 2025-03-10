package View;
import Controller.EntityControl;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    public static final int TILE_SIZE = 75;   //size of a tile, in pixels (square tile)
    private BakerPanel bkPanel;
    private RaccoonPanel rcPanel;

    private EntityControl entityControl;

    private JLabel bakerLabel;
    private JLabel raccoonLabel;
    public static final int FRAME_H = 600;
    public static final int FRAME_W = 900;
    public Display() {
        setTitle("Raccooking");
        setSize(FRAME_W, FRAME_H);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // Add the Bakerpanel to the Bakery frame
        bkPanel = new BakerPanel();
        bkPanel.setVisible(false);


        //Add the imaginary baker to the Bakery frame for testing
        ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/baker.png"));
        int newWidth = 150;
        int newHeight = 150;
        Image scaledImage = bakerIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBakerIcon = new ImageIcon(scaledImage);
        bakerLabel = new JLabel(scaledBakerIcon);
        add(bakerLabel, BorderLayout.WEST);




        // Add the RaccoonPanel to the Bakery frame
        rcPanel = new RaccoonPanel();
        rcPanel.setVisible(false);


        //Add the imaginary raccoon to the Bakery frame for testing
        ImageIcon raccoonIcon = new ImageIcon(getClass().getResource("/img/raccoon.png"));
        int newWidthRaccoon = 150;
        int newHeightRaccoon = 150;
        Image scaledImageRaccoon = raccoonIcon.getImage().getScaledInstance(newWidthRaccoon, newHeightRaccoon, Image.SCALE_SMOOTH);
        ImageIcon scaledRaccoonIcon = new ImageIcon(scaledImageRaccoon);
        raccoonLabel = new JLabel(scaledRaccoonIcon);
        add(raccoonLabel, BorderLayout.CENTER);


        // Manage interaction with bakery elements
        entityControl = new EntityControl(rcPanel, bkPanel, this);
        // bakerpanel appears on the right when I click on bakerlabel
        bakerLabel.addMouseListener(entityControl);
        // raccoonpanel appears on the right when I click on raccoonlabel
        raccoonLabel.addMouseListener(entityControl);

        //set frame visible
        setVisible(true);
    }
    public RaccoonPanel getRaccoonPanel() {
        return rcPanel;
    }

    public JLabel getBakerLabel() {
        return bakerLabel;
    }
    public JLabel getRaccoonLabel() {
        return raccoonLabel;
    }


}
