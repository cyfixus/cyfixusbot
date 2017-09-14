package cyfixusBot.minigames.lotto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.Timer;

public class TicketFrame extends JFrame implements ActionListener{

  private String sender;
  private Map attributes;
  private TicketPanel ticketPanel;
  private int time;
  
  public TicketFrame(){
    super("Ticket");
  }
  
  public TicketFrame(String sender){
    super("Ticket");
    setFocusableWindowState(false);
    this.sender = sender;
    ticketPanel = new TicketPanel(sender);
    add(ticketPanel);
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(2600, 400);
    setAlwaysOnTop(true);
    
    Timer timer = new Timer(1000, this);
    timer.setInitialDelay(0);
    timer.start();
    
  }

  public void actionPerformed(ActionEvent arg0) {
    time++;
    if(time % 3 == 0){
      setVisible(false);
      dispose();
    }
  }

}
