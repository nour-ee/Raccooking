package Controller;

import View.Begin;
import View.Display;
import View.EndScreen;

//Timer to end the game
public class EndGame extends Thread {
    public static final int TIME = 40; // in seconds
    public static int timeLeft;
    private Display display;

    public EndGame(Display display) {
        this.display = display;
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
