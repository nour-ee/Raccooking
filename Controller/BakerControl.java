package Controller;

import Model.Baker;
import Model.Bakery;
import Model.Oven;
import View.BakerPanel;

import javax.swing.*;
import java.awt.*;
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
                // JPanel for the check boxes
                JPanel panel = new JPanel(new GridLayout(0, 2));

                // Create the check boxes
                JCheckBox[] checkBoxes = new JCheckBox[BakerPanel.RESSOURCES.length];

                for (int i = 0; i < BakerPanel.RESSOURCES.length; i++) {
                    checkBoxes[i] = new JCheckBox(BakerPanel.RESSOURCES[i]);
                    JTextField textField = new JTextField(); // a ajouter sous forme de liste
                    panel.add(checkBoxes[i]);
                    panel.add(textField);
                }

                // Show the dialog with the check boxes
                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Choose ressource(s) you need...",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // Action to do if the user click on OK
                if (result == JOptionPane.OK_OPTION) {
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            bakery.getPlayer().buy(checkBox.getText());
                        }
                    }
                }
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

