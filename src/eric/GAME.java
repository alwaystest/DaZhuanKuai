package eric;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GAME extends Frame {
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 400;
	private static boolean isMouseIn = false;// 标志 判断鼠标指针是否在窗口区域内
	Wall MyWall = new Wall(200, 380);
	Ball MyBall = new Ball(90, 90);
	DIYArrayList<Brick> brickList = new DIYArrayList<Brick>();
	/*
	 * ZK zk[]={new ZK(25,25),new ZK(75,25),new ZK(125,25),new ZK(175,25),new
	 * ZK(225,25),new ZK(275,25),new ZK(325,25),new ZK(375,25),new
	 * ZK(425,25),new ZK(475,25),new ZK(525,25),new ZK(525,55),new
	 * ZK(475,55),new ZK(425,55),new ZK(375,55),new ZK(125,55)};
	 */

	Image offScreenImage = null;

	public static void main(String[] args) {
		GAME game = new GAME();
		game.lauchFrame();
	}

	public void paint(Graphics g) {
		if (isMouseIn) {
			Point mousepoint = this.getMousePosition();
			if (mousepoint != null) // 避免鼠标移出速度过快，获取坐标之前鼠标移出
				MyWall.setlocation(mousepoint.x);
		}
		MyWall.draw(g);
		MyBall.isHitWall(MyWall);// 判断球和板碰撞
		for (int i = 0; i < brickList.size(); i++) {
			Brick t = brickList.get(i);
			t.draw(g);
			if (MyBall.isHitBrick(t)) {
				// In DIYArrayList.remove(id),elementData[id] = elementData[size-1]. elementData[i] needs to be painted.
				brickList.remove(i--);
			}
		} 
		/*
		 * for(Brick tmp : brickList){	//backup, i like use iterator
		 * tmp.draw(g); MyBall.isHitBrick(tmp); 
		 * }
		 */
		MyBall.draw(g);
	}

	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}// 双缓存

	/**
	 * init
	 */
	public void lauchFrame() {
		// init Bricks
		for (int i = 0; i < 11; i++)
			for (int j = 0; j < 4; j++) {
				brickList.add(new Brick(25 + 50 * (i + 0), 25 + 30 * (j + 0)));
			}
		this.setLocation(100, 100);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setTitle("GAME@ERIC");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.BLACK);

		this.addKeyListener(new KeyMonitor());

		/*
		 * this.addMouseMotionListener(new MouseAdapter(){ public void
		 * mouseMoved(MouseEvent e){ MyWall.setlocation(e.getX());
		 * //System.out.println(e.getX()); }//mouseMotionListener才能监听鼠标move事件
		 * });//这个是通过mouseMove设定板的位置
		 */
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				isMouseIn = true;
				System.out.println("entered");
			}

			public void mouseExited(MouseEvent e) {
				isMouseIn = false;
				System.out.println("exited");
			}
		});// 这个是通过getMousePosition()设定板的位置，此处判断鼠标是否在界面上方

		setVisible(true);
		new Thread(new PaintThread()).start();
	}

	private class PaintThread implements Runnable {

		public void run() {
			while (true) {
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

		public void keyReleased(KeyEvent e) {
			MyWall.keyReleased(e);
		}// 判断按键释放，板停止
	}
}