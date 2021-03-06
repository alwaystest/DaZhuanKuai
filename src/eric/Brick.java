package eric;

import java.awt.*;

public class Brick {
	private boolean isAlive = true;
	int x, y;
	private int WIDTH = 30;
	private int HEIGHT = 30;

	Brick(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		if (!isAlive)
			return;
		Color c = g.getColor();
		g.setColor(Color.blue);
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(Color.green);
		g.drawRect(x, y, WIDTH, HEIGHT);
		g.setColor(c);
	}

	public void setLive(boolean live) {
		this.isAlive = live;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getTop() {
		return y;
	}

	public int getBottom() {
		return y + HEIGHT;
	}

	public int getLeft() {
		// System.out.println(x);
		return x;
	}

	public int getRight() {
		// System.out.println(x+WIDTH);
		return x + WIDTH;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public String toString(){
		return new String("("+x+",\t"+y+")");
	}
}
