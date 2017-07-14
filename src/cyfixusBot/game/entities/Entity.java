package cyfixusBot.game.entities;

import java.util.Random;

public abstract class Entity extends GameObject{
	protected int health;
	protected int mana;
	protected double currency;
	protected double capacity;
	protected String name;
	protected String title;
	protected int level;
	protected long exp;
	protected long toNextLevel;
	
	private Random random = new Random();

	public Entity(float x, float y, ObjectID id) {
		super(x, y, id);
		generateBase();
	}
	
	private void generateBase(){
		health = random.nextInt(20)+80;
		mana = random.nextInt(20)+80;
		currency = 100;
		capacity = 150;
		level = 1;
		exp = 0;
		toNextLevel = (level * 10) + 10;
		
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getMana(){
		return mana;
	}
	
	public int getLevel(){
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
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setMana(int mana){
		this.mana = mana;
	}
	
	public void setCurrency(double currency){
		this.currency = currency;
	}
	
	public void setCapacity(double capacity){
		this.capacity = capacity;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	public boolean setExp(long exp){
		this.exp = exp;
		if(this.exp >= toNextLevel){
			incLevel(1);
			setToNextLevel();
			return true;
		}
		return false;
	}
	
	
	
	public void incHealth(int health){
		this.health += health;
	}
	
	public void incMana(int mana){
		this.mana += mana;
	}
	
	public void incCurrency(double currency){
		this.currency += currency;
	}
	
	public void incCapacity(double capacity){
		this.capacity += capacity;
	}
	
	public void incLevel(int level){
		this.level += level;
	}
	public boolean incExp(long exp){
		this.exp += exp;
		if(this.exp >= toNextLevel){
			incLevel(1);
			setToNextLevel();
			return true;
		}
		return false;
	}
	
	
	
	
	

}
