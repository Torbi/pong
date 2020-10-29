package pong.model;


//abstract class for all objects in the game such as paddles, floor and ceiling
public abstract class AbstractPositionable implements IPositionable{

    private double x,y;
    private double dy;
    private double dx;

    public AbstractPositionable(double x, double y) {
        this.x = x;
        this.y = y;
        move();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void move(){
        x += dx;
        y += dy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    //all subclasses implements their own getwidth and height, these are just for the interface
    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }

    public double getDy() {
        return dy;
    }

    public double getDx() {
        return dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }
}
