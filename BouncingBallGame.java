import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;

public class BouncingBallGame extends Applet implements KeyListener, Runnable{
  Font scoreFont = new Font("Comic Sans MS", Font.BOLD, 20);
  Ball ball;
  Ball dbBall;
  BouncePad bp;
  int score;
  Thread animationThread;
  boolean paused;
  int xSPaused;
  int ySPaused;
  int bXPaused;
  int highScore;
  int paddleIncrease;
  int wallIncrease;

  public void init(){
    paused = false;
    ball = new Ball(100, 50, 5, 5, 10);
    bp = new BouncePad();
    score = 0;
    paddleIncrease = 10;
    wallIncrease = 5;
    addKeyListener(this);
    animationThread = new Thread(this);
  }

  @Override
  public void run(){
    while(animationThread.isAlive()){
      ball.moveBall(ball.getXSpeed(), ball.getYSpeed());
      repaint();
      delay(50);
      checkWallCollision();
      checkBouncePadCollision();
      if(ball.getY() + ball.getRadius() >= 440){
        loseScreen();
        break;
      }
    }
  }

  public void loseScreen(){
    Graphics g = getGraphics();
    g.setFont(scoreFont);
    g.drawString("You lost! \"R\" to restart.", 210, 220);
  }

  public void beginAnimation(){
    animationThread.start();
  }

  public void restart(){
    init();
    repaint();
  }

  public void checkWallCollision(){
    if(ball.getX() <= 20){
      ball.setXSpeed(Math.abs(ball.getXSpeed()));
      score+=wallIncrease;
    }
    if(ball.getY() <= 20){
      ball.setYSpeed(Math.abs(ball.getYSpeed()));
      score+=wallIncrease;
    }
    if(ball.getX() + 2*ball.getRadius() >= 520){
      ball.setXSpeed(-Math.abs(ball.getXSpeed()));
      score+=wallIncrease;
    }
  }

  public void checkBouncePadCollision(){
    if(ball.getY() + 2*ball.getRadius() >= 420){
      if(ball.getX() >= bp.getX() && ball.getX() <= bp.getX() + bp.getLength()){
        ball.setYSpeed(-Math.abs(ball.getYSpeed()));
        score+=paddleIncrease;
      }
    }
  }

  @Override
  public void paint(Graphics g){
    g.setColor(Color.DARK_GRAY); //wall color
    g.fillRect(0, 0, 540, 20);
    g.fillRect(0, 0, 20, 440);
    g.fillRect(520, 0, 20, 440);
    g.setColor(Color.black); //bouncepad color
    g.fillRect(bp.getX(), 420, bp.getLength(), bp.getHeight());
    g.setColor(Color.red); //ball color
    g.fillOval(ball.getX(), ball.getY(), 2*ball.getRadius(), 2*ball.getRadius());
    g.setColor(Color.blue); //score Color
    g.setFont(scoreFont);
    g.drawString("Score: " + score, 20, 20);
  }

  @Override
  public void keyReleased(KeyEvent e){

  }

  @Override
  public void keyPressed(KeyEvent e){
    int keyCode = e.getKeyCode();
    switch(keyCode){
      case KeyEvent.VK_LEFT:{
        if(!paused){
          bp.moveLeft();
        }else{}
        break;
      }
      case KeyEvent.VK_RIGHT:{
        if(!paused){
          bp.moveRight();
        }else{}
        break;
      }
      case KeyEvent.VK_R:{
        restart();
        break;
      }
      case KeyEvent.VK_S:{
        beginAnimation();
        break;
      }
    }
  }

  @Override
  public void keyTyped(KeyEvent e){

  }

  public void delay(int millis){
    try{
      Thread.sleep(millis);
    }catch(InterruptedException e){}
  }

}
