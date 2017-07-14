package cyfixusBot.bot;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.Timer;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

import cyfixusBot.game.entities.ObjectID;
import cyfixusBot.game.players.Player;
import cyfixusBot.game.players.StatEnum;
import cyfixusBot.game.players.Users;
import cyfixusBot.minigames.Battle;
import cyfixusBot.minigames.lotto.Lotto;
import cyfixusBot.util.CyPrinters;
import cyfixusBot.util.DataManager;
import cyfixusBot.util.LottoListener;
import cyfixusBot.util.StringListener;

public class CyfixusBot extends PircBot implements ActionListener{
	private DataManager dataManager = new DataManager();
	private Users users = new Users();
	private CyPrinters cyPrinters = new CyPrinters(this);
	private Lotto lotto = new Lotto();
	private String channel;
	private int time = 0;
	private StringListener stringListener;
	private LottoListener lottoListener;

	public CyfixusBot(){
		this.setName("cyfixusbot");
		
		this.isConnected();

		Timer timer = new Timer(1000, this);
        timer.setInitialDelay(0);
        timer.start();
	}
	
	public void addStringListener(StringListener listener){
		this.stringListener = listener;
	}
	
	public void addLottoListener(LottoListener listener){
		this.lottoListener = listener;
	}
	
	public void onJoin(String channel, String sender, String login, 
			               String hostname){
		this.channel = channel;
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + 
		                    this.channel + "<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	public void autoJoin(){
		User[] channelUsers = getUsers(channel);
		for(User user: channelUsers){
			Player player = new Player(0, 0, ObjectID.Player, user.toString());
			if(!users.contains(player)){
			    join(channel, user.toString(), player);
			}
		}
	}
	public void getUsers(){
		User[] channelUsers = getUsers(channel);
		sendMessage(channel, buildUserString(channelUsers));
	}
	
	public String buildUserString(User[] channelUsers){
		StringBuilder buildString = new StringBuilder();
		String userString = "";
		for(User user: channelUsers){
			buildString.append(user.toString() + " ");
		}
		userString = buildString.toString();
		return userString;
	}

	public void onMessage(String channel, String sender, String login, 
			                  String hostname, String input){
		    Player player = new Player(0, 0, ObjectID.Player, sender);
		    Player targetPlayer = new Player(0, 0, ObjectID.Player, "dummy");
		    String message = input.toLowerCase();
		    String command = message;
		    String target = " ";
		    String target2 = " ";
		    String change = " ";
		    StringBuilder changeBuild = new StringBuilder();
		    if(message.charAt(0) == '!'){
			    try{
				    String splitMessage[] = message.split(" ");
				    command = splitMessage[0];
				    target = splitMessage[1];
				    target2 = splitMessage[2];
				    for(int i = 3; i < splitMessage.length; i++){
				    	changeBuild.append(splitMessage[i]);
				    	try{
					    		if(splitMessage[i].charAt(0) >= '0' && 
					    			splitMessage[i].charAt(0) < '9'){
					    		break;
					    	}
					    	else
					    	{
					    		changeBuild.append(' ');
					    	}
				    	}catch(Exception e){};
				    }
				    change = changeBuild.toString();
			    }catch(Exception e){
//			    	e.printStackTrace();
			    }
			    
			    switch(command){
			        case "!yo":
			        	sendMessage(channel, "Yo " + sender + "!");
			        	break;
				    case "!join":
				    	join(channel, sender, player);
				    	break;
				    case "!users":
				    	cyPrinters.printUsers(channel, users);
				    	stringListener.textEmitted(getAllPlayers());
				    	break;
				    case "!stats":
				    	if(users.contains(player)){
							cyPrinters.printStats(channel, sender, users);
							stringListener.textEmitted("whispering " + sender + "'s stats\n");
				    	}
				    	
				    	break;
				    case "!set":
				    	targetPlayer = users.getPlayer(target);
				    	if(sender.equals("cyfixus")){
				    		set(channel, sender, target2, targetPlayer, 
				    				change);
				    	}
				    	stringListener.textEmitted("setting " + target + "'s stats\n");
				    	break;
				    case "!autojoin":
				    	targetPlayer = users.getPlayer(target);
				    	if(sender.equals("cyfixus")){
				    		autoJoin();
				    	}
				    	stringListener.textEmitted("autojoin\n");
				    	break;
				    case "!abandon":
				    	player = users.getPlayer(sender);
				    	if(users.contains(player)){
								users.removePlayer(sender);
								cyPrinters.privateMessage(channel, sender, 
										                  "Goodbye " + sender);	
							}
				    	stringListener.textEmitted("Goodbye " + sender + '\n');
				    	break;
				    case "!lotto":
				    	player = users.getPlayer(sender);
				    	lotto(channel, sender, player);
				    	break;
				    case "!stat":
				    	player = users.getPlayer(sender);
				    	stat(channel, sender, target, player);
				    	break;
				    case "!battle":
				    	player = users.getPlayer(sender);
				    	targetPlayer = users.getPlayer(target);
				    	battle(channel, player, targetPlayer, stringListener);
				    	break;
				    case "!title":
				    	player = users.getPlayer(sender);
				    	sendMessage(channel, player.getName() + ": " + 
				    	                player.getTitle());
				    	stringListener.textEmitted(player.getName() + ": " + 
				    	                player.getTitle());
				    	break;
			    }
		    }
		    
		    
		dataManager.save(users.getPlayerNames());
	}
	
