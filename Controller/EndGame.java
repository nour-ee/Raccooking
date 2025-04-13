package Controller;

import Model.Bakery;
import View.Begin;
import View.Display;
import View.EndScreen;

//Timer to end the game
public class EndGame extends Thread {
    public static int TIME ; // in seconds
    public static int timeLeft;
    private Display display;

    public EndGame(Display display) {
        this.display = display;
        TIME= Bakery.MAXTIME;
        EndGame.timeLeft = TIME;
    }

    @Override
    public void run() {
        while (timeLeft > 0) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Time left: " + timeLeft);
            timeLeft--;
        }
        System.out.println("Time's up!");
        EndScreen end = new EndScreen(display);
        end.setVisible(true);
    }
}
