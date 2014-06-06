import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FrameDemo extends JFrame {

	private JPanel contentPane;
	TestComponent tc1, tc2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDemo frame = new FrameDemo();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FrameDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 10, 10));
		tc1 = new TestComponent();
		tc1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		//tc2 = new TestComponent();
		//tc2.setBorder(BorderFactory.createLineBorder(Color.PINK));
		contentPane.add(tc1);
		//contentPane.add(tc2);
	}
}

class TestComponent extends JComponent {
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.RED);
		g.drawLine(0, 0, this.getWidth(), this.getHeight());
	}
}