package cyfixusBot.minigames.lotto;

import java.util.LinkedList;
import java.util.Random;

import cyfixusBot.game.players.Player;

public class Lotto {
	/*******************************************************
	 * Player will type !lotto and 1 currency will be removed
	 * from the player, and one ticket will be entered on
	 * behalf of the player. When Lotto reaches 10 tickets
	 * a winner will be drawn.
	 * 
	 * List of Player that act as tickets 
	 */
	
	private LinkedList<Player> tickets = new LinkedList<>();
	private Random random = new Random();
	private int pot = 0;
	
	public Lotto(){
		
	}
	/*************************************
	 * allow the addition of tickets while there are < 11
	 * tickets in the hat. each ticket added, increases
	 * the player's number of tickets, and decreases the
	 * player's currency by 1, which is "transferred" to the pot 
	 * 
	 */
	public boolean addTicket(Player player){
		if(checkFull()){
			return false;
		}
		else{
		    tickets.add(player);
		    pot++;
		    return true;
		}
	}
	
	public void reset(){
		tickets.clear();
		pot = 0;
	}
	
	public Player selectWinner(){
		Player player = tickets.get(random.nextInt(tickets.size()));

		return player;
	}
	
	public int getPot(){
		return pot;
	}
	
	public boolean checkFull(){
		if(tickets.size() > 9){
            return true;
		}
		return false;
	}

}
