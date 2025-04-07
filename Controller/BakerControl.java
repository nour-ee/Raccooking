package Controller;

import Model.Baker;
import Model.Bakery;
import Model.Bread;
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
        switch(((JButton)e.getSource()).getName()){
            case "BakeBread" ->{
                Baker b= bakery.getPlayer();
                if(Bread.canBake(b.getRessources())){
                    Optional<Oven> o= bakery.hasFreeOven();
                    if(o.isPresent()){
                        o.get().addBread();
                        b.spendRessources(Bread.getRecipe());
                    }
                    else{ System.out.println("No free oven"); }
                    }
                    else{System.out.println("Not enough ressources to bake bread");}
                    break;
            }
            case "Collect & Sell" -> {
                System.out.println("Collect");
                bakery.collectBread();
                ;break;
            }
            case "Buy" -> {// print combo box
                // JPanel for the check boxes
                JPanel panel = new JPanel(new GridLayout(0, 2,10,5));

                // Create the check boxes
                JCheckBox[] checkBoxes = new JCheckBox[BakerPanel.RESSOURCES.length];
                JSpinner[] spinners = new JSpinner[BakerPanel.RESSOURCES.length]; // add quantity of ressources

                for (int i = 0; i < BakerPanel.RESSOURCES.length; i++) {
                    checkBoxes[i] = new JCheckBox(BakerPanel.RESSOURCES[i]);
                    JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,0 , 100, 1));
                    spinner.setEnabled(false); // disable spinner by default
                    spinners[i] = spinner;

                    checkBoxes[i].addItemListener(evt -> {
                        JCheckBox source = (JCheckBox) evt.getSource();
                        for (int j = 0; j < checkBoxes.length; j++) {
                            if (checkBoxes[j] == source) {
                                spinners[j].setEnabled(source.isSelected());
                                break;
                            }
                        }
                    });
                    panel.add(checkBoxes[i]);
                    panel.add(spinner);
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
        }
    }


}

