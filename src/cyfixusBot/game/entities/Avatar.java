package cyfixusBot.game.entities;

import java.util.Random;

public abstract class Avatar extends Entity{
  protected byte strength;
  protected byte stamina;
  protected byte intelligence;
  protected byte will;
  
  protected CyClass cyClass;
  private Random random = new Random();
  
  public Avatar(float x, float y, ObjectID id) {
    super(x, y, id);
    generateStats();
  }
  
  private void generateStats(){
    strength = (byte)(random.nextInt(6)+4);
    stamina = (byte)(random.nextInt(6)+4);
    intelligence = (byte)(random.nextInt(6)+4);
    will = (byte)(random.nextInt(6)+4);
    
  }
  
  public byte getStrength(){
    return strength;
  }
  
  public byte getStamina(){
    return stamina;
  }
  
  public byte getIntelligence(){
    return intelligence;
  }
  
  public byte getWill(){
    return will;
  }
  
  public void setStrength(byte strength){
    this.strength = strength;
  }
  public void setStamina(byte stamina){
    this.stamina = stamina;
  }
  public void setIntelligence(byte intelligence){
    this.intelligence = intelligence;
  }
  public void setWill(byte will){
    this.will = will;
  }
  public void incStrength(byte strength){
    this.strength += strength;
  }
  public void incStamina(byte stamina){
    this.stamina += stamina;
  }
  public void incIntelligence(byte intelligence){
    this.intelligence += intelligence;
  }
  public void incWill(byte will){
    this.will += will;
  }

}
