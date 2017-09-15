package cyfixusBot.gui;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import cyfixusBot.bot.CyfixusBot;
import cyfixusBot.events.FormEvent;
import cyfixusBot.game.players.Player;
import cyfixusBot.minigames.lotto.WinnerPanel;
import cyfixusBot.util.CyButtonListener;
import cyfixusBot.util.FormListener;
import cyfixusBot.util.LottoListener;
import cyfixusBot.util.StringListener;

public class MainFrame extends JFrame{
  private CyfixusBot bot;
  private TextPanel textPanel;
  private Toolbar toolbar;
  private FormPanel formPanel;
  private String name;
  private String title;
  private String classString;
  private FormListener formListener;
  private Player player;
  private PrefsDialog prefsDialog;
  private Preferences prefs;
  private boolean extended;

  public MainFrame(int extended){
    super("cyfixusbot");
    if(extended == 1){
    	this.extended = true;
    }
    else
    {
    	this.extended = false;
    }
    prefsDialog = new PrefsDialog(this);
    prefs = Preferences.userRoot().node("db");
    prefsDialog.setPrefsListener(new PrefsListener() {
      @Override
      public void preferencesSet(String channel, String oauth) {
        prefs.put("channel", channel);
        prefs.put("oauth", oauth);
      } 
    });
    try {
      init();
    } catch (Exception e) {
      System.out.println("can't connect");
    }
    setLayout(new BorderLayout());     
    textPanel = new TextPanel();
    toolbar = new Toolbar(prefsDialog, prefs);
   
    initBot();
    formPanel = new FormPanel(bot);
    initToolbar();
    initFormListener();
     
    add(toolbar, BorderLayout.NORTH);
    add(textPanel, BorderLayout.SOUTH);
    add(formPanel, BorderLayout.WEST);
     
    setSize(500, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
  }
  
  private void init() throws Exception{
    String oauth = prefs.get("oauth", "");
    String channel = prefs.get("channel", "");
    prefsDialog.setDefaults(channel, oauth);
    stageBot(channel, oauth);
  }
    
  protected void stageBot(String channel, String oauth) 
      throws NickAlreadyInUseException, IOException, IrcException{

    String oauthIn = "oauth:" + oauth;
    bot = new CyfixusBot();
    bot.setVerbose(true);
    bot.disconnect();
    bot.connect("irc.chat.twitch.tv", 6667, oauthIn);
    bot.sendRawLineViaQueue("CAP REQ :twitch.tv/membership");
    bot.joinChannel("#" + channel);
    bot.sendRawLineViaQueue("CAP REQ :twitch.tv/membership");
  }
  
  private void initBot(){
    bot.addStringListener(new StringListener(){
      
      public void textEmitted(String text){
        textPanel.appendText(text);
        textPanel.updateView();
        String[] splitText = text.split(" ");
        if(splitText[0].equals("Welcome")){
          AlertFrame welcome = new AlertFrame(splitText[1], "welcome", 5, extended);
          welcome.setVisible(true);
        }
      }  
    });
    
    bot.addLottoListener(new LottoListener(){

      public void buyTicket(String sender) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

          @Override
          protected Void doInBackground() throws Exception {
            AlertFrame lotto = new AlertFrame(sender, "ticket", 3, extended);
            lotto.setVisible(true);
            return null;
          }
        };
        worker.execute();
      }
      public void announceWinner(String winner) {
        AlertFrame lotto = new AlertFrame(winner, "winner", 5, extended);
        lotto.resize(500, 300);
        lotto.setPanel(new WinnerPanel(winner, "Winner"));
        lotto.setVisible(true);
      } 
    });
  }
  
  public void initToolbar(){
    toolbar.setStringListener(new StringListener(){
      public void textEmitted(String text){
        textPanel.appendText(bot.getAllPlayers());
      }
    });
     
    toolbar.setCyButtonListener(new CyButtonListener(){
      public void cyButtonClicked(String command){
        switch(command){
          case "autojoin":
            bot.autoJoin();
            break;
          case "getusers":
            bot.getUsers();
            break;
        }
      }
    });
  }
  public void initFormListener(){
    
    formListener = new FormListener(){
      public void formEventOccurred(FormEvent e) {
        name = e.getName();
        title = e.getTitle();
        classString = e.getClassString();
     
        textPanel.appendText("name: " + name + "\t");
        textPanel.appendText("title: " + title + "\t");
        textPanel.appendText("class: " + classString + "\n");
      }
      public void formRemove(FormEvent e) {
        name = e.getName();
        if(bot.removePlayerGUI(name)){
          textPanel.appendText("removed: " + name + '\n');
        }
      }

      public void formAdd(FormEvent e) {
        name = e.getName();
        bot.addPlayer(name);
        textPanel.appendText("added: " + name + '\n');
      }

      public void setPlayerStats(FormEvent ev) {
        bot.gmAddPlayer(ev.getName(), ev.getTitle(),
          (byte)(ev.getLevel()), ev.getCapacity(), 
          ev.getExp(), 
          (byte)(ev.getHealth()), (byte)(ev.getMana()),
          ev.getCurrency(), (byte)(ev.getStrength()), 
          (byte)(ev.getStamina()), (byte)(ev.getIntelligence()),
          (byte)(ev.getWill()), ev.getPlayerClass());
        textPanel.appendText("set " + ev.getName() +" stats\n");
      }
      
      public void getPlayerStats(FormEvent ev){
        name = ev.getName();
        try{
        player = bot.getPlayer(name);
        formPanel.setStats(name, player.getTitle(),
              player.getLevel(), player.getCapacity(),
              player.getExp(), player.getExpToNextLevel(),
              player.getHealth(), player.getMana(),
              player.getCurrency(), player.getStrength(),
              player.getStamina(), player.getIntelligence(),
              player.getWill(), player.getPlayerClass());
        textPanel.appendText("get " + name + " stats\n");
        }catch(Exception e){} 
      }
    };
    formPanel.setFormListener(formListener);
  }
}