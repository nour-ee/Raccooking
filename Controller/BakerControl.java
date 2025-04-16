package Controller;

import Model.*;
import View.BakerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Optional;

/** Control the baker panel in particular buttons */
public class BakerControl implements ActionListener {

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
            case "BakeBread"  ->{
                bake("Bread");
                break;
            }
            case "BakeCroissant" -> {
                bake("Croissant");
                break;
            }
            case "BakeBrioche" -> {
                bake("Brioche");
                break;
            }
            case "Collect & Sell" -> {
                bakery.collect();
                break;
            }
            case "Buy" -> {// print combo box
                // JPanel for the check boxes
                JPanel panel = new JPanel(new GridLayout(0, 3,10,5));

                // Create the check boxes
                JCheckBox[] checkBoxes = new JCheckBox[BakerPanel.RESSOURCES.length];
                JSpinner[] spinners = new JSpinner[BakerPanel.RESSOURCES.length]; // add quantity of ressources
                JLabel[] prices = new JLabel[BakerPanel.RESSOURCES.length]; // add prices of ressources

                for (int i = 0; i < BakerPanel.RESSOURCES.length; i++) {
                    checkBoxes[i] = new JCheckBox(BakerPanel.RESSOURCES[i]);
                    JSpinner spinner = new JSpinner(new SpinnerNumberModel(1,0 , 100, 1));
                    spinner.setEnabled(false); // disable spinner by default
                    spinners[i] = spinner;
                    JLabel price;
                    switch (BakerPanel.RESSOURCES[i]){
                        case "flour" : price = new JLabel("2€"); prices[i] = price; break;
                        case "egg" : price = new JLabel("4€"); prices[i] = price; break;
                        case "yeast" : price = new JLabel("5€"); prices[i] = price; break;
                        case "butter" : price = new JLabel("3€"); prices[i] = price; break;
                    }

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
                    panel.add(prices[i]);
                }

                // Show the dialog with the check boxes
                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Choose ressource(s) you need...",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // Action to do if the user click on OK
                if (result == JOptionPane.OK_OPTION) {
                    for (int i = 0; i < checkBoxes.length; i++) {
                        if (checkBoxes[i].isSelected()) {
                            bakery.getPlayer().buy(checkBoxes[i].getText(), (Integer) spinners[i].getValue());
                        }
                    }
                }
                ;break;}
        }
    }
/**
     * Method to bake a type of bread
     * @param type the type of bread to bake
     */
public void bake(String type){
    HashMap<String, Integer> recipe= BakedGoods.getRecipe(type);
    Baker b= bakery.getPlayer();
    if(b.canBake(recipe)){
        Optional<Oven> o= bakery.hasFreeOven();
        if(o.isPresent()){
            o.get().addBakedGoods(type);
            b.spendRessources(recipe);
        }
    }
}

}

