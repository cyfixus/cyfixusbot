package cyfixusBot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import cyfixusBot.game.players.Player;

public class DataManager {
	private FileOutputStream fout;
	private FileInputStream fin;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private LinkedList<String> players;
	
	public DataManager(){
		load();
	}
	
	public LinkedList<String> load(){
		players = new LinkedList<>();
		try {
			fin = new FileInputStream("users.txt");
			players = (LinkedList<String>)in.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return players;
		
	}
	
	public void save(LinkedList<String> players){
		try{
		fout = new FileOutputStream("users.txt");
		out = new ObjectOutputStream(fout);
		out.writeObject(players);
		out.close();
		System.out.println("Save success");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
