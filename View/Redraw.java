package View;

public class Redraw extends Thread{
    private Display myDisplay;
    private BakerPanel bkPanel;
    private RaccoonPanel rcPanel;
    public static final int DELAY = 50;

    public Redraw(Display d, BakerPanel bk, RaccoonPanel rc){
        myDisplay = d;
        bkPanel = bk;
        rcPanel = rc;
    }

    @Override
    public void run(){
        while(true){
            myDisplay.repaint();
            bkPanel.update();
            rcPanel.repaint();
            try{
                Thread.sleep(DELAY);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
