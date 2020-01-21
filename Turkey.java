import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.sound.sampled.*;
import java.io.File;
import javax.swing.Timer;
import java.util.Random;
import java.util.Arrays;

public class Turkey extends Applet implements MouseListener, KeyListener, Runnable {
    Image turkey;
    Clip turkeyNoise;
    AudioInputStream sample;
    int x = 0, y = 0, xTarget = 200, yTarget = 200, step = 2, xStart = 1, yStart = 1;
    int direction = -1;
    int radius = 5;
    int slope;
    double dx = 1, dy = 1;
    //double xDir = 1, yDir = 1;

    public void init(){
      Toolkit tk = Toolkit.getDefaultToolkit();
      turkey = tk.getImage("turkey.png");
      addKeyListener(this);
      addMouseListener(this);
      (new Thread(this)).start();
    }

    public void run(){
      while(true){
        while(!((x < xTarget+5) && (x > xTarget-5))){
          try{
            slope = (yTarget - y)/(xTarget - x);
            //Calculate x and y components
            dx = radius/Math.sqrt(Math.pow(slope, 2)+1);
            dy = (slope*radius)/Math.sqrt(Math.pow(slope, 2)+1);
          }catch(ArithmeticException e){
            dx = 0;
            dy = radius;
          }
          //System.out.println("Old");
          //System.out.println("DX: " + dx + " DY: " + dy);

				 //Get a direction for dx and dy
				if(xTarget < x){
					dx = -Math.abs(dx);
				}else if(xTarget > x){
					dx = Math.abs(dx);
				}
				if(yTarget > y){
					dy = Math.abs(dy);
				}else if(yTarget < y){
					dy = -Math.abs(dy);
				}
				//System.out.println("New");
				//System.out.println("DX: " + dx + "DY: " + dy);
            //update position
            x+=dx;
            y+=dy;

            try{
              Thread.sleep(100);
            }catch(InterruptedException i){}

            repaint();
        }

        try{
          Thread.sleep(100);
        }catch(InterruptedException i){}
      }
    }

    public void mouseClicked(MouseEvent e) {
      xStart = x;
      yStart = y;

      xTarget = e.getX();
      yTarget = e.getY();
    }

    public void delay(){
      for(int c = 0; c < 10000; c++){
        Random rndm = new Random();
        int cc = rndm.nextInt(1000);
        int[] arr = new int[10000];
        arr[c] = cc;
      }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void keyReleased(KeyEvent e){

    }

    public void keyPressed(KeyEvent e){
      int keyCode = e.getKeyCode();
      int initDirection = direction;
      switch(keyCode){
        case KeyEvent.VK_LEFT:{
          x-=10;
          direction = 0;
          break;
        }
        case KeyEvent.VK_RIGHT:{
          x+=10;
          direction = 1;
          break;
        }
        case KeyEvent.VK_UP:{
          y-=10;
          direction = 2;
          break;
        }
        case KeyEvent.VK_DOWN:{
          y+=10;
          direction = 3;
          break;
        }
      }
      if(initDirection != direction){
        playNoise();
      }
      repaint();
    }

    public void keyTyped(KeyEvent e){

    }

    public void playNoise(){
      try{
        sample = AudioSystem.getAudioInputStream(new File("turkeynoise.wav"));
        turkeyNoise = AudioSystem.getClip();
        turkeyNoise.open(sample);
        turkeyNoise.start();
      }catch(Exception e){}
    }

    public void paint(Graphics g) {
      g.drawImage(turkey, x, y, 50, 50, this);
    }
}