	public boolean addPlayer(String targetPlayer){
		boolean newPlayer = true;
		Player player = new Player(0, 0, ObjectID.Player, targetPlayer);
		if(users.contains(player)){
			newPlayer = false;
		}
		join(channel, targetPlayer, player);
		return newPlayer;
	}
	public void join(String channel, String sender, Player player){
		if(!users.contains(player)){
			users.addUser(sender);
			sendMessage(channel, "Welcome " + sender + "!");
			stringListener.textEmitted("Welcome " + sender + "\n");
//			cyPrinters.printStats(channel, sender, users);
		}
		else{
			cyPrinters.privateMessage(channel, sender, 
					                  "You're already a member!");
		}
	}
	
	public void lotto(String channel, String sender, Player player){
		if(users.contains(player)){
    		if(lotto.addTicket(player)){
    			cyPrinters.privateMessage(channel, sender, 
    					                  "ticket added: " + 
    			                          player.setTickets());
    			player.incCurrency(-1);
    			cyPrinters.playerStat(channel, sender, player, 
    					                  StatEnum.CURRENCY);
    			lottoListener.buyTicket(sender);
    			stringListener.textEmitted(sender + " +1 ticket\n");
    		}
    		else{
    			Player winner = lotto.selectWinner();
    			sendMessage(channel, winner.getName() + " won the Lotto!");
    			player = users.getPlayer(winner.getName());
    			player.incCurrency(lotto.getPot());
    			grantExp(player, 1);
    			cyPrinters.privateMessage(channel, sender, 
    					                  " +1 exp: " + winner.getExp());
    			cyPrinters.privateMessage(channel, sender, 
    					                  "Congratulations! You now have");
    			lotto.reset();
    			resetUserTickets();
    			cyPrinters.playerStat(channel, sender, player, 
    					                  StatEnum.CURRENCY);
    			lottoListener.announceWinner(winner.getName());
    			stringListener.textEmitted(winner.getName() + " won the Lotto!\n");
    		}
    	}
	}
	
	public void stat(String channel, String sender, String target, 
			             Player player){
		if(users.contains(player)){
    		StatEnum statEnum;
    		if(target != " "){
    		    switch(target){
    		         case "title":
    		        	 statEnum = StatEnum.TITLE;
    		        	 break;
	    		     case "health":
	    		    	 statEnum = StatEnum.HEALTH;
	    		    	 break;
	    		     case "mana":
	    		    	 statEnum = StatEnum.MANA;
	    		    	 break;
	    		     case "level":
	    		         statEnum = StatEnum.LEVEL;
	    		         break;
	    		     case "exp":
	    		    	 statEnum = StatEnum.EXP;
	    		    	 break;
	    		     case "tonextlevel":
	    		     	 statEnum = StatEnum.TONEXTLEVEL;
	    		     	 break;
	    		     case "currency":
	    		    	 statEnum = StatEnum.CURRENCY;
	    		    	 break;
	    		     case "capacity":
	    		    	 statEnum = StatEnum.CAPACITY;
	    		    	 break;
	    		     case "strength":
	    		    	 statEnum = StatEnum.STRENGTH;
	    		    	 break;
	    		     case "stamina":
	    		    	 statEnum = StatEnum.STAMINA;
	    		    	 break;
	    		     case "intelligence":
	    		    	 statEnum = StatEnum.INTELLIGENCE;
	    		    	 break;
	    		     case "will":
	    		    	 statEnum = StatEnum.WILL;
	    		    	 break;
	    		     default:
	    		    	 statEnum = StatEnum.NAME;
	    		    	 break;
    		    }
    		cyPrinters.playerStat(channel, sender, player, statEnum);
    	    }
        }
	}
	
