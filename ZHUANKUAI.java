/*实现界面下方的墙移动。
*2014、1、22
*/



import java.awt.*;
import java.awt.event.*;

public class ZHUANKUAI extends Frame {
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 400;
	
	 int x = 50 ;
	
	Image offScreenImage = null;
	
	public void paint(Graphics g) {
		
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawLine(x,379,x+40,379);
		g.drawLine(x,380,x+40,380);//g.fillOval(x, y, 30, 30);
		g.setColor(c);
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
	}

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
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_LEFT :
				x -= 5;
				break;
			//case KeyEvent.VK_UP :
				//y -= 5;
				//break;
			case KeyEvent.VK_RIGHT :
				x += 5;
				break;
			//case KeyEvent.VK_DOWN :
				//y += 5;
				//break;
			}
		}
		
	}
}













