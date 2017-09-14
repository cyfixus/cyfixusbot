package cyfixusBot.util;

import java.util.LinkedList;

import cyfixusBot.bot.CyfixusBot;
import cyfixusBot.game.players.Player;
import cyfixusBot.game.players.StatEnum;
import cyfixusBot.game.players.Users;

public class CyPrinters{
  CyfixusBot cyfixusBot;
  
  public CyPrinters(CyfixusBot cyfixusBot){
    this.cyfixusBot = cyfixusBot;
  }
  
  public void privateMessage(String channel, String sender, String string) {
    if(!sender.equals("cyfixusbot")){
      cyfixusBot.sendRawLineViaQueue("PRIVMSG " + channel + " :/w " + sender + " " + string);
    }
  }

  public void printUsers(String channel, Users users){
    LinkedList<Player> players = users.getPlayers();
    for(Player player: players){
      cyfixusBot.sendMessage(channel, player.getName());
    }
  }
  
  public void printStats(String channel, String sender, Users users){
      Player player = users.getPlayer(sender);
      messageInit();
      playerName(channel, sender, player);
      playerTitle(channel, sender, player);
      playerLevel(channel, sender, player);
      playerExp(channel, sender, player);
      playerToNextLevel(channel, sender, player);
      playerCurrency(channel, sender, player);
      playerCapacity(channel, sender, player);
      playerHealth(channel, sender, player);
      playerMana(channel, sender, player);
      playerStrength(channel, sender, player);
      playerStamina(channel, sender, player);
      playerIntelligence(channel, sender, player);
      playerWill(channel, sender, player);

  }
  
  public void playerStat(String channel, String sender, Player player, StatEnum statEnum){
    messageInit();
    switch(statEnum){
      case TITLE:
        playerTitle(channel, sender, player);
        break;
      case LEVEL:
        playerLevel(channel, sender, player);
        break;
      case EXP:
        playerExp(channel, sender, player);
        break;
      case TONEXTLEVEL:
        playerToNextLevel(channel, sender, player);
        break;
      case CURRENCY:
        playerCurrency(channel, sender, player);
        break;
      case CAPACITY:
        playerCapacity(channel, sender, player);
        break;
      case HEALTH:
        playerHealth(channel, sender, player);
        break;
      case MANA:
        playerMana(channel, sender, player);
        break;
      case STRENGTH:
        playerStrength(channel, sender, player);
        break;
      case STAMINA:
        playerStamina(channel, sender, player);
        break;
      case INTELLIGENCE:
        playerIntelligence(channel, sender, player);
        break;
      case WILL:
        playerWill(channel, sender, player);
        break;
      default:
        playerName(channel, sender, player);
        break;
    }
  }
  
  public void playerName(String channel, String sender, Player player){
    privateMessage(channel, sender, " Name: " + player.getName());
  }
  public void playerTitle(String channel, String sender, Player player){
    privateMessage(channel, sender, " Title: " + player.getTitle());
  }
  public void playerLevel(String channel, String sender, Player player){
    privateMessage(channel, sender, " Level: " + player.getLevel());
  }
  public void playerExp(String channel, String sender, Player player){
    privateMessage(channel, sender, " Exp: " + player.getExp());
  }
  public void playerToNextLevel(String channel, String sender, Player player){
    privateMessage(channel, sender, " Exp To Next Level: " + player.getExpToNextLevel());
  }
  public void playerCurrency(String channel, String sender, Player player){
    privateMessage(channel, sender, " Currency: " + player.getCurrency());
  }
  public void playerCapacity(String channel, String sender, Player player){
    privateMessage(channel, sender, " Capacity: " + player.getCapacity());
  }
  public void playerHealth(String channel, String sender, Player player){
    privateMessage(channel, sender, " Health: " + player.getHealth());
  }
  public void playerMana(String channel, String sender, Player player){
    privateMessage(channel, sender, " Mana: " + player.getMana());
  }
  public void playerStrength(String channel, String sender, Player player){
    privateMessage(channel, sender, " Strength: " + player.getStrength());
  }
  public void playerStamina(String channel, String sender, Player player){
    privateMessage(channel, sender, " Stamina: " + player.getStamina());
  }
  public void playerIntelligence(String channel, String sender, Player player){
    privateMessage(channel, sender, " Intelligence: " + player.getIntelligence());
  }
  public void playerWill(String channel, String sender, Player player){
    privateMessage(channel, sender, " Will: " + player.getWill());
  }
  