	public void set(String channel, String sender, String target, 
			            Player targetPlayer, String change){
		if(users.contains(targetPlayer)){
    		StatEnum statEnum;
    		if(target != " "){
    		    switch(target){
    		         case "title":
    		    	     statEnum = StatEnum.TITLE;
    		    	     break;
	    		     case "health":
	    		    	 statEnum = StatEnum.HEALTH;
	    		    	 break;
	    		     case "mana":
	    		    	 statEnum = StatEnum.MANA;
	    		    	 break;
	    		     case "level":
	    		         statEnum = StatEnum.LEVEL;
	    		         break;
	    		     case "exp":
	    		    	 statEnum = StatEnum.EXP;
	    		    	 break;
	    		     case "tonextlevel":
	    		     	 statEnum = StatEnum.TONEXTLEVEL;
	    		     	 break;
	    		     case "currency":
	    		    	 statEnum = StatEnum.CURRENCY;
	    		    	 break;
	    		     case "capacity":
	    		    	 statEnum = StatEnum.CAPACITY;
	    		    	 break;
	    		     case "strength":
	    		    	 statEnum = StatEnum.STRENGTH;
	    		    	 break;
	    		     case "stamina":
	    		    	 statEnum = StatEnum.STAMINA;
	    		    	 break;
	    		     case "intelligence":
	    		    	 statEnum = StatEnum.INTELLIGENCE;
	    		    	 break;
	    		     case "will":
	    		    	 statEnum = StatEnum.WILL;
	    		    	 break;
	    		     default:
	    		    	 statEnum = StatEnum.NAME;
	    		    	 break;
    		    }
    		cyPrinters.playerSet(channel, sender, change, targetPlayer, 
    				                 statEnum);
    	    }
        }
	}
	public void battle(String channel, Player player, Player targetPlayer, 
			           StringListener stringListener){
		if(users.contains(player) && users.contains(targetPlayer)){
    		Battle battle = new Battle(player, targetPlayer, this, channel, stringListener);
    		int winner = battle.getWinner();
    		String winnerName = " ";
    		String expOutcome = " ";
    		if(winner == 0){
    			winnerName = player.getName() + " wins the battle!";
    			grantExp(player, 1);
    			expOutcome = "" + player.getExp();
    		}
    		else if(winner == 1){
    			winnerName = targetPlayer.getName() + " wins the battle!";
    			grantExp(targetPlayer, 1);
    			expOutcome = "" + targetPlayer.getExp();
    		}
    		else if(winner != 1 && winner != 0){
    			winnerName = "draw";
    		}
    		sendMessage(channel, winnerName);
    		cyPrinters.privateMessage(channel, winnerName, 
    				                  " +1 exp: " + expOutcome);
    		stringListener.textEmitted(winnerName + '\n');
		}
	}
	public void onNotice(String sourceNick, String sourceLogin, 
			String sourceHostname, String target, String notice){
		System.out.println("sourceNick: " + sourceNick + " sourceLogin: " + 
			sourceLogin + " sourceHostname: " +  sourceHostname + " target: " +
				target + " notice: " + notice);
	}
	public void resetUserTickets(){
    	for(Player player: users.getPlayers()){
    		player.resetTickets();
    	}
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		time++;
		if(time % 300 == 0){
		    grantExpToAll(1);
		}
		if(time % 60 == 0){
			autoJoin();
		}
	}
	public String getAllPlayers(){
		LinkedList<Player> currentPlayers = users.getPlayers();
		StringBuilder buildString = new StringBuilder();
		for(int i = 0; i < currentPlayers.size(); i++){
			buildString.append(currentPlayers.get(i).getName() + " ");
			if(i != 0 && i % 2 == 0){
				buildString.append('\n');
			}
		}
		buildString.append('\n');
		return buildString.toString();
	}
	public void grantExpToAll(long exp){
		for(Player player: users.getPlayers()){
			grantExp(player, 1);
		}
	}
	
	public void grantExp(Player player, long exp){
		if(player.incExp(exp)){
			cyPrinters.privateMessage(channel, player.getName(), "Level up!: " 
		                              + player.getLevel());
		}
//		cyPrinters.privateMessage(channel, player.getName(), exp + " exp!: " + 
//		                           player.getExp());
	}
	
	public boolean removePlayerGUI(String player){
		if(users.getPlayer(player) != null){
			users.removePlayer(player);
			return true;
		}
		return false;
	}
	
	public void sendMSG(String message){
		sendMessage(channel, message);
	}
	
	public boolean gmAddPlayer(String name, String title,
			                int level, double capacity,
			                long exp,
			                int health, int mana,
			                double currency, int strength,
			                int stamina, int intelligence,
			                int will){
		boolean newPlayer = true;
		newPlayer = addPlayer(name);
		Player player = users.getPlayer(name);
		if(!player.getTitle().equals(title)){
			player.setTitle(title);
		}
		if(player.getLevel() != level){
			player.setLevel(level);
		}
		if(player.getCapacity() != capacity){
			player.setCapacity(capacity);
		}
		if(player.getExp() != exp){
			player.setExp(exp);
		}
		player.setToNextLevel();
		if(player.getHealth() != health){
			player.setHealth(health);
		}
		if(player.getMana() != mana){
			player.setMana(mana);
		}
		if(player.getCurrency() != currency){
			player.setCurrency(currency);
		}
		if(player.getStrength() != strength){
			player.setStrength(strength);
		}
		if(player.getStamina() != stamina){
			player.setStamina(stamina);
		}
		if(player.getIntelligence() != intelligence){
			player.setIntelligence(intelligence);
		}
		if(player.getWill() != will){
			player.setWill(will);
		}
		return newPlayer;
	}

	public Player getPlayer(String name) {
		return users.getPlayer(name);
	}
}
