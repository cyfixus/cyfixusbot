package cyfixusBot.util;
import java.util.EventListener;

import cyfixusBot.events.FormEvent;

public interface FormListener extends EventListener{
  public void formEventOccurred(FormEvent e);
  public void formRemove(FormEvent e);
  public void formAdd(FormEvent e);
	public void setPlayerStats(FormEvent ev);
	public void getPlayerStats(FormEvent ev);
}
