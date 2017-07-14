package cyfixusBot.minigames;

import java.util.Random;

import cyfixusBot.bot.CyfixusBot;
import cyfixusBot.game.players.Player;
import cyfixusBot.util.StringListener;

/***********************************************************
 * 
 * 2 Players
 * sender   &&   sender's target
 * target will be extracted from !battle message
 * slpit \s regex
 * String[] [0] = "!*", [1] = "message"
 *  temp param for battle will be based upon
 *  random shit... mthll  from player stats
 * 
 * users 
 * getPlayer.... 
 */
public class Battle {
    private Player playerOne;
    private Player playerTwo;
    private int winner;
    private String channel;
    private StringListener stringListener;
    
    private CyfixusBot bot;
    
    private Random random = new Random();
    
	public Battle(Player playerOne, Player playerTwo, CyfixusBot bot, 
			          String channel, StringListener stringListener){
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.bot = bot;
		this.channel = channel;
		this.stringListener = stringListener;
		//initbattle
		winner = declareWinner(battle());
	}
	/************************************************
	 * Compare Strength Stamina Intelligence Will
	 * based on random values obtained from the limits
	 * defined by the value of the stat
	 */
	public int[] battle(){
		int pTotals[] = {0, 0};
		String p1 = playerOne.getName();
		String p2 = playerTwo.getName();
		int p1Str = random.nextInt(playerOne.getStrength());
		int p2Str = random.nextInt(playerTwo.getStrength());
		int p1Sta = random.nextInt(playerOne.getStamina());
		int p2Sta = random.nextInt(playerTwo.getStamina());
		int p1Int = random.nextInt(playerOne.getIntelligence());
		int p2Int = random.nextInt(playerTwo.getIntelligence());
		int p1Wil = random.nextInt(playerOne.getWill());
		int p2Wil =random.nextInt(playerTwo.getWill());
		bot.sendMessage(channel, "   -   battle -   ");
		bot.sendMessage(channel, p1 +" vs. " + p2);
		stringListener.textEmitted("   -   battle -   \n");
		stringListener.textEmitted(p1 +" vs. " + p2 + '\n');
		//use ternarrryyy
		if(p1Str > p2Str){
			pTotals[0]++;
			bot.sendMessage(channel, p1 + " overpowered " + p2);
		    stringListener.textEmitted(p1 + " overpowered " + p2 + '\n');
		}
		else if(p1Str < p2Str){
			pTotals[1]++;
			bot.sendMessage(channel, p2 + " smashed " + p1);
			stringListener.textEmitted(p2 + " smashed " + p1 + '\n');
		}
		if(p1Sta > p2Sta){
			pTotals[0]++;
			bot.sendMessage(channel, p1 + " outlasted " + p2);
			stringListener.textEmitted(p1 + " outlasted " + p2 + '\n');
		}
		else if(p1Sta < p2Sta){
			pTotals[1]++;
			bot.sendMessage(channel, p2 + " outendured " + p1);
			stringListener.textEmitted( p2 + " outendured " + p1 + '\n');
		}
		if(p1Int > p2Int){
			pTotals[0]++;
			bot.sendMessage(channel, p1 + " outsmarted " + p2);
			stringListener.textEmitted(p1 + " outsmarted " + p2 + '\n');
		}
		else if(p1Int < p2Int){
			pTotals[1]++;
			bot.sendMessage(channel, p2 + " outwitted " + p1);
			stringListener.textEmitted(p2 + " outwitted " + p1 + '\n');
		}
		if(p1Wil > p2Wil){
			pTotals[0]++;
			bot.sendMessage(channel, p1 + " wanted it more than " 
			                    + p2);
			stringListener.textEmitted(p1 + " wanted it more than " 
                    + p2 + '\n');
		}
		else if(p1Wil < p2Wil){
			pTotals[1]++;
			bot.sendMessage(channel, p2 + " had the will to win " 
			                 + p1);
			stringListener.textEmitted(p2 + " had the will to win " 
	                 + p1 + '\n');
		}
		return pTotals;
	}
	
	public int declareWinner(int[] bda){
		int winner = -1;
	    if(bda[1] > bda[0]){
	    	winner = 1;
	    }
	    else if(bda[0] > bda[1]){
	    	winner = 0;
	    }
		return winner;
	}
	
	public int getWinner(){
		return winner;
	}
	
}
