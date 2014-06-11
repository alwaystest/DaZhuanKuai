/*
*2014.01.26.2:00��
*�ѽ����
*���жϰ��������ײ�����intersects������API��TANK1.6
*/
import java.awt.*;
import java.awt.event.*;


public class Wall{
	int x,y;
	public static final int SPEED = 8;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 2;
	private boolean live = true;
	
	private boolean bL=false,bR=false;
	enum Direction {L,R, STOP};
	
	private Direction dir = Direction.STOP;
	
	public Wall(int x,int y){
	this.x=x;
	this.y=y;
	}
	
public void draw(Graphics g) {
		if(!live) return;//�жϰ����Ƿ�������Ͳ����ٻ���
		
		Color c = g.getColor();
		g.setColor(Color.RED);
		//g.drawLine(x,379,x+40,379);
		g.drawRect(x,y,WIDTH,HEIGHT);//�Ķ����þ��δ�����ӣ������ж���ײ�¼�,����һ�����ÿ�һ��
		//g.fillRect(x,y,WIDTH,HEIGHT);
		g.setColor(c);
		//setlocation();//到game的adapter中调用
		move();
		/*Point mp=g.getMousePosition();
		if (mp!=null)
			x=mp.x;
		else
			move();*/
	}
	
	void setlocation(Point mousepoint) throws HeadlessException{
		//PointerInfo tmp;
		//tmp=getPointerInfo();
		//Point mousepoint = MouseInfo.getPointerInfo().getLocation(); //获取到的是全局的坐标
		x=mousepoint.x;
		//x=y;
	}

	void move() {
		switch(dir) {
		case L:
			x -= SPEED;
			break;
		case R:
			x += SPEED;
			break;
		case STOP:
			break;
		}
		
		if(x < 0) x = 0;
		if(x+WIDTH>595) x=595-WIDTH;//bug1
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
		
		public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT+2);
	}
}
