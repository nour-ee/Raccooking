package Controller;

import View.RaccoonPanel;

import javax.swing.*;

import Model.Raccoon;

/** Thread to update the progress bar */
public class RaccoonThread extends Thread{
    /****************
     *  ATTRIBUTES  *
     ****************/
    private RaccoonPanel raccoonPanel;

    /********************
     *    CONSTRUCTOR   *
     ********************/
    public RaccoonThread(RaccoonPanel raccoonPanel){
        this.raccoonPanel = raccoonPanel;
    }

    /****************
     *    METHODS   *
     ****************/
    /**
     * Method to update the progress bar
     */
    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> raccoonPanel.getProgressBar().setValue(Raccoon.MAX_AGE-this.raccoonPanel.getRaccoon().getAge()));
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

