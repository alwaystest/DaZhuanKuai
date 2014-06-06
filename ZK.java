import java.awt.*;
import java.awt.event.*;

public class ZK{
	private boolean live=true;
	int x,y;
	private int WIDTH=30;//Ô­À´Îª100
	private int HEIGHT=30;
	
	ZK(int x,int y){
		this.x=x;
		this.y=y;
	}

	public void draw(Graphics g){
		if(!live) return;
		Color c=g.getColor();
		g.setColor(Color.blue);
		g.fillRect(x,y,WIDTH,HEIGHT);
		g.setColor(Color.green);
		g.drawRect(x,y,WIDTH,HEIGHT);
		g.setColor(c);
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean getLive(){
		return live;
	}
	
	public int  getTop(){
		return y;
	}
	
	public int  getBottom(){
		return y+HEIGHT;
	}
	
		public int  getLeft(){
			System.out.println(x);
		return x;
	}
	
	public int  getRight(){
		System.out.println(x+WIDTH);
		return x+WIDTH;
	}

	public Rectangle getRect(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
}

