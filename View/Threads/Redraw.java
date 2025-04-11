package View.Threads;

import View.BakerPanel;
import View.Display;

public class Redraw extends Thread{
    private Display myDisplay;
    private BakerPanel bkPanel;
    public static final int DELAY = 50;

    public Redraw(Display d, BakerPanel bk){
        myDisplay = d;
        bkPanel = bk;
    }


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
