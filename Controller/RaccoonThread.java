package Controller;

import View.RaccoonPanel;


/** Thread to update the progress bar and goods stolen */
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
            raccoonPanel.update(); // Update the progress bar and the number of items stolen
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

