package eric;

/*
 *2014.01.26.2:00；
 *已解决。
 *可判断板与球的碰撞，详见intersects（）；API及TANK1.6
 */
import java.awt.*;
import java.awt.event.*;

public class Wall {
	int x, y;
	public static final int SPEED = 8;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 2;
	private boolean isAlive = true;

	private boolean bL = false, bR = false;

	enum Direction {
		L, R, STOP
	};

	private Direction dir = Direction.STOP;

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		if (!isAlive)
			return;// 判断板子是否存活，死亡就不用再画了

		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawRect(x, y, WIDTH, HEIGHT);// 改动，用矩形代替板子，便于判断碰撞事件,好像一个框框好看一点
		// g.fillRect(x,y,WIDTH,HEIGHT);
		g.setColor(c);
		move();
	}

	void setlocation(int x) throws HeadlessException {
		x -= (WIDTH >> 1);
		if (x < 0) {
			this.x = 0;
			return;
		}
		if (x + WIDTH > GAME.GAME_WIDTH) {
			this.x = GAME.GAME_WIDTH - WIDTH;
			return;
		}
		this.x = x;
	}

	void move() {
		switch (dir) {
		case L:
			x -= SPEED;
			break;
		case R:
			x += SPEED;
			break;
		case STOP:
			break;
		}

		if (x < 0)
			x = 0;
		if (x + WIDTH > GAME.GAME_WIDTH)
			x = GAME.GAME_WIDTH - WIDTH;// TODO:bug1
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:
			bL = true;
			break;

		case KeyEvent.VK_RIGHT:
			bR = true;
			break;

		}
		locateDirection();
	}

	private void locateDirection() {
		if (bL)
			dir = Direction.L;
		else if (bR)
			dir = Direction.R;
		else if (!bL && !bR)
			dir = Direction.STOP;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		}
		locateDirection();
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT + 2);
	}
}
