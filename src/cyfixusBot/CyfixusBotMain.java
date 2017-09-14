package cyfixusBot;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import cyfixusBot.bot.CyfixusBot;
import cyfixusBot.gui.MainFrame;

public class CyfixusBotMain {

    public static void main(String[] args) throws Exception {
    	SwingUtilities.invokeLater(new Runnable(){
    		public void run(){
    			MainFrame mainFrame = new MainFrame(); 
    			mainFrame.setIconImage(new ImageIcon("ico.png").getImage());
    			mainFrame.setVisible(true);
    		}
    	});
        
    }

}