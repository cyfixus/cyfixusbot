package cyfixusBot.game.players;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cyfixusBot.game.entities.Avatar;
import cyfixusBot.game.entities.ObjectID;
import sun.reflect.annotation.TypeAnnotation.LocationInfo.Location;

public class Player extends Avatar{
//	private StatGenerator statGen;
	private int index;
	private int tickets = 0;
	private Random random = new Random();

	public Player(float x, float y, ObjectID id, String name) {
		super(x, y, id);
		this.setName(name);
		this.setTitle("");
	}
    @Override
    public String toString(){
    	String playerData = " ";
    	return playerData;
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
