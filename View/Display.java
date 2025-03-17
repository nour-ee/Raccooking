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
    //panels that appear when clicking on the entities
    private BakerPanel bkPanel;
    private RaccoonPanel rcPanel;

    private EntityControl entityControl;

    private BakerMovement bakerMovement; //thread to move the baker

    private Bakery bakery ; //the bakery aka map

    //labels to display the entities
    private JLabel bakerLabel;
    private ArrayList<JLabel> raccoonLabels = new ArrayList<JLabel>();
    private ArrayList<RaccoonPanel> raccoonPanels = new ArrayList<RaccoonPanel>();
    private ArrayList<JLabel> breadLabels = new ArrayList<JLabel>();

    //constants of our display
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
        initBread();

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
    public Bakery getBakery() { return bakery;}


    /****************
     *    METHODS   *
     ****************/

    public Point coord(int x, int y) {
        return new Point(x*TILE_SIZE, y*TILE_SIZE);

    }

    /** Places the baker in the bakery by accessing their position
     * **/
    private void placeBaker() {
        //gets the baker and their position in the bakery
        Baker b = bakery.getPlayer();
        int x = b.getPosition().getX();
        int y = b.getPosition().getY();

        //The image representing our baker :
        ImageIcon bakerIcon = new ImageIcon(getClass().getResource("/img/baker.png"));
        int newWidth = TILE_SIZE;
        int newHeight = TILE_SIZE;
        Image scaledImage = bakerIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledBakerIcon = new ImageIcon(scaledImage);

        bakerLabel = new JLabel(scaledBakerIcon);
        bakerLabel.setBounds(coord(x, y).x, coord(x, y).y, newWidth, newHeight);
        bakerLabel.setFocusable(true);

        // bakerpanel appears on the right when bakerlabel is clicked
        bakerLabel.addMouseListener(entityControl);
        bakerLabel.addKeyListener(bakerMovement);

        add(bakerLabel);
    }

    /**
     * Places raccoons in bakery by accessing their positions
     * **/
    private void placeRaccoons() {
        Raccoon[] rc = bakery.getRaccoons();
        for (Raccoon r : rc) {
            System.out.println(r.getPosition().getX() + " " + r.getPosition().getY());
            int x = r.getPosition().getX();
            int y = r.getPosition().getY();

            ImageIcon raccoonIcon = new ImageIcon(getClass().getResource("/img/raccoon.png"));
            int newWidth = TILE_SIZE;
            int newHeight = TILE_SIZE;
            Image scaledImage = raccoonIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledRaccoonIcon = new ImageIcon(scaledImage);

            JLabel raccoonLabel = new JLabel(scaledRaccoonIcon);
            raccoonLabel.setBounds(coord(x, y).x, coord(x, y).y, newWidth, newHeight);

            //Create raccoon panel for each raccoon
            RaccoonPanel raccoonPanel = new RaccoonPanel();
            raccoonPanel.setVisible(false);

            //RaccoonPanel appears on the right when RaccoonLabel is clicked
            raccoonLabel.addMouseListener(entityControl);

            //Identify which label and panel corresponds to which  raccoon with indexes
            raccoonLabels.add(raccoonLabel);
            raccoonPanels.add(raccoonPanel);
            add(raccoonLabel);
            add(raccoonPanel);
        }
    }

    /**
     * Updates the raccoons to their new positions after they moved
     */
    private void repaintRaccoons(){
        for(int r=0;r<raccoonLabels.size();r++){
            int x = bakery.getRaccoons()[r].getPosition().getX();
            int y = bakery.getRaccoons()[r].getPosition().getY();
            raccoonLabels.get(r).setBounds(coord(x,y).x,coord(x,y).y,TILE_SIZE,TILE_SIZE);
        }
    }


    /**
     * Places breads in bakery by accessing the ovens and checking if they're occupied
     * **/
    private void initBread() {
        for (Oven o : bakery.getOvens()) {
            int newWidth = TILE_SIZE;
            int newHeight = TILE_SIZE;
            JLabel breadLabel = new JLabel();
            Point coord = coord(o.getX(), o.getY());
            breadLabel.setBounds(coord.x, coord.y, newWidth, newHeight);
            breadLabels.add(breadLabel);
            add(breadLabel);
        }
    }

    private void repaintBread () {
        for (int i = 0; i < bakery.getOvens().size(); i++) {
            Oven o = bakery.getOvens().get(i);
            if (o.isOccupied()) {
                Bread b = o.getBread();
                String filename="";
                switch(b.getState()) {
                    case COOKING:
                        filename = "/img/cooking.png";
                        break;
                    case COOKED:
                        filename = "/img/cooked.png";
                        break;
                    case BURNT:
                        filename = "/img/burnt.png";
                }
                ImageIcon breadIcon = new ImageIcon(getClass().getResource(filename));
                Image scaledImage = breadIcon.getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
                ImageIcon scaledBreadIcon = new ImageIcon(scaledImage);
                breadLabels.get(i).setIcon(scaledBreadIcon);
            }
            else{
                breadLabels.get(i).setIcon(null);
            }

            //draw square to represent an oven
            breadLabels.get(i).setBounds(coord(o.getX(), o.getY()).x, coord(o.getX(), o.getY()).y, TILE_SIZE, TILE_SIZE); 
            breadLabels.get(i).setOpaque(true);
            breadLabels.get(i).setBackground(Color.GRAY);
            breadLabels.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));

        }
    }

    /**
     * Paints the tiles of the bakery
     * **/
    public void paintTiles() {
        for (int i = 0; i < Bakery.BAKERY_W; i++) {

            for (int j = 0; j < Bakery.BAKERY_H; j++) {
                Tile t = bakery.getMap()[i][j];
                JLabel tileLabel = new JLabel();
                tileLabel.setBounds(coord(i, j).x, coord(i, j).y, TILE_SIZE, TILE_SIZE);
                if (t.isAccessible()) {
                    tileLabel.setOpaque(true);
                    tileLabel.setBackground(new Color(241, 132, 60, 161));
                }
                add(tileLabel);
            }

        }
    }

    /**
     * Paints the components of the bakery
     * @param g Graphics
    */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintTiles();

        //Update baker position :
        int x = bakery.getPlayer().getPosition().getX();
        int y = bakery.getPlayer().getPosition().getY();

        bakerLabel.setBounds(coord(x, y).x, coord(x, y).y, TILE_SIZE, TILE_SIZE);
        //bakerLabel.setIcon();  TODO ----------- LATER

        repaintRaccoons();
        repaintBread();
    }
}
