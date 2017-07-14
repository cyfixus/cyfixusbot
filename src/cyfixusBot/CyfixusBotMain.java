package cyfixusBot;

import cyfixusBot.bot.CyfixusBot;

public class CyfixusBotMain
{
  private String OAUTH = "";
  private static CyfixusBot bot;
  
  public CyfixusBotMain() {}
  
  public static void main(String[] args) throws Exception {
    try {
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    javax.swing.SwingUtilities.invokeLater(new CyfixusBotMain.1());
  }
  

























  private static void init()
    throws Exception
  {
    bot = new CyfixusBot();
    bot.setVerbose(true);
    bot.connect("irc.chat.twitch.tv", 6667, OAUTH);
    bot.sendRawLineViaQueue("CAP REQ :twitch.tv/membership");
    bot.joinChannel("#cyfixus");
  }
}