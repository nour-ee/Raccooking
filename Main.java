import Controller.RaccoonLife;
import Controller.RaccoonMovement;
import Controller.RaccoonThread;
import Model.Bakery;
import View.*;

public class Main {

    public static void main(String[] args) {

        //Creation of the bakery
        LevelPanel levelPanel = new LevelPanel();
        //Bakery bakery = new Bakery(levelPanel);
        System.out.println(levelPanel.getBakery().getLevelFile());
        Display display = new Display(levelPanel.getBakery());
        System.out.println(levelPanel.getBakery().getLevelFile());
        Begin begin = new Begin(display,levelPanel);

        //Starting the threads
        RaccoonMovement rm = new RaccoonMovement(display.getBakery());
        rm.start();
        RaccoonLife rl = new RaccoonLife(display.getBakery());
        rl.start();
        Redraw redraw = new Redraw(display, display.getBakerPanel());
        redraw.start();
        Animation anim = new Animation(levelPanel);
        anim.start();

    }
}