package pong.model;

import static pong.model.Pong.GAME_HEIGHT;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle extends AbstractPositionable{

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;


    public Paddle(double x, double y) {
       super(x, y);
    }

    @Override
    public void move() {

        setY(getY() + getDy());

        if(!(this.getY() + PADDLE_HEIGHT <= GAME_HEIGHT && this.getY() >= 0)) {
            setY(getY() - getDy());
        }
    }

    @Override
    public double getWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public double getHeight() {
        return PADDLE_HEIGHT;
    }

}
