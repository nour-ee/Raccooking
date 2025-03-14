package View;
import Controller.BakerMovement;
import Controller.EntityControl;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private BakerPanel bkPanel;
    private RaccoonPanel rcPanel;

    private EntityControl entityControl;

    private BakerMovement bakerMovement;

    private Bakery bakery ;

    private JLabel bakerLabel;
    private ArrayList<JLabel> raccoonLabels = new ArrayList<JLabel>();
    private ArrayList<RaccoonPanel> raccoonPanels = new ArrayList<RaccoonPanel>();

    public static final int TILE_SIZE = 75;   //size of a tile, in pixels (square tile)
    public static final int MARGIN = 10; //margin around the bakery, in pixels
    public static final int FRAME_H = Bakery.BAKERY_H*TILE_SIZE + 2*MARGIN;
    public static final int FRAME_W = Bakery.BAKERY_W*TILE_SIZE + 2*MARGIN;

    /********************
     *    CONSTRUCTOR   *
     ********************/
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
        bakerMovement = new BakerMovement(bakery);


        // Add the RaccoonPanel to the Bakery frame
        rcPanel = new RaccoonPanel();
        rcPanel.setVisible(false);

        // Manage interaction with bakery elements
        entityControl = new EntityControl(rcPanel, bkPanel, this);

        // Add the Baker to the Bakery frame
        placeBaker();
        //Add raccoons to the Bakery frame
        placeRaccoons();
        //Add bread to the Bakery frame
        //placeBread();

        //set frame visible
        setVisible(true);
    }

    /****************
     *    GETTERS   *
     ****************/
    public RaccoonPanel getRaccoonPanel() {return rcPanel;}
    public JLabel getBakerLabel() {return bakerLabel;}
    public BakerPanel getBakerPanel() {return bkPanel;}
    public ArrayList<JLabel> getRaccoonLabels() {return raccoonLabels;}

    /****************
     *    METHODS   *
     ****************/

    public Point coord(int x, int y) {
        return new Point(x*TILE_SIZE, y*TILE_SIZE);

    }
    /**place the baker on the bakery by getting its position**/
    private void placeBaker() {
        Baker b = bakery.getPlayer();
        int x = b.getPosition().getX();
        int y = b.getPosition().getY();

        ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/baker.png"));
        int newWidth = TILE_SIZE;
        int newHeight = TILE_SIZE;
        Image scaledImage = bakerIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBakerIcon = new ImageIcon(scaledImage);

        bakerLabel = new JLabel(scaledBakerIcon);
        bakerLabel.setBounds(coord(x, y).x, coord(x, y).y, newWidth, newHeight);

        // bakerpanel appears on the right when I click on bakerlabel
        bakerLabel.addMouseListener(entityControl);
        bakerLabel.addKeyListener(bakerMovement);

        add(bakerLabel);
    }

    /**place the raccoons on the bakery by getting their positions**/
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

            //create raccoon panel for each raccoon
            RaccoonPanel raccoonPanel = new RaccoonPanel();
            raccoonPanel.setVisible(false);

            // raccoonpanel appears on the right when I click on raccoonlabel
            raccoonLabel.addMouseListener(entityControl);

            raccoonLabels.add(raccoonLabel);
            raccoonPanels.add(raccoonPanel);
            add(raccoonLabel);
            add(raccoonPanel);
        }
    }

    /**place the bread on the bakery by getting the ovens**/
    private void placeBread(){
        Tile[][] map = bakery.getMap();
        for (int i = 0; i < Bakery.BAKERY_H; i++) {
            for (int j = 0; j < Bakery.BAKERY_W; j++) {
                if (map[i][j].hasOven()) {
                    Oven o = (Oven) map[i][j];
                    if (o.isOccupied()) {
                        Bread b = o.getBread();
                        if (true) { //il y avait b.isCooked() avant ici mais c'est pas très logique donc en attendant
                            ImageIcon breadIcon = new ImageIcon(getClass().getResource("/img/bread.png"));
                            int newWidth = TILE_SIZE;
                            int newHeight = TILE_SIZE;
                            Image scaledImage = breadIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                            ImageIcon scaledBreadIcon = new ImageIcon(scaledImage);

                            JLabel breadLabel = new JLabel(scaledBreadIcon);
                            Point coord = coord(o.getX(),o.getY());
                            breadLabel.setBounds(coord.x, coord.y, newWidth, newHeight);
                            add(breadLabel);
                        }
                    }
                }
            }
        }
    }
    public void paint(Graphics g) {
        super.paint(g);
        placeBread();
        //update the baker position
        int x = bakery.getPlayer().getPosition().getX();
        int y = bakery.getPlayer().getPosition().getY();
        bakerLabel.setBounds(coord(x, y).x, coord(x, y).y, TILE_SIZE, TILE_SIZE);//la position reste toujours à 0,0
        placeRaccoons();

    }
}
