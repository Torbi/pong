package pong.model;

public abstract class AbstractPositionable implements IPositionable{

    private double x,y;
    private double dy;
    private double dx;

    public AbstractPositionable(double x, double y) {
        this.x = x;
        this.y = y;

        move();
    }

    @Override
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

    @Override
    public double getWidth() {
        return 1;
    }

    @Override
    public double getHeight() {
        return 1;
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
