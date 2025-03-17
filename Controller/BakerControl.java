package Controller;

import Model.Baker;
import Model.Bakery;
import Model.Oven;
import View.BakerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/** Control the baker panel in particular buttons */
public class BakerControl implements ActionListener {

    /****************
     *  ATTRIBUTES  *
     ****************/
    private BakerPanel bkpanel;
    private Bakery bakery;

    /****************
     *    GETTERS   *
     ****************/
    public Bakery getBakery() {
        return bakery;
    }

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public BakerControl(BakerPanel p, Bakery bakery){
        this.bkpanel = p;
        this.bakery = bakery;

    }

    /****************
     *    METHODS   *
     ****************/

    /**
     * Method to manage the actions of the buttons
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Collect & Sell" -> {
                System.out.println("Collect");
                bakery.collectBread();
                ;break;}
            case "Buy" -> {// print combo box
                String input = (String) JOptionPane.showInputDialog(null, "Choose one element you need...",
                        "The Choice of a ressource", JOptionPane.QUESTION_MESSAGE, null,
                        BakerPanel.RESSOURCES, // Array of choices
                        BakerPanel.RESSOURCES[1]); // Initial choice
                System.out.println("Buy " + input); // print the choice
                bakery.getPlayer().buy(input);
                ;break;}

            //if the player has enough ressources, and if there is a free oven, the player can bake bread
            case "Add Bread" -> {System.out.println("Add Bread");
                Baker b= bakery.getPlayer();
                if(b.canBake()){
                    Optional<Oven> o= bakery.hasFreeOven();
                    if(o.isPresent()){
                        o.get().addBread();
                        b.spendRessources();
                    }
                    else{ System.out.println("No free oven"); }
                }
                else{System.out.println("Not enough ressources to bake bread");}
                break;}
        }

    }


}

