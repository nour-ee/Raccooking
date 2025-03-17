package Controller;
import View.BakerPanel;
import View.Display;
import View.RaccoonPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Controls the visibility of the panel entities
 * */
public class EntityControl extends MouseAdapter {

    /****************
     *  ATTRIBUTES  *
     ****************/
    BakerPanel bkPanel;
    Display display;

    /********************
     *    CONSTRUCTOR   *
     ********************/

    public EntityControl( BakerPanel bkPanel, Display display) {
        this.bkPanel = bkPanel;
        this.display = display;
    }

    /****************
     *    METHODS   *
     ****************/

    /**
     * Method to manage the interactions with the entities
     * to print the corresponding panel
     * @param e the action event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        JLabel bakerLabel = display.getBakerLabel();
        ArrayList<JLabel> raccoonLabels = display.getRaccoonLabels();

        if (source == bakerLabel) { // Verify if the click comes from the baker label
            System.out.println("Baker clicked!");
            setAllNonVisible();
            bkPanel.setBounds(Display.FRAME_W, 0, BakerPanel.WIDTH, BakerPanel.HEIGHT);
            bkPanel.requestFocus();
            display.add(bkPanel);
            bkPanel.setVisible(true);
        } else {
            for(int i =0; i<raccoonLabels.size();i++){
                RaccoonPanel rc = display.getRaccoonPanels().get(i);
                if(source == raccoonLabels.get(i)){
                    System.out.println("Raccoon clicked!");
                    bkPanel.setVisible(false);
                    setNonVisible(i);
                    rc.setBounds(Display.FRAME_W, 0, RaccoonPanel.WIDTH, RaccoonPanel.HEIGHT); // Position the panel off-screen to the right
                    display.add(rc);
                    rc.setVisible(true);
                }
            }
        }
    }

    /**
     * Makes all RaccoonPanels except the one we want non visible
     */
    public void setNonVisible(int i){
        for(int j = 0; j<display.getRaccoonPanels().size();j++){
            if(j!=i){
                display.getRaccoonPanels().get(j).setVisible(false);
            }
        }
    }

    /**
     * Makes all RaccoonPanels non visible
     */
    public void setAllNonVisible(){
        for(int j = 0; j<display.getRaccoonPanels().size();j++){
            display.getRaccoonPanels().get(j).setVisible(false);
        }
    }
}
