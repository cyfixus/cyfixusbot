package cyfixusBot.game.players;

import java.util.LinkedList;

import cyfixusBot.game.entities.ObjectID;

public class Users {
  private LinkedList<Player> players = new LinkedList<>();
  
  public Users(){
    
  }
  
  public void setPlayers(LinkedList<Player> players){
    this.players = players;
  }
  
  public String toString(){
    StringBuilder users = new StringBuilder();
    for(Player player: players){
      users.append(player.toString() + "\n");
    }

    return users.toString();
  }
  
  public void addUser(String name){
    Player player = new Player(0, 0, ObjectID.Player, name);
    players.add(player);
    int index = players.indexOf(this);
    player.setIndex(index);
    
  }
  
  public void addUser(String playerString, String delimiter){
    Player player = new Player(ObjectID.Player, playerString, delimiter);
    players.add(player);
    int index = players.indexOf(this);
    player.setIndex(index);
  }
  
  public void printUsers(){
    for(Player player: players){
      player.printAttributes();
    }
  }

  public boolean contains(Player player2) {
    for(Player player: players){
      if(player.getName().equals(player2.getName())){
        return true;
      }
    }
    return false;
  }
  
  public LinkedList<Player> getPlayers(){
    return players;
  }
  
  public void clear(){
    players.clear();
  }
  
  public LinkedList<String> getPlayerNames(){
    LinkedList<String> playerNames = new LinkedList<>();
    for(Player player: players){
      playerNames.add(player.getName());
    }
    return playerNames;
  }
  
  public LinkedList<String> getPlayerStrings(){
    LinkedList<String> playerStrings = new LinkedList<>();
    for(Player player: players){
      playerStrings.add(player.toString());
    }
    return playerStrings;
  }
  
  public Player getPlayer(String sender){
    for(Player player: players){
      if(player.getName().equals(sender)){
        return player;
      }
    }
    return null;
  }
  
  public void removePlayer(String sender){
    for(Player player: players){
      if(player.getName().equals(sender)){
        players.remove(player);
      }
    }
  }
}
