package eric;

import java.awt.*;

public class Ball {
	private int x, y;
	private boolean isAlive = true;
	private boolean moveToRight = true, moveToDown = true;
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	public static final int R = 10;
	
	public Ball(){
		this.x = 0;
		this.y = 0;
	}

	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		if (isAlive == true) {
			Color c = g.getColor();
			g.setColor(Color.YELLOW);
			g.fillOval(x, y, R, R);
			g.setColor(c);
			move();
		}
	}

	public void move() {
//		TODO: 根据窗口大小判断
		if (x <= 0)
			moveToRight = true;
		else if (y >= 400)
			isAlive = false;
		else if (x >= 585)
			moveToRight = false;
		else if (y <= 25)
			moveToDown = true;
		if(moveToDown)
			y += YSPEED;
		else
			y -= YSPEED;
		if(moveToRight)
			x += XSPEED;
		else
			x -= XSPEED;
	}

	public Rectangle getRect() {
		return new Rectangle(x - R, y - R, R + R, R + R);// 加法速度比乘法快
	}

	public boolean isHitWall(Wall w) {
		if(!moveToDown){
			return false;//TODO: return not be used
		}
		if (this.getRect().intersects(w.getRect())) {// getRect创建矩形，用于判断碰撞，intersects方法用于判断矩形是否相交见api与tank1.6
			moveToDown = false;// 判断球与板碰撞以后改变球的运动方向
			return true;
		}
		return false;
	}

	public boolean isHitBrick(Brick brick) {
		// TODO: change to event driven
		if (this.getRect().intersects(brick.getRect()) && brick.getLive() == true) {// getRect创建矩形，用于判断碰撞，intersects方法用于判断矩形是否相交见api与tank1.6
			if (y - YSPEED <= brick.getTop())
				moveToDown = false;// 判断球与板碰撞以后改变球的运动方向,向上
			else if (y + YSPEED >= brick.getBottom())
				moveToDown = true;// 判断球与板碰撞以后改变球的运动方向,向下
			if (x - XSPEED < brick.getLeft()) {
				// System.out.println(x-XSPEED);
				moveToRight = false;// 判断球与板碰撞以后改变球的运动方向,向左
			} else if (x + XSPEED > brick.getRight()) {
				// System.out.println(x+XSPEED);
				moveToRight = true;// 判断球与板碰撞以后改变球的运动方向,向右
			}// 这部分逻辑不够完善，需要ball的步进小一点，砖块大一点，否则判断出错，完全没问题的判断逻辑不好想
			brick.setLive(false);
			return true;
		}
		return false;
	}

}