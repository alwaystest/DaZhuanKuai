package eric;

import java.awt.*;

public class Ball {
    private double x, y;
    private boolean isAlive = true;
    private boolean moveToRight = true, moveToDown = true;
    private int count;

    private static int XSpeed = 5;

    private static int YSpeed = 5;
    public static int R = 10;

    public Ball() {
        this.x = 0;
        this.y = 0;
        count = 0;
    }

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (isAlive) {
            Color c = g.getColor();
            g.setColor(Color.YELLOW);
            g.fillOval((int)x, (int)y, R, R);
            g.setColor(c);
        }
    }

    public void move(double dt) {
        if (moveToDown)
            y += YSpeed * dt;
        else
            y -= YSpeed * dt;
        if (moveToRight)
            x += XSpeed * dt;
        else
            x -= XSpeed * dt;
//        System.out.println(x + "\t" + y);
    }

    public Rectangle getRect() {
        return new Rectangle((int)x - R, (int) y - R, R + R, R + R);// 加法速度比乘法快
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getCount() {
        return count;
    }

    public int getXSpeed() {
        return XSpeed;
    }

    public int getYSpeed() {
        return YSpeed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isMoveToDown() {
        return moveToDown;
    }

    public boolean isMoveToRight() {
        return moveToRight;
    }

    public boolean bounceOffVertical(Event e) {
        boolean flag = false;
        Brick brick = e.getBrick();
        if(brick == null){
            flag = true;
            this.count++;
            this.moveToRight = ! this.moveToRight;
        }else{
            if(brick.isAlive() && this.y >= brick.getTop() - R && this.y <= brick.getBottom() + R) {
                flag = true;
                this.count++;
                this.moveToRight = !this.moveToRight;
                brick.setLive(false);
            }
        }
        return flag;
    }

    public boolean bounceOffHorizontal(Event e,Wall myWall) {
        boolean flag = false;
        Brick brick = e.getBrick();
        if(brick == null){
            if(this.moveToDown && this.x >= myWall.getX() && this.x <= myWall.getX() + Wall.WIDTH) {
                flag = true;
                this.count++;
                this.moveToDown = false;
            }else if(!this.moveToDown){
                flag = true;
                this.count++;
                this.moveToDown = true;
            }
        }else{
            if(brick.isAlive() && this.x >= brick.getLeft() - R && this.x <= brick.getRight() + R){
                flag = true;
                this.count++;
                this.moveToDown = ! this.moveToDown;
                brick.setLive(false);
            }
        }
        return flag;
    }
}