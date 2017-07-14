package cyfixusBot.game.entities;

import java.util.Random;

public abstract class Avatar extends Entity{
	protected int strength;
	protected int stamina;
	protected int intelligence;
	protected int will;
	
	protected CyClass cyClass;
	private Random random = new Random();
	
	public Avatar(float x, float y, ObjectID id) {
		super(x, y, id);
		generateStats();
	}
	
	private void generateStats(){
		strength = random.nextInt(6)+4;
		stamina = random.nextInt(6)+4;
		intelligence = random.nextInt(6)+4;
		will = random.nextInt(6)+4;
		
	}
	
	public int getStrength(){
		return strength;
	}
	
	public int getStamina(){
		return stamina;
	}
	
	public int getIntelligence(){
		return intelligence;
	}
	
	public int getWill(){
		return will;
	}
	
	public void setStrength(int strength){
		this.strength = strength;
	}
	public void setStamina(int stamina){
		this.stamina = stamina;
	}
	public void setIntelligence(int intelligence){
		this.intelligence = intelligence;
	}
	public void setWill(int will){
		this.will = will;
	}
	public void incStrength(int strength){
		this.strength += strength;
	}
	public void incStamina(int stamina){
		this.stamina += stamina;
	}
	public void incIntelligence(int intelligence){
		this.intelligence += intelligence;
	}
	public void incWill(int will){
		this.will += will;
	}

}
