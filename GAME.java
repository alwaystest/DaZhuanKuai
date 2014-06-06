/*
*2014.01.28；
*砖块范围，x从25到275，横排共11个砖块；
*修改小球位置为（90,90），修改砖块大小（50*30）；
*先添加两个砖块做范围调整，若小球位置为（60,60），则(60,30)右侧面出错，（10,300）下侧出错；
*/

import java.awt.*;
import java.awt.event.*;

public class GAME extends Frame {
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 400;
	
	 Wall MyWall = new Wall(200,380);
	 Ball MyBall = new Ball(90,90);
	 ZK zk[]={new ZK(25,25),new ZK(75,25),new ZK(125,25),new ZK(175,25),new ZK(225,25),new ZK(275,25),new ZK(325,25),new ZK(375,25),new ZK(425,25),new ZK(475,25),new ZK(525,25),new ZK(525,55),new ZK(475,55),new ZK(425,55),new ZK(375,55),new ZK(125,55)};
					
	 
	 
	Image offScreenImage = null;
	
	public void paint(Graphics g) {
		MyWall.draw(g);
		MyBall.hitwall(MyWall);//判断球和板碰撞
		for(int i=0;i<16;i++)
		{	zk[i].draw(g);
			MyBall.hitzk(zk[i]);
			
			}MyBall.draw(g);
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
		
		setVisible(true);
		
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













