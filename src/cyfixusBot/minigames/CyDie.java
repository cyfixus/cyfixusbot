package cyfixusBot.minigames;

import java.util.Random;

public class CyDie {
	
	private int sides;
	private Random r;
	
	public CyDie(int sides){
		this.sides = sides;
		r = new Random(System.currentTimeMillis());
	}
	
	public int roll(){
		return r.nextInt(sides)+1;
	}
	
	public int[] roll(int quantity){
		int roll[] = new int[quantity];
		for(int i = 0; i < quantity; i++){
			roll[i] = roll();
		}
		return roll;
	}

}
