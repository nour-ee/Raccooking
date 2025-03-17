package Controller;

import View.RaccoonPanel;

import javax.swing.*;

import Model.Raccoon;

/** Thread to update the progress bar */
public class RaccoonThread extends Thread{
    private RaccoonPanel raccoonPanel;
    public RaccoonThread(RaccoonPanel raccoonPanel){
        this.raccoonPanel = raccoonPanel;
    }
    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> raccoonPanel.getProgressBar().setValue(Raccoon.MAX_AGE-this.raccoonPanel.getRaccoon().getAge())); // Update the progress bar on the EDT
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

