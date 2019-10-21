import java.awt.*;
import javax.swing.*;

public class BouncingBall extends JPanel {

   private static final int BOX_WIDTH = 640;
   private static final int BOX_HEIGHT = 480;

   private double ballRadius = 100;
   private double ballX = ballRadius + 20;
   private double ballY = ballRadius + 20;
   private double ballSpeedX = 10;
   private double ballSpeedY = 10;

   private static final int UPDATE_RATE = 30;

   public BouncingBall() {
      setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               ballX += ballSpeedX;
               ballY += ballSpeedY;
               if (ballX - ballRadius < 0) {
                  ballSpeedX = -ballSpeedX;
                  ballX = ballRadius;
               } else if (ballX + ballRadius > BOX_WIDTH) {
                  ballSpeedX = -ballSpeedX;
                  ballX = BOX_WIDTH - ballRadius;
               }
               if (ballY - ballRadius < 0) {
                  ballSpeedY = -ballSpeedY;
                  ballY = ballRadius;
               } else if (ballY + ballRadius > BOX_HEIGHT) {
                  ballSpeedY = -ballSpeedY;
                  ballY = BOX_HEIGHT - ballRadius;
               }
               repaint();
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) { }
            }
         }
      };
      gameThread.start();
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.setColor(Color.WHITE);
      g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

      g.setColor(Color.RED);
      g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
            (int)(2 * ballRadius), (int)(2 * ballRadius));

      g.setColor(Color.BLACK);
      g.setFont(new Font("Courier New", Font.PLAIN, 12));
      String information = "Location: (" + ballX + ", " + ballY + ")";
      String velocity = "x: " + ballSpeedX + " y: " + ballSpeedY;
      g.drawString(information, 20, 30);
      g.drawString(velocity, 20, 40);
   }

   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new BouncingBall());
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}
