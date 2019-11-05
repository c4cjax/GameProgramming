import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

@SuppressWarnings("serial")
public class SimpleSnake extends JPanel {
	private static final int BOX_WIDTH = 640;
	private static final int BOX_HEIGHT = 480;
	
	private double sectionRadius = 10;
	private double bodyX = sectionRadius;
	private double bodyY = sectionRadius;
	
	private double snakeBodyX[] = {(sectionRadius), (sectionRadius + 20), (sectionRadius + 40)};
	private double snakeBodyY[] = {(sectionRadius), (sectionRadius), (sectionRadius)};
	private double snakeSpeedX = 10;
	private double snakeSpeedY = 5;


	//(0,0) is in the top left corner, goes positive as it goes right and down
	private static final int UPDATE_RATE = 100; //in milliseconds, = 0.1 seconds
	
	public SimpleSnake() {
		setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					for(int pixel = 0; pixel < 3; pixel++) {
						snakeBodyX[pixel] += snakeSpeedX;

					}
					repaint();
		            try {
		            	TimeUnit.MILLISECONDS.sleep(UPDATE_RATE);
		            } catch (InterruptedException ex) { }
				}
		   }
		};
		
		gameThread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

		g.setColor(Color.BLACK);
		for(int pixel = 0; pixel < 3; pixel++) {
			g.fillOval((int) (snakeBodyX[pixel] - sectionRadius), (int) (snakeBodyY[pixel] - sectionRadius),
					(int)(2 * sectionRadius), (int)(2 * sectionRadius));
		}
	}

	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new SimpleSnake());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
	
}
