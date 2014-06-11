/*

*/

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GAME extends Frame {
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 400;
	
	 Wall MyWall = new Wall(200,380);
	 Ball MyBall = new Ball(90,90);
	 List<ZK> zk = new ArrayList<ZK>();
	 /*ZK zk[]={new ZK(25,25),new ZK(75,25),new ZK(125,25),new ZK(175,25),new ZK(225,25),new ZK(275,25),new ZK(325,25),new ZK(375,25),new ZK(425,25),new ZK(475,25),new ZK(525,25),new ZK(525,55),new ZK(475,55),new ZK(425,55),new ZK(375,55),new ZK(125,55)};*/
					
	 
	 
	Image offScreenImage = null;
	
	public void paint(Graphics g) {
		Point mousepoint=this.getMousePosition();
		MyWall.setlocation(mousepoint);
		MyWall.draw(g);
		MyBall.hitwall(MyWall);//判断球和板碰撞
		/*for(int i=0;i<16;i++)
		{	zk[i].draw(g);
			MyBall.hitzk(zk[i]);
			
			}*/
		for(int i=0; i<zk.size(); i++) {
			ZK t = zk.get(i);
			t.draw(g);
			MyBall.hitzk(t);//14.2.8
		}	//新加入时间：14/2/7
			MyBall.draw(g);
	}
	

	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}//双缓存

	public void lauchFrame() {
			for(int i=0; i<11; i++)
				for(int j=0;j<4; j++)
					{
						zk.add(new ZK(25 +50*(i+0), 25 +30*(j+0)));//此处尝试时间：14/2/7
					}
		this.setLocation(100,100);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("ZUANKUAI");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		
		this.addKeyListener(new KeyMonitor());
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				//MyWall.setlocation(e.getX());
				System.out.println(e.getX());
			}
			public void mouseEntered(MouseEvent e){
				System.out.println("entered");
			}
		});
		
		setVisible(true);
		System.out.println(this.getName());
		new Thread(new PaintThread()).start();
	}

	public static void main(String[] args) {
		GAME tc = new GAME();
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class KeyMonitor extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			MyWall.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
		    MyWall.keyReleased(e);
		}//判断按键释放，板停止
	}
}













