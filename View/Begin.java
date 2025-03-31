package View;

import javax.swing.*;
import java.awt.*;

public class Begin extends JFrame {

    private boolean sound;
    private Display display;

    //private JPanel panelButton; // panel for the button
    private LevelPanel panelLevel; // panel for the level

    public static final int BEGIN_W = 800;
    public static final int BEGIN_H = 600;

    /****************
     *  CONSTRUCTOR *
     ****************/
    public Begin(Display display,LevelPanel levelPanel) {
        this.display = display;
        this.sound = true;
        this.panelLevel = levelPanel;

        ImageIcon icon = new ImageIcon("img/Raccooking.png");

        // resize the image
        Image imgIcon = icon.getImage();
        Image newImg = imgIcon.getScaledInstance(BEGIN_W, BEGIN_H, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        // set the frame
        setTitle("Raccooking");
        setSize(BEGIN_W, BEGIN_H);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // create the image of the background
        JLabel fond = new JLabel(icon);
        fond.setBounds(0,0,getWidth(),getHeight());
        //add the image to the frame
        this.add(fond);
        panelLevel.setLocation(BEGIN_W/2-150, 150);
        this.add(panelLevel);
        //create the buttons
        createPlayButton();
        createSoundButton(new ImageIcon("img/sound-on.png"));
        setVisible(true);
    }

    /** Create the play button*/
    public void createPlayButton() {
        JButton playB = new JButton("Play");
        playB.addActionListener(e -> {
            this.setVisible(false);
            display.setVisible(true);
        });
        playB.setBounds(BEGIN_W/2-50, 330, 100, 50);
        this.add(playB);
    }
    /** Create the sound button **/
    public void createSoundButton(ImageIcon img) {
        JButton soundB = new JButton();
        soundB.setIcon(img);
        soundB.addActionListener(e -> {
            if(sound) {
                sound = false;
                soundB.setIcon(new ImageIcon("img/sound-off.png"));
            }
            else{
                sound = true;
                soundB.setIcon(new ImageIcon("img/sound-on.png"));
            }
        });
        soundB.setBounds(BEGIN_W/2-50, 400, 100, 50);
        this.add(soundB);
    }
}
