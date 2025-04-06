package Controller;

import Model.Bread;
import View.Display;
import View.EndScreen;

import static Model.Bread.T_BURNT;
import static Model.Bread.T_COOKED;

public class EndGame extends Thread {
    private static final int TIME = 30;
    private int timeLeft;
    private Display display;

    public EndGame(Display display) {
        this.display = display;
        this.timeLeft = TIME;
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
