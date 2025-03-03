import java.awt.*;

public class bread {
    public enum State {
        COOKING, COOKED, BURNT
    }
    private State state ;
    private Point position;

    public bread(Point position) {
        this.position = position;
        this.state = State.COOKING;
    }

}
