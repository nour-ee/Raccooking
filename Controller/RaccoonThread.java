package Controller;

import View.RaccoonPanel;

import javax.swing.*;

/** Thread to update the progress bar */
public class RaccoonThread extends Thread{
    private RaccoonPanel raccoonPanel;
    public RaccoonThread(RaccoonPanel raccoonPanel){
        this.raccoonPanel = raccoonPanel;
    }
    @Override
    public void run() {
        int i = 100;
        while (i >= 0) {
            int finalI = i;
            SwingUtilities.invokeLater(() -> raccoonPanel.getProgressBar().setValue(finalI)); // Update the progress bar on the EDT
            i -= 5;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

