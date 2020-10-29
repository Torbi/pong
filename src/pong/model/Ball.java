package pong.model;

import java.util.Random;

import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;

/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball extends AbstractPositionable{

    public static final double WIDTH = 40;
    public static final double HEIGHT = 40;


    public Ball(double x, double y){
        super(x, y);
    }

    /*
    Creates a new ball with a random speed and direction
    betwen 1.5 and 3 for dx
    1.25 and 2.5 for dy
     */
    public void setRandom() {
        double randX = ((Math.random() + 1) * 1.5);
        double randY = ((Math.random() + 1) * 1.25);
        int rndDirX = new Random().nextBoolean()? 1 : -1;
        int rndDirY = new Random().nextBoolean()? 1 : -1;
        setDx(randX * rndDirX);
        setDy(randY * rndDirY);
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }
}