  public void messageInit(){
    cyfixusBot.sendRawLineViaQueue("CAP REQ :twitch.tv/commands");
  }

  public void playerSet(String channel, String sender, String target, Player targetPlayer, StatEnum statEnum) {
    messageInit();
    switch(statEnum){
      case TITLE:
        setPlayerTitle(channel, sender, target, targetPlayer);
        break;
      case LEVEL:
        setPlayerLevel(channel, sender, target, targetPlayer);
        break;
      case EXP:
        setPlayerExp(channel, sender, target, targetPlayer);
        break;
      case CURRENCY:
        setPlayerCurrency(channel, sender, target, targetPlayer);
        break;
      case CAPACITY:
        setPlayerCapacity(channel, sender, target, targetPlayer);
        break;
      case HEALTH:
        setPlayerHealth(channel, sender, target, targetPlayer);
        break;
      case MANA:
        setPlayerMana(channel, sender, target, targetPlayer);
        break;
      case STRENGTH:
        setPlayerStrength(channel, sender, target, targetPlayer);
        break;
      case STAMINA:
        setPlayerStamina(channel, sender, target, targetPlayer);
        break;
      case INTELLIGENCE:
        setPlayerIntelligence(channel, sender, target, targetPlayer);
        break;
      case WILL:
        setPlayerWill(channel, sender, target, targetPlayer);
        break;
    }
  }

  public void setPlayerTitle(String channel, String sender, String target, Player player){
    player.setTitle(target);
    privateMessage(channel, sender, " Title: " + player.getTitle());
  }
  public void setPlayerLevel(String channel, String sender,  String target, Player player){
    player.setLevel(Byte.parseByte(target));
    privateMessage(channel, sender, " Level: " + player.getLevel());
  }
  public void setPlayerExp(String channel, String sender, String target,  Player player){
    player.setExp(Long.parseLong(target));
    privateMessage(channel, sender, " Exp: " + player.getExp());
  }
  public void setPlayerCurrency(String channel, String sender,  String target, Player player){
    player.setCurrency(Double.parseDouble(target));
    privateMessage(channel, sender, " Currency: " + player.getCurrency());
  }
  public void setPlayerCapacity(String channel, String sender,  String target, Player player){
    player.setCapacity(Integer.parseInt(target));
    privateMessage(channel, sender, " Capacity: " + player.getCapacity());
  }
  public void setPlayerHealth(String channel, String sender,  String target, Player player){
    player.setHealth(Byte.parseByte(target));
    privateMessage(channel, sender, " Health: " + player.getHealth());
  }
  public void setPlayerMana(String channel, String sender,  String target, Player player){
    player.setMana(Byte.parseByte(target));
    privateMessage(channel, sender, " Mana: " + player.getMana());
  }
  public void setPlayerStrength(String channel, String sender,  String target, Player player){
    player.setStrength(Byte.parseByte(target));
    privateMessage(channel, sender, " Strength: " + player.getStrength());
  }
  public void setPlayerStamina(String channel, String sender,  String target, Player player){
    player.setStamina(Byte.parseByte(target));
    privateMessage(channel, sender, " Stamina: " + player.getStamina());
  }
  public void setPlayerIntelligence(String channel, String sender,  String target, Player player){
    player.setIntelligence(Byte.parseByte(target));
    privateMessage(channel, sender, " Intelligence: " + player.getIntelligence());
  }
  public void setPlayerWill(String channel, String sender,  String target, Player player){
    player.setWill(Byte.parseByte(target));
    privateMessage(channel, sender, " Will: " + player.getWill());
  }
}
