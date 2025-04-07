package View;

import Model.Baker;
import Model.Bakery;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EndScreen extends JFrame {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private Display display;
    private Bakery bakery;
    private int GOAL;
    private Baker player;

    public static final int END_W = 800;
    public static final int END_H = 600;

    /****************
     *  CONSTRUCTOR *
     ****************/
    public EndScreen(Display display) {
        this.display = display;
        this.bakery = display.getBakery();
        this.player = bakery.getPlayer();
        this.GOAL = bakery.GOAL;

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
        moneyLabel.setBounds(END_W / 2 - 180, END_H / 2 - 40, 350, 50);
        moneyLabel.setForeground(Color.BLACK);
        //moneyLabel.setOpaque(true);
        moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        layeredPane.add(moneyLabel, Integer.valueOf(1));

        //if the player has more than bakery.GOAL money, show "You win !" else show "You don't have enough money... Game over :("
        if (money >= GOAL) {
            JLabel winLabel = new JLabel("You win !");
            winLabel.setFont(new Font("Arial", Font.BOLD, 20));
            winLabel.setBounds(END_W / 2 - 180, END_H / 2 -20, 300, 50);
            winLabel.setForeground(Color.BLACK);
            //winLabel.setOpaque(true);
            winLabel.setHorizontalAlignment(SwingConstants.CENTER);
            layeredPane.add(winLabel, Integer.valueOf(1));
        } else {
            JLabel loseLabel = new JLabel("You don't have enough money... Game over :(");
            loseLabel.setFont(new Font("Arial", Font.BOLD, 20));
            loseLabel.setBounds(END_W / 2 - 260, END_H / 2 + 30, 500, 50);
            loseLabel.setForeground(Color.BLACK);
            //loseLabel.setOpaque(true);
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


}
