package View;

import Controller.EndGame;
import Model.Bakery;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Begin extends JFrame {

    private boolean sound;
    public static Clip playSound; // sound when the game starts

    //private JPanel panelButton; // panel for the button
    private LevelPanel levelPanel; // panel for the level

    public static final int BEGIN_W = 800;
    public static final int BEGIN_H = 600;

    /****************
     *  CONSTRUCTOR *
     ****************/
    public Begin() {
        this.sound = true;

        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);
        layeredPane.setBounds(0, 0, BEGIN_W, BEGIN_H);
        layeredPane.setLayout(null);

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
        levelPanel = new LevelPanel();
        layeredPane.add(fond,Integer.valueOf(0));
        levelPanel.setLocation(BEGIN_W/2-150, 150);
        layeredPane.add(levelPanel,Integer.valueOf(1));
        //create the buttons
        createPlayButton(layeredPane);
        createSoundButton(new ImageIcon("img/sound-on.png"),layeredPane);
        setVisible(true);
    }

    /** Create the play button*/
    public void createPlayButton(JLayeredPane layeredPane) {
        JButton playB = new JButton("Play");
        playB.addActionListener(e -> {
            this.setVisible(false);
            if(sound) {
                PlaySound();
            }
            Bakery b=new Bakery("levels/"+levelPanel.getCurrentLevel()+".txt");
            Display d=new Display(b);
            EndGame end = new EndGame(d);
            end.start();
        });
        playB.setBounds(BEGIN_W/2-50, 330, 100, 50);
        layeredPane.add(playB,Integer.valueOf(1));
    }
    /** Create the sound button **/
    public void createSoundButton(ImageIcon img, JLayeredPane layeredPane) {
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
        layeredPane.add(soundB, Integer.valueOf(1));
    }
    private void PlaySound() {
        File file = new File("sound/sound_play.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            playSound = AudioSystem.getClip();
            playSound.open(audioStream);
            playSound.loop(Clip.LOOP_CONTINUOUSLY); // Loop the sound
            playSound.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
