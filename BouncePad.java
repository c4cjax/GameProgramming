public class BouncePad{
  private int length;
  private int height;

  private int x;
  private int y;

  private int speed;

  public BouncePad(){
    length = 50;
    height = 20;
    speed = 10;
    x = 270;
  }

  public int getLength(){
    return length;
  }

  public int getHeight(){
    return height;
  }

  public int getX(){
    return x;
  }

  public void moveLeft(){
    x-=speed;
  }

  public void moveRight(){
    x+=speed;
  }

}
