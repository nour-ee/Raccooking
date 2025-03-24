package View;

import javax.swing.*;
import java.awt.*;

public class Begin extends JFrame {

    private boolean begin;
    private boolean sound;
    private Display display;

    public static final int BEGIN_W = 800;
    public static final int BEGIN_H = 600;


    public Begin(Display display){
        this.display = display;
        this.sound = true;
        setTitle("Raccooking");
        setSize(BEGIN_W, BEGIN_H);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.getContentPane().setBackground(new Color(255, 235, 182, 150));

        creePlayButton(BEGIN_W/2-90, 300, 100, 50);
        creeSoundButton(BEGIN_W/2+25, 300, 100, 50, new ImageIcon("img/sound-on.png"));
        setVisible(true);
    }
    public boolean getBegin(){ return begin; }

    public void creePlayButton(int x, int y, int w, int h) {
        JButton playB = new JButton("Play");
        playB.addActionListener(e -> {
            begin = true;
            this.setVisible(false);
            display.setVisible(true);
        });
        playB.setBounds(x, y, w, h);
        this.add(playB);
    }
    public void creeSoundButton(int x, int y, int w, int h, ImageIcon img) {
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
        soundB.setBounds(x, y, w, h);
        this.add(soundB);
    }

    /*public void chooseLevel(){
        String[] levels = {"level1.txt", "level2.txt", "level3.txt"};
        JComboBox<String> levelList = new JComboBox<>(levels);
        levelList.setSelectedIndex(0);
        levelList.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            String level = (String)cb.getSelectedItem();
            switch (level){
                case "Level 1":
                    display.setBakery(new Bakery(1));
                    break;
                case "Level 2":
                    display.setBakery(new Bakery(2));
                    break;
                case "Level 3":
                    display.setBakery(new Bakery(3));
                    break;
            }
        });
        levelList.setBounds(BEGIN_W/2-50, 400, 100, 50);
        this.add(levelList);

    }*/
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Raccooking", BEGIN_W/2-50, 200);

    }
}
