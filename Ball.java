public class Ball{
  //x and y speed
  private int xSpeed;
  private int ySpeed;

  //ball size
  private int radius;

  //x and y location
  private int x;
  private int y;

  public Ball(int x, int y, int xSpeed, int ySpeed, int radius){
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.radius = radius;
    this.x = x;
    this.y = y;
  }

  public void setPosition(int x, int y){
    this.x = x;
    this.y = y;
  }

  public int getXSpeed(){
      return xSpeed;
  }

  public int getYSpeed(){
    return ySpeed;
  }

  public void setXSpeed(int speed){
    xSpeed = speed;
  }

  public void setYSpeed(int speed){
    ySpeed = speed;
  }

  public void moveBall(int xSpeed, int ySpeed){
    x+=xSpeed;
    y+=ySpeed;
  }

  public int getRadius(){
    return radius;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }
}
