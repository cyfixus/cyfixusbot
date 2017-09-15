package cyfixusBot;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cyfixusBot.gui.MainFrame;

public class CyfixusBotMain implements ActionListener{
	static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"foo", "bar", "baz"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120,140,200,40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading "+comps[(frame/5)%3]+"...", 120, 150);
    }
    public CyfixusBotMain() {
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            System.out.println("g is null");
            return;
        }
        for(int i=0; i<10; i++) {
            renderSplashFrame(g, i);
            splash.update();
            try {
                Thread.sleep(90);
            }
            catch(InterruptedException e) {
            }
        }
        splash.close();
    }
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }
     
    private static WindowListener closeWindow = new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            e.getWindow().dispose();
        }
    };

    public static void main(String[] args) throws Exception {
      System.out.println(args.length>1?args[1]:"no args");
      CyfixusBotMain splash = new CyfixusBotMain();
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          MainFrame mainFrame = new MainFrame(); 
          mainFrame.setIconImage(new ImageIcon("ico.png").getImage());
          showOnScreen(args.length > 1?Integer.parseInt(args[1]):0, (JFrame)mainFrame);
          mainFrame.setVisible(true);
        }
      });
        
    }
    
    public static void showOnScreen( int screen, JFrame frame ) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        switch(screen) {
          case 0:
            frame.setLocation((gd[screen].getDefaultConfiguration().getBounds().width) - frame.getWidth()
            		, gd[screen].getDefaultConfiguration().getBounds().y);
            break;
          case 1:
            frame.setLocation((gd[screen].getDefaultConfiguration().getBounds().width*2) - frame.getWidth()/2
            		, gd[screen].getDefaultConfiguration().getBounds().y);
            break;
          default:
            break;
        }
    }

}