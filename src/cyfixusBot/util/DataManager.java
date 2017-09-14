package cyfixusBot.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class DataManager {
  
  private FileWriter fout;
  private FileInputStream fin;
  private File file;
  private BufferedWriter out;
  private ObjectInputStream in;
  private LinkedList<String> players;
  private String player;
  private StringBuilder saveString;
  
  public DataManager(){
    load();
  }
  
  public LinkedList<String> load(){
    saveString = new StringBuilder();
    players = new LinkedList<>();
    try {
      file = new File("users.txt");
      Scanner sc = new Scanner(new FileInputStream(file));
      System.out.println(sc.nextLine());
      while(sc.hasNextLine()){
        player = sc.nextLine();
        players.add(player);
        saveString.append(player + "\n");
        System.out.println(player);
      }
      sc.close();
    } catch (Exception e) {
      System.out.println("unable to load: " + file);
    }
    return players;
    
  }
  
  public void save(LinkedList<String> players){
    saveString = new StringBuilder();
      saveString.append("name\t\ttitle\tcurrency level  "  
           + "exp  health  mana  strength  " 
           + "stamina  intelligence  will\n");
    for(String player: players){
      saveString.append(player + "\n");
      System.out.println(player);
    }
    
    try{
    fout = new FileWriter("users.txt");
    out = new BufferedWriter(fout);
    out.write(saveString.toString());
    out.close();
    System.out.println("Save success");
    }catch(Exception e){
      System.out.println("unable to save: " + fout);
    }
  }
  
  public LinkedList<String> getPlayers(){
    return players;
  }
  
  public String getPlayersString(){
    return saveString.toString();
  }

}
