package cyfixusBot.game.entities;

import java.util.Random;

public abstract class Entity extends GameObject{
  protected byte health;
  protected byte mana;
  protected double currency;
  protected double capacity;
  protected String name;
  protected String title;
  protected byte level;
  protected long exp;
  protected long toNextLevel;
  
  private Random random = new Random();

  public Entity(float x, float y, ObjectID id) {
    super(x, y, id);
    generateBase();
  }
  
  private void generateBase(){
    health = (byte)(random.nextInt(20)+80);
    mana = (byte)(random.nextInt(20)+80);
    currency = 100;
    capacity = 150;
    level = 1;
    exp = 0;
    toNextLevel = (level * 10) + 10;
    
  }
  
  public byte getHealth(){
    return health;
  }
  
  public byte getMana(){
    return mana;
  }
  
  public byte getLevel(){
    return level;
  }
  
  public long getExp(){
    return exp;
  }
  
  public long getExpToNextLevel(){
    return toNextLevel;
  }
  
  public double getCurrency(){
    return currency;
  }
  
  public double getCapacity(){
    return capacity;
  }
  
  public String getName(){
    return name;
  }
  
  public String getTitle(){
    return title;
  }
  public void setName(String name){
    this.name = name;
  }
  
  public void setTitle(String title){
    this.title = title;
  }
  
  public void setToNextLevel(){
    toNextLevel = getExp() + (getLevel() * getLevel()) + 10;
  }
  
  public void setHealth(byte health){
    this.health = health;
  }
  
  public void setMana(byte mana){
    this.mana = mana;
  }
  
  public void setCurrency(double currency){
    this.currency = currency;
  }
  
  public void setCapacity(double capacity){
    this.capacity = capacity;
  }
  
  public void setLevel(byte level){
    this.level = level;
  }
  public boolean setExp(long exp){
    this.exp = exp;
    if(this.exp >= toNextLevel){
      incLevel((byte)1);
      setToNextLevel();
      return true;
    }
    return false;
  }
  
  
  
  public void incHealth(byte health){
    this.health += health;
  }
  
  public void incMana(byte mana){
    this.mana += mana;
  }
  
  public void incCurrency(double currency){
    this.currency += currency;
  }
  
  public void incCapacity(double capacity){
    this.capacity += capacity;
  }
  
  public void incLevel(byte level){
    this.level += level;
  }
  public boolean incExp(long exp){
    this.exp += exp;
    if(this.exp >= toNextLevel){
      incLevel((byte)1);
      setToNextLevel();
      return true;
    }
    return false;
  }
  
  
  
  
  

}
