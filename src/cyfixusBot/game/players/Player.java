package cyfixusBot.game.players;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

import cyfixusBot.game.entities.Avatar;
import cyfixusBot.game.entities.ObjectID;

public class Player extends Avatar{
//	private StatGenerator statGen;
	private int index;
	private int tickets = 0;
	private int playerClass;
	private Random random = new Random();

	public Player(float x, float y, ObjectID id, String name) {
		super(x, y, id);
		this.setName(name);
		this.setTitle("");
	}
	
	public Player(ObjectID id, String playerString, String delimiter){
		super(0, 0, id);
		Scanner sc = new Scanner(playerString).useDelimiter(delimiter);
		setName(sc.next());
		setTitle(sc.next());
		setCurrency(Double.parseDouble(sc.next()));
		setLevel(Byte.parseByte(sc.next()));
		setExp(Integer.parseInt(sc.next()));
		setHealth(Byte.parseByte(sc.next()));
		setMana(Byte.parseByte(sc.next()));
		setStrength(Byte.parseByte(sc.next()));
		setStamina(Byte.parseByte(sc.next()));
		setIntelligence(Byte.parseByte(sc.next()));
		setWill(Byte.parseByte(sc.next()));
		setPlayerClass(Integer.parseInt(sc.next()));
	}
	
	public void setPlayerClass(int playerClass){
		this.playerClass = playerClass;
	}
	
	public int getPlayerClass(){
		return playerClass;
	}
	
    @Override
    public String toString(){
    	StringBuilder playerData = new StringBuilder();
    	playerData.append(name + "\t" + title  + "\t" + currency + "\t" + level  
    			 + "\t" + exp + "\t" + health + "\t" + mana  + "\t" + strength 
    			 + "\t" + stamina + "\t" + intelligence + "\t" + will + "\t" + playerClass);
    	return playerData.toString();
    }
    
    private void writeObject(ObjectOutputStream o)
    	    throws IOException {  
    	    
    	o.defaultWriteObject();  
    	o.writeObject(getName());
    }
    	  
    private void readObject(ObjectInputStream o)
    	    throws IOException, ClassNotFoundException {
    	    
    	o.defaultReadObject();
    	name = (String) o.readObject();
    }



	public void printAttributes() {
		System.out.println("Name: " + name);
		System.out.println("Health: " + health);
		System.out.println("Mana: " + mana);
		System.out.println("Level: " + level);
		System.out.println("exp: " + exp);
		System.out.println("strength: " + strength);
		System.out.println("stamina: " + stamina);
		System.out.println("intelligence: " + intelligence);
		System.out.println("will: " + will);
		System.out.println("class: " + playerClass);
	}
	
	
	
	
	public boolean equals(Object o){
		if(o == this){
			return true;
		}
		if((o instanceof Player)){
			Player player = (Player)o;
			
			return this.name == player.getName();
		}
		return false;
		

	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getTickets(){
		return tickets;
	}
	public int setTickets(){
		return ++tickets;
	}
	
	public void resetTickets(){
		tickets = 0;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}






}
