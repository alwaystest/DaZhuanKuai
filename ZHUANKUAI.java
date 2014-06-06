/*
*2014.01.26；
*板子移动已经改好，需要微调，板子移动顺畅，可以降低sleep()时间，改小移动距离，已改动
*此版本让小球碰到板子反弹 ，wall接不到小球，小球就死亡，游戏结束；不然没办法写砖块，主要改动ball和wall
*/

import java.awt.*;
import java.awt.event.*;

public class ZHUANKUAI extends Frame {
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 400;
	
	 Wall MyWall = new Wall(50,380);
	 Ball MyBall = new Ball(60,60);
	
	Image offScreenImage = null;
	
	public void paint(Graphics g) {
		MyWall.draw(g);
		MyBall.hit(MyWall);//判断球和板碰撞
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
		ZHUANKUAI tc = new ZHUANKUAI();
		tc.lauchFrame();
	}
	
	private class PaintThread implements Runnable {

		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(10);
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













