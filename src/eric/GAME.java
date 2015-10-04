package eric;

import java.awt.*;
import java.awt.event.*;

public class GAME extends Panel {
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 400;
    private static final double LIMIT = 120;
    private static final int Hz = 30;
    private boolean isMouseIn;// 标志 判断鼠标指针是否在窗口区域内
    private Wall MyWall;
    private Ball MyBall;
    private MinPQ<Event> pq;
    private double time;
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
        game.launchFrame();
    }

    public GAME() throws HeadlessException {
        super();
        MyWall = new Wall(200, 380);
        MyBall = new Ball(90, 90);
        isMouseIn = false;
        pq = new MinPQ<Event>();
        pq.insert(new Event(0.0, null, null, false));
        time = 0;
        // init Bricks
        for (int i = 1; i < 11; i++)
            for (int j = 0; j < 4; j++) {
                brickList.add(new Brick(25 + 50 * i, 25 + 30 * j));
            }
        predict(LIMIT);
    }

    public void paint(Graphics g) {
        if (isMouseIn) {
            Point mousePoint = this.getMousePosition();
            if (mousePoint != null) // 避免鼠标移出速度过快，获取坐标之前鼠标移出
                MyWall.setLocation(mousePoint.x);
        }
        MyWall.draw(g);
        for (int i = 0; i < brickList.size(); i++) {
            Brick t = brickList.get(i);
            if (!t.isAlive()) {// In DIYArrayList.remove(id),elementData[id] = elementData[size-1]. elementData[i] needs to be painted.
                brickList.remove(i--);
                continue;
            }
            t.draw(g);
        }
        MyBall.draw(g);
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);//背景颜色
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);//相当于clear
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }// 双缓存

    public void launchFrame() {
        Frame f = new Frame();
        f.setLocation(100, 100);
        f.setSize(GAME_WIDTH+10, GAME_HEIGHT + 20);
        f.setTitle("GAME@ERIC");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setResizable(false);
        f.setBackground(Color.BLACK);
        f.add(this);
        this.addKeyListener(new KeyMonitor());

		/*
         * this.addMouseMotionListener(new MouseAdapter(){ public void
		 * mouseMoved(MouseEvent e){ MyWall.setLocation(e.getX());
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

        f.setVisible(true);
        new Thread(new PaintThread()).start();
    }

    private class PaintThread implements Runnable {

        public void run() {
            while (MyBall.isAlive() && !pq.isEmpty()) {
                Event event = pq.delMin();
                assert event.getTime() >= time;
                if (!event.isValid())
                    continue;
                MyBall.move(event.getTime() - time);
                time = event.getTime();
                if (event.getBall() == null) {
                    // repaint event
                    if (MyBall.getY() > MyWall.getY())
                        MyBall.setIsAlive(false);
                    pq.insert(new Event(time + 1.0 / Hz, null, null, false));
                    repaint();
                } else {
                    //hit event
                    if (event.isVertical() && event.getBall().bounceOffVertical(event))
                        predict(LIMIT);
                    else if (!event.isVertical() && event.getBall().bounceOffHorizontal(event, MyWall))
                        predict(LIMIT);
                }
                try {
                    Thread.sleep(2);
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

    private void predict(double limit) {
        if (brickList == null || MyBall == null || pq == null)
            return;
        boolean moveToRight = MyBall.isMoveToRight();
        boolean moveToDown = MyBall.isMoveToDown();
        double ballX = MyBall.getX();
        double ballY = MyBall.getY();
        int R = Ball.R;
        for (Brick b : brickList) {

            // be able to arrive b.left
            if (moveToRight && ballX <= b.getLeft()) {
                double eventTime = (b.getLeft() - R - R - ballX) / MyBall.getXSpeed();
                assert eventTime >= 0;
                if (eventTime < limit) {
                    Event event = new Event(time + eventTime, MyBall, b, true);
                    pq.insert(event);
                }
            }
            // be able to arrive b.right
            if (!moveToRight && ballX > b.getRight()) {
                double eventTime = (ballX - b.getRight()) / MyBall.getXSpeed();
                assert eventTime > 0;
                if (eventTime < limit) {
                    Event event = new Event(time + eventTime, MyBall, b, true);
                    pq.insert(event);
                }
            }
            // be able to arrive b.top
            if (moveToDown && ballY < b.getTop()) {
                double eventTime = (b.getTop() - R - R - ballY) / MyBall.getYSpeed();
                assert eventTime > 0;
                if (eventTime < limit) {
                    Event event = new Event(time + eventTime, MyBall, b, false);
                    pq.insert(event);
                }
            }
            // be able to arrive b.bottom
            if (!moveToDown && ballY > b.getBottom()) {
                double eventTime = (ballY - b.getBottom()) / MyBall.getYSpeed();
                assert eventTime > 0;
                if (eventTime < limit) {
                    Event event = new Event(time + eventTime, MyBall, b, false);
                    pq.insert(event);
                }
            }
        }
        // time to arrive edge or wall
        if (moveToDown) {
            // time to hit wall
            double eventTime = ((MyWall.getY() - R - R - ballY) / MyBall.getYSpeed());
            if (eventTime < limit) {
                pq.insert(new Event(time + eventTime, MyBall, null, false));
//                System.out.println(eventTime);
            }
        } else {
            double eventTime = ballY / MyBall.getYSpeed();
            if (eventTime < limit) {
                pq.insert(new Event(time + eventTime, MyBall, null, false));
//                System.out.println(eventTime);
            }
        }
        if (moveToRight) {
            double eventTime = (GAME_WIDTH - R - R - ballX) / MyBall.getXSpeed();
            if (eventTime < limit) {
                pq.insert(new Event(time + eventTime, MyBall, null, true));
//                System.out.println(eventTime);
            }
        } else {
            double eventTime = ballX / MyBall.getXSpeed();
            if (eventTime < limit) {
                pq.insert(new Event(time + eventTime, MyBall, null, true));
//                System.out.println(eventTime);
            }
        }
    }
}