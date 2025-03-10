package Controller;

import Model.Bakery;
import View.BakerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Control the baker panel in particular buttons */
public class BakerControl implements ActionListener {
    private BakerPanel bkpanel;
    private Bakery bakery;

    public Bakery getBakery() {
        return bakery;
    }

    public BakerControl(BakerPanel p, Bakery bakery){
        this.bkpanel = p;
        this.bakery = bakery;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Collect & Sell" -> {System.out.println("Collect");break;}
            case "Buy" -> {// print combo box
                String input = (String) JOptionPane.showInputDialog(null, "Choose one element you need...",
                        "The Choice of a ressource", JOptionPane.QUESTION_MESSAGE, null,
                        BakerPanel.RESSOURCES, // Array of choices
                        BakerPanel.RESSOURCES[1]); // Initial choice
                System.out.println("Buy " + input); // à remplacer par  l'appel d'une methode qui stocke les ressources
                bakery.getPlayer().buy(input);
                System.out.println("Money: " + bakery.getPlayer().getMoney());
                ;break;}
        }

    }


}

