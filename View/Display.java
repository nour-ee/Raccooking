package View;
import Controller.EntityControl;
import Model.Bakery;
import Model.Raccoon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {
    public static final int TILE_SIZE = 75;   //size of a tile, in pixels (square tile)
    public static final int MARGIN = 10; //margin around the bakery, in pixels
    private BakerPanel bkPanel;
    private RaccoonPanel rcPanel;

    private EntityControl entityControl;

    private Bakery bakery ;

    private JLabel bakerLabel;
    //private JLabel raccoonLabel;

    private ArrayList<JLabel> raccoonLabels = new ArrayList<JLabel>();
    public static final int FRAME_H = Bakery.BAKERY_H*TILE_SIZE + 2*MARGIN;
    public static final int FRAME_W = Bakery.BAKERY_W*TILE_SIZE + 2*MARGIN;
    public Display() {
        setTitle("Raccooking");
        setSize(FRAME_W+BakerPanel.WIDTH, FRAME_H);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a bakery
        bakery = new Bakery();

        // Add the Bakerpanel to the Bakery frame
        bkPanel = new BakerPanel(bakery);
        bkPanel.setVisible(false);


        //Add the baker to the Bakery frame for testing
        ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/baker.png"));
        int newWidth = TILE_SIZE;
        int newHeight = TILE_SIZE;
        Image scaledImage = bakerIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBakerIcon = new ImageIcon(scaledImage);
        bakerLabel = new JLabel(scaledBakerIcon);
        bakerLabel.setBounds(coord(bakery.getPlayer().getPosition().getX(), bakery.getPlayer().getPosition().getY()).x, coord(bakery.getPlayer().getPosition().getX(), bakery.getPlayer().getPosition().getY()).y, newWidth, newHeight);
        add(bakerLabel);
        // Add the RaccoonPanel to the Bakery frame
        rcPanel = new RaccoonPanel();
        rcPanel.setVisible(false);


        //Add raccoons to the Bakery frame
        placeRaccoons();

        // Manage interaction with bakery elements
        entityControl = new EntityControl(rcPanel, bkPanel, this);
        // bakerpanel appears on the right when I click on bakerlabel
        bakerLabel.addMouseListener(entityControl);
        // raccoonpanel appears on the right when I click on raccoonlabel
        //raccoonLabel.addMouseListener(entityControl);

        //set frame visible
        setVisible(true);
    }
    public RaccoonPanel getRaccoonPanel() {
        return rcPanel;
    }

    public JLabel getBakerLabel() {
        return bakerLabel;
    }
    /*public JLabel getRaccoonLabel() {
        return raccoonLabel;
    }*/


    public BakerPanel getBakerPanel() {
        return bkPanel;
    }

    public ArrayList<JLabel> getRaccoonLabels() {
        return raccoonLabels;
    }

    public Point coord(int x, int y) {
        return new Point(x*TILE_SIZE, y*TILE_SIZE);

    }
    private void placeRaccoons() {
        Raccoon[] rc = bakery.getRaccoons();
        for (Raccoon r : rc) {
            int x = r.getPosition().getX();
            int y = r.getPosition().getY();

            ImageIcon raccoonIcon = new ImageIcon(getClass().getResource("/img/raccoon.png"));
            int newWidth = TILE_SIZE;
            int newHeight = TILE_SIZE;
            Image scaledImage = raccoonIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledRaccoonIcon = new ImageIcon(scaledImage);

            JLabel raccoonLabel = new JLabel(scaledRaccoonIcon);
            raccoonLabel.setBounds(coord(x, y).x, coord(x, y).y, newWidth, newHeight);
            raccoonLabel.addMouseListener(entityControl);
            raccoonLabels.add(raccoonLabel);
            add(raccoonLabel);
        }
    }
}
