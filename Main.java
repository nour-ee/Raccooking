import Controller.RaccoonThread;
import View.Display;
import View.Redraw;

public class Main {

    //I WAS JUST TESTING SMTH
    //can either delete or refactor this class you do you
    public static void main(String[] args) {
        Display display = new Display();
        RaccoonThread raccoonThread = new RaccoonThread(display.getRaccoonPanel());
        raccoonThread.start();
        Redraw redraw = new Redraw(display, display.getBakerPanel(), display.getRaccoonPanel());
        redraw.start();
    }
}