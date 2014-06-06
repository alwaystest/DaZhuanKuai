
import java.awt.*;
import java.awt.event.*;


public class Wall{
	int x;
	
	public Wall(int x){
	this.x=x;
	}
	
public void draw(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawLine(x,379,x+40,379);
		g.drawLine(x,380,x+40,380);//两条线当做下面的板子
		g.setColor(c);
	}
	
	

		public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		{
		if(x>=5&&x<=555){	
				switch(key) {
					case KeyEvent.VK_LEFT :
						x -= 5;
						break;

					case KeyEvent.VK_RIGHT :
						x += 5;
						break;

				}
			}
		else
			if(x<5){
				switch(key) {
				case KeyEvent.VK_RIGHT:
					x+= 5;
					break;
				}		
			}
			else
				if(x>555){
				switch(key) {
					case KeyEvent.VK_LEFT :
						x -= 5;
						break;
					}
				}
			}	
		}
}