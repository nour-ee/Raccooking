import Controller.RaccoonThread;
import View.Display;

public class Main {

    //I WAS JUST TESTING SMTH
    //can either delete or refactor this class you do you
    public static void main(String[] args) {
       // System.out.println("Hello world!");
        Display display = new Display();
        RaccoonThread raccoonThread = new RaccoonThread(display.getRaccoonPanel());
        raccoonThread.start();
    }
}
