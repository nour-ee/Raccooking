package View;

import Model.Bakery;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JFrame {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private Display display;
    private Bakery bakery;
    private int GOAL;
    public static final int END_W = 800;
    public static final int END_H = 600;

    /****************
     *  CONSTRUCTOR *
     ****************/
    public EndScreen(Display display) {
        if(Begin.sound){
        Begin.playSound.stop(); // stop the sound
        }
        this.display = display;
        this.bakery = display.getBakery();
        bakery.getPlayer();
        this.GOAL = Bakery.GOAL;

        System.out.println("GOAL : " + GOAL);

        this.display.setVisible(false);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);
        layeredPane.setBounds(0, 0, END_W, END_H);
        layeredPane.setLayout(null);

        // set the frame
        setTitle("Raccooking");
        setSize(END_W, END_H);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        int money = bakery.getPlayer().getMoney();
        bakery.gameEnded();
        // text that shows the amount of money the player has
        JLabel moneyLabel = new JLabel("Goal : "+GOAL+"€    Your earnings : " + money + "€");
        moneyLabel.setFont(new Font("Arial", Font.BOLD, 20));
        moneyLabel.setBounds(END_W / 2 - 180, 0, 350, 50);
        moneyLabel.setForeground(Color.BLACK);
        //moneyLabel.setOpaque(false);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(moneyLabel, Integer.valueOf(1));

        //if the player has more than bakery.GOAL money, show "You win !" else show "You don't have enough money... Game over :("
        if (money >= GOAL) {
            //add background image
            JLabel winBackground = new JLabel(createBackgroundImage("win"));
            winBackground.setBounds(0, 0, END_W, END_H);
            layeredPane.add(winBackground, Integer.valueOf(0));
            JLabel winLabel = new JLabel("YOU WIN !");
            winLabel.setFont(new Font("Arial", Font.BOLD, 20));
            winLabel.setBounds(END_W / 2 , END_H/12, 300, 50);
            winLabel.setForeground(Color.BLACK);
            //winLabel.setOpaque(false);
            winLabel.setHorizontalAlignment(SwingConstants.CENTER);
            layeredPane.add(winLabel, Integer.valueOf(1));
        } else {
            //add background image
            JLabel loseBackground = new JLabel(createBackgroundImage("lose"));
            loseBackground.setBounds(0, 0, END_W, END_H);
            layeredPane.add(loseBackground, Integer.valueOf(0));
            JLabel loseLabel = new JLabel("You don't have enough money... Game over :(");
            loseLabel.setFont(new Font("Arial", Font.BOLD, 20));
            loseLabel.setBounds(END_W / 2 - 260, END_H/20, 500, 50);
            loseLabel.setForeground(Color.BLACK);
            loseLabel.setOpaque(false);
            loseLabel.setHorizontalAlignment(SwingConstants.CENTER);
            layeredPane.add(loseLabel, Integer.valueOf(1));
        }

        //on ajoute un bouton "Rejouer" qui relance le jeu lorsque l'on clique dessus (ferme cette fenêtre et met un nouvel écran de démarrage Begin)
        JButton replayButton = new JButton("Replay");
        replayButton.addActionListener(e -> {
            this.setVisible(false);
            Begin begin = new Begin();
            begin.setVisible(true);
        });
        replayButton.setBounds(END_W / 2 - 50, END_H / 2 + 100, 100, 50);
        layeredPane.add(replayButton, Integer.valueOf(1));
        //on ajoute un bouton "Quitter" qui ferme le jeu lorsque l'on clique dessus
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> {
            this.setVisible(false);
            System.exit(0);
        });
        quitButton.setBounds(END_W / 2 - 50, END_H / 2 + 170, 100, 50);
        layeredPane.add(quitButton, Integer.valueOf(1));

        setVisible(true);
    }

    private ImageIcon createBackgroundImage(String name) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/"+name+".png"));
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(END_W, END_H, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

}
