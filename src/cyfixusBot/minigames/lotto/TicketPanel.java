package cyfixusBot.minigames.lotto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JPanel;

import cyfixusBot.gui.components.CyLabel;

public class TicketPanel extends JPanel {
	private Font nameFont;
	private Font ticketFont;
	private Map attributes;
	private String sender;
	private CyLabel winnerName;
	private CyLabel ticket;
	private CyLabel ticket2;
	
	public TicketPanel(){
		super();
		
	}
	
	public TicketPanel(String sender){
		super();
		this.sender = sender;
		setBackground(new Color(3));
        setLayout(new GridBagLayout());
        setLabel();
    	
    	GridBagConstraints gc = new GridBagConstraints();

     	gc.gridx = 0;
     	gc.gridy = 0;
     	gc.fill = GridBagConstraints.NONE;
     	add(ticket, gc);
     	gc.weighty = 1;
     	gc.gridx = 0;
     	gc.gridy = 1;
     	gc.fill = GridBagConstraints.NONE;
     	add(winnerName, gc);
     	gc.weighty = 0;
     	gc.gridx = 0;
     	gc.gridy = 2;
     	gc.fill = GridBagConstraints.NONE;
     	add(ticket2, gc);
	}
	public void setLabel(){
		setNameFont();
		winnerName = new CyLabel(sender);
		winnerName.setFont(nameFont);
		setTicketFont();
		ticket = new CyLabel("-\t-\tTICKET\t-\t-");
		ticket.setFont(ticketFont);
		ticket2 = new CyLabel("-\t-\tTICKET\t-\t-");
		ticket2.setFont(ticketFont);
		
	}

	public void setNameFont(){
		nameFont = this.getFont();
		attributes = nameFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffff00ff));
		attributes.put(TextAttribute.SIZE, 48);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		nameFont = nameFont.deriveFont(attributes);
	}
	public void setTicketFont(){
		ticketFont = this.getFont();
		attributes = ticketFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ffff));
		attributes.put(TextAttribute.SIZE, 24);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		ticketFont = ticketFont.deriveFont(attributes);
	}
}
