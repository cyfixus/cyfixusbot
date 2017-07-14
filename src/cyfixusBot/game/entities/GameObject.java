package cyfixusBot.game.entities;

import java.awt.*;

public abstract class GameObject{
  protected float x,y;
  protected float velX = 0, velY = 0;
  protected ObjectID id;

  public GameObject(float x, float y, ObjectID id){
    this.x = x;
    this.y = y;
    this.id = id;
  }

  public abstract void move();
  public abstract void render(Graphics g);
  public abstract Rectangle getBounds();
  public abstract Rectangle getBoundsTop();
  public abstract Rectangle getBoundsBottom();
  public abstract Rectangle getBoundsLeft();
  public abstract Rectangle getBoundsRight();


  public void setX(float x){
    this.x = x;
  }

  public void setY(float y){
    this.y = y;
  }
  public float getX(){
    return x;
  }

  public float getY(){
    return y;
  }
  public void setVelX(float velX){
    this.velX = velX;
  }

  public void setVelY(float velY){
    this.velY = velY;
  }
  public float getVelX(){
    return velX;
  }

  public float getVelY(){
    return velY;
  }

  public ObjectID getID(){
    return id;
  }

}