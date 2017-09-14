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

import cyfixusBot.gui.AlertPanel;

import javax.swing.JFrame;

public class LottoFrame extends JFrame implements ActionListener{

  protected String message;
  protected String alert;
  protected Map attributes;
  protected AlertPanel alertPanel;
  protected int time;
  protected int duration;
  
  public LottoFrame(){
    super();
  }
  
  public LottoFrame(String message, String alert, int duration){
    super();
    setFocusableWindowState(false);
    this.message = message;
    this.alert = alert;
    this.duration = duration;
    alertPanel = new AlertPanel(message, alert);
    add(alertPanel);
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(2600, 400);
    setAlwaysOnTop(true);
    setUndecorated(true);

    Timer timer = new Timer(1000, this);
    timer.setInitialDelay(0);
    timer.start();
  }

  public void actionPerformed(ActionEvent arg0) {
    time++;
    if(time % duration == 0){
      setVisible(false);
      this.dispose();
    }
  }
  
  public void setPanel(AlertPanel alertPanel){
    this.alertPanel = alertPanel;
    add(alertPanel);
    repaint();
  }

}
