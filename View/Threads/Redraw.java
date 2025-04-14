package View.Threads;

import View.BakerPanel;
import View.Display;
/**
 * Redraw class to manage the animation of the game
 * It will update the display and the baker panel
 */
public class Redraw extends Thread{
    /****************
     *  ATTRIBUTES  *
     ****************/
    private Display myDisplay;
    private BakerPanel bkPanel;
    /****************
     *  CONSTANTS   *
     ****************/
    public static final int DELAY = 50;

    /****************
     *  CONSTRUCTOR  *
     ****************/
    public Redraw(Display d, BakerPanel bk){
        myDisplay = d;
        bkPanel = bk;
    }

    /**
     * Method to run the animation
     * It will update the display and the baker panel
     */
    @Override
    public void run(){
        while(true){
            myDisplay.repaint();
            bkPanel.update();
            try{
                Thread.sleep(DELAY);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
