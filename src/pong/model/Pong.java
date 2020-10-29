package pong.model;


import pong.event.EventBus;
import pong.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;

/*
 * Logic for the Pong Game
 * Model class representing the "whole" game
 * Nothing visual here
 *
 */
public class Pong {

    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.02;
    public static final long HALF_SEC = 500;


    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;

    private Ball ball;
    private Paddle rightPaddle;
    private Paddle leftPaddle;

    private Ceiling ceiling;
    private Floor floor;

    // TODO Constructor

    public Pong(Ball ball, Paddle rightPaddle, Paddle leftPaddle, Ceiling ceiling, Floor floor) {
        this.ball = ball;
        this.rightPaddle = rightPaddle;
        this.leftPaddle = leftPaddle;
        this.ceiling = ceiling;
        this.floor = floor;
        ball.setRandom();
    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }
    // --------  Game Logic -------------

    private long timeForLastHit = System.currentTimeMillis();         // To avoid multiple collisions

    //main gameloop with logic
    //checks if ball has left the gameplan
    //also checks all the collisions and makes the ball and paddles move
    public void update(long now) {

        // tODO Gamelogic here
        if (ball.getX() > GAME_WIDTH) {
            pointsLeft++;
            ball = new Ball(GAME_WIDTH / 2 - ball.getWidth() / 2, GAME_HEIGHT / 2 - ball.getHeight() / 2);
            ball.setRandom();

        } else if (ball.getX() < -ball.getWidth()) {
            pointsRight++;
            ball = new Ball(GAME_WIDTH / 2 - ball.getWidth() / 2, GAME_HEIGHT / 2 - ball.getHeight() / 2);
            ball.setRandom();
        }
        leftPaddle.move();
        rightPaddle.move();
        ball.move();
        //Check collision med celling och floor
        checkCollision();
        //to avoid multiple hits in a short span
        if(System.currentTimeMillis() >= timeForLastHit + HALF_SEC){
            //collitions med paddles
            checkCollisionPaddle();
        }
    }

    //collision between ball and paddles, play sound upon hit and changes direction
    public void checkCollisionPaddle() {
        if ((ball.getX()  > rightPaddle.getX() - Ball.WIDTH)
                && (ball.getY() + ball.getHeight() > rightPaddle.getY())
                && (ball.getY() < rightPaddle.getY() + rightPaddle.getHeight())) {

            timeForLastHit = System.currentTimeMillis();
            ball.setDx(ball.getDx() * -1 * BALL_SPEED_FACTOR);
            EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_PADDLE);
        } else if (ball.getX() < leftPaddle.getX() + Paddle.PADDLE_WIDTH
                && ball.getY() + ball.getHeight() > leftPaddle.getY()
                && ball.getY() < leftPaddle.getY() + leftPaddle.getHeight()) {

            timeForLastHit = System.currentTimeMillis();
            ball.setDx(ball.getDx() * -1 * BALL_SPEED_FACTOR);
            EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_PADDLE);
        }
    }

    //collision check between ball and ceilings and floor, also plays sound upon collision
    public void checkCollision() {
        if (ball.getY() < 0) {
            ball.setDy(ball.getDy() * -1 * BALL_SPEED_FACTOR);
            EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_WALL_CEILING);
        } else if (ball.getY() > GAME_HEIGHT - Ball.HEIGHT) {
            ball.setDy(ball.getDy() * -1 * BALL_SPEED_FACTOR);
            EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_WALL_CEILING);
        }
    }


    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        drawables.add(rightPaddle);
        drawables.add(leftPaddle);
        drawables.add(ball);
        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;
    }

    public void setSpeedRightPaddle(double dy) {
        // TODO
        rightPaddle.setDy(dy);
    }

    public void setSpeedLeftPaddle(double dy) {
        // TODO
        leftPaddle.setDy(dy);
    }
}
