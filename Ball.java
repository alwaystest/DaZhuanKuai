
import java.awt.*;
import java.awt.event.*;

public class Ball{
	int x,y;
	boolean X=true,Y=true;
	
	public Ball(int x, int y){
		this.x=x;
		this.y=y;//≥ı ºŒª÷√
		//u=(x+10)/2;
		//t=(y+10)/2;
		}
	
	public void draw(Graphics g){
		
		Color c=g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval(x,y,10,10);
		g.setColor(c);
		
		if(X==true && Y==true)
			{x+=5;y+=5;}
		else if(X==true &&Y==false)
			{x+=5;y-=5;}
		else if(X==false &&Y==true)
			{x-=5;y+=5;}
		else if(X==false &&Y==false)
			{x-=5;y-=5;}
			
		move();
	}
	
	public void move(){
		if(x<=0)
			X=true;
		else if(y>=400)
			Y=false;
		else if(x>=600)
			X=false;
		else if(y<=0)
			Y=true;
	}
	

}