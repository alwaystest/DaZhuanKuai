
import java.awt.*;
import java.awt.event.*;

public class Ball{
	int x,y;
	private boolean live = true;
	private boolean X=true,Y=true;
	public static final int XSPEED = 2;
	public static final int YSPEED = 2;
	public static final int R=10;
	public Ball(int x, int y){
		this.x=x;
		this.y=y;
		//u=(x+10)/2;
		//t=(y+10)/2;
		}
	
	public void draw(Graphics g){
		if(live==true){
			Color c=g.getColor();
			g.setColor(Color.YELLOW);
			g.fillOval(x,y,R,R);
			g.setColor(c);	
			move();
		}
	}
	
	public void move(){
		if(x<=0)
			X=true;
		else if(y>=400)
			live=false;
		else if(x>=585)
			X=false;
		else if(y<=25)
			Y=true;
		if(X==true && Y==true)
			{x+=XSPEED;y+=YSPEED;}
		else if(X==true &&Y==false)
			{x+=XSPEED;y-=YSPEED;}
		else if(X==false &&Y==true)
			{x-=XSPEED;y+=YSPEED;}
		else if(X==false &&Y==false)
			{x-=XSPEED;y-=YSPEED;}
		//System.out.println(x+"  "+y);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, R, R);
	}
	
	public boolean hitwall(Wall w){
		if(this.getRect().intersects(w.getRect())) {//getRect创建矩形，用于判断碰撞，intersects方法用于判断矩形是否相交见api与tank1.6
			Y=false;//判断球与板碰撞以后改变球的运动方向
			//System.out.println(x+"  "+y);
			
			return true;
		}
		return false;
	}
	
	public boolean hitzk(ZK zk){
		if(this.getRect().intersects(zk.getRect())&&zk.getLive()==true) {//getRect创建矩形，用于判断碰撞，intersects方法用于判断矩形是否相交见api与tank1.6
			Y=false;//判断球与板碰撞以后改变球的运动方向
			zk.setLive(false);
			//System.out.println(x+"  "+y);
			
			return true;
		}
		return false;
	}
	
}