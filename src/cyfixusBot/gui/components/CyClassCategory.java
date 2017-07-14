package cyfixusBot.gui.components;

public class CyClassCategory {
    private int id;
	private String text;
		
	public CyClassCategory(int id, String text){
			this.id = id;
			this.text = text;
	}
		
	public String toString(){
			return text;
	}
		
	public int getId(){
		return id;
	}
}
