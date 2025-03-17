import Controller.RaccoonLife;
import Controller.RaccoonMovement;
import Controller.RaccoonThread;
import Model.Bakery;
import View.Display;
import View.Redraw;

public class Main {

    public static void main(String[] args) {

        //Creation of the bakery
        Bakery bakery = new Bakery();
        Display display = new Display(bakery);
        
        //Starting the threads
        RaccoonMovement rm = new RaccoonMovement(display.getBakery());
        rm.start();
        RaccoonLife rl = new RaccoonLife(display.getBakery());
        rl.start();
        Redraw redraw = new Redraw(display, display.getBakerPanel());
        redraw.start();

    }
}