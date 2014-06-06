
import java.awt.*;
import java.awt.event.*;

public class Ball{
	int x,y;
	private boolean live = true;
	private boolean X=true,Y=true;
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
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
		return new Rectangle(x-R, y-R, R+R, R+R);//�ӷ��ٶȱȳ˷���
	}
	
	public boolean hitwall(Wall w){
		if(this.getRect().intersects(w.getRect())) {//getRect�������Σ������ж���ײ��intersects���������жϾ����Ƿ��ཻ��api��tank1.6
			Y=false;//�ж��������ײ�Ժ�ı�����˶�����
			//System.out.println(x+"  "+y);
			
			return true;
		}
		return false;
	}
	
	public boolean hitzk(ZK zk){
		if(this.getRect().intersects(zk.getRect())&&zk.getLive()==true) {//getRect�������Σ������ж���ײ��intersects���������жϾ����Ƿ��ཻ��api��tank1.6
			if(y-YSPEED<=zk.getTop())
			Y=false;//�ж��������ײ�Ժ�ı�����˶�����,����
			else if(y+YSPEED>=zk.getBottom())
			Y=true;//�ж��������ײ�Ժ�ı�����˶�����,����
			if(x-XSPEED<zk.getLeft()){
				System.out.println(x-XSPEED);
			X=false;//�ж��������ײ�Ժ�ı�����˶�����,����
			}
			else if(x+XSPEED>zk.getRight()){
				System.out.println(x+XSPEED);
			X=true;//�ж��������ײ�Ժ�ı�����˶�����,����
			}//�ⲿ���߼��������ƣ���Ҫball�Ĳ���Сһ�㣬ש���һ�㣬�����жϳ�����ȫû������ж��߼�������

			zk.setLive(false);
			//System.out.println(x+"  "+y);
			
			return true;
		}
		return false;
	}
	
}