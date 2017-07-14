package cyfixusBot.events;
import java.util.EventObject;

public class FormEvent extends EventObject {
	private String name;
	private String title;
	private int level;
	private double capacity;
	private long exp;
	private long toNextLevel;
    private int health;
    private int mana;
	private double currency;
    private int strength;
    private int stamina;
    private int intelligence;
    private int will;


	

	private int classCat;

	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String name){
		super(source);
		this.name = name;
	}
	
	public FormEvent(Object source, String name, String title, int classCat){
		super(source);
		this.name = name;
		this.title = title;
		this.classCat = classCat;
	}
	public FormEvent(Object source, String name, String title,
	         int level, double capacity, 
	         long exp, 
	         int health, int mana, 
	         double currency, int strength,
	         int stamina, int intelligence, 
	         int will){
		super(source);
		this.name = name;
		this.title = title;
		this.level = level;
		this.capacity = capacity;
		this.exp = exp;
		this.health = health;
		this.mana = mana;
		this.currency = currency;
		this.strength = strength;
		this.stamina = stamina;
		this.intelligence = intelligence;
		this.will = will;


	}
	public FormEvent(Object source, String name, String title,
			         int level, double capacity, 
			         long exp,long toNextLevel, 
			         int health, int mana, 
			         double currency, int strength,
			         int stamina, int intelligence, 
			         int will, int classCat){
		super(source);
		this.name = name;
		this.title = title;
		this.level = level;
		this.capacity = capacity;
		this.exp = exp;
		this.toNextLevel = toNextLevel;
		this.health = health;
		this.mana = mana;
		this.currency = currency;
		this.strength = strength;
		this.stamina = stamina;
		this.intelligence = intelligence;
		this.will = will;
		this.classCat = classCat;

	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
		

	public String getClassString(){
		String classString = "";
		switch(classCat){
		case 0:
			classString = "grunt";
			break;
		case 1:
			classString = "seer";
			break;
		case 2:
			classString = "sneak";
			break;
		case 3:
			classString = "tradesmith";
			break;
		}
		return classString;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getWill() {
		return will;
	}

	public void setWill(int will) {
		this.will = will;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public long getToNextLevel() {
		return toNextLevel;
	}

	public void setToNextLevel(long toNextLevel) {
		this.toNextLevel = toNextLevel;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
	}

	public int getClassCat() {
		return classCat;
	}

	public void setClassCat(int classCat) {
		this.classCat = classCat;
	}

}
