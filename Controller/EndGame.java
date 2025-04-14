package Controller;

import Model.Bakery;
import View.Display;
import View.EndScreen;

/**
 * Class to manage the end of the game with a timer
 */
public class EndGame extends Thread {
    /****************
     *  ATTRIBUTES  *
     ****************/
    public static int TIME ; // in seconds
    public static int timeLeft;
    private Display display;

    /***************
     * CONSTRUCTOR *
     * *************/
    public EndGame(Display display) {
        this.display = display;
        TIME= Bakery.MAXTIME;
        EndGame.timeLeft = TIME;
    }

    /****************
     *    METHODS   *
     ****************/
    /**
     * Method to start the timer thread
     */
    @Override
    public void run() {
        while (timeLeft > 0) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeLeft--; // Decrease the time left
        }
        // When the time is up, show the end screen
        EndScreen end = new EndScreen(display);
        end.setVisible(true);
    }
}
