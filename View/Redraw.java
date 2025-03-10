package View;

public class Redraw extends Thread{
    private Display myDisplay;
    public static final int DELAY = 50;

    public Redraw(Display d){
        myDisplay = d;
    }

    @Override
    public void run(){
        while(true){
            myDisplay.repaint();
            try{
                Thread.sleep(DELAY);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
