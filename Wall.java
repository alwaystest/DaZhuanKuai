/*
*2014.01.25；
*试图解决板子移动第一下慢的问题。
*目前板子自动执行第一下动作，无法更改；
*晚上再解决；
*/
import java.awt.*;
import java.awt.event.*;


public class Wall{
	int x;//初始位置
	
	private boolean live = true;
	
	private boolean bL=false,bR=false;
	enum Direction {L,R, STOP};
	
	private Direction dir = Direction.STOP;
	
	public Wall(int x){
	this.x=x;
	}
	
public void draw(Graphics g) {
		if(!live) return;//判断板子是否存活，死亡就不用再画了
		
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawLine(x,379,x+40,379);
		g.drawLine(x,380,x+40,380);//两条线当做下面的板子
		g.setColor(c);
	
		move();
	}
	
	void move() {
		switch(dir) {
		case L:
			x -= 20;
			break;
		case R:
			x += 20;
			break;
		case STOP:
			break;
		}
		
		if(x < 0) x = 0;
		if(x+40>595) x=555;
	}

		
		
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
				switch(key) {
					case KeyEvent.VK_LEFT :
						bL=true;
						break;

					case KeyEvent.VK_RIGHT :
						bR=true;
						break;

				}
	
			locateDirection();
	}
		
		void locateDirection() {
			if(bL) dir = Direction.L;
			else if(bR) dir = Direction.R;
			else if(!bL && !bR) dir = Direction.STOP;
		} 
		
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_LEFT :
				bL = false;
				break;
			case KeyEvent.VK_RIGHT :
				bR = false;
				break;
			}
			locateDirection();
		}
}