package cyfixusBot.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JPanel;

import cyfixusBot.gui.components.CyLabel;

public class AlertPanel extends JPanel {
	protected Font messageFont;
	protected Font alertFont;
	protected Map attributes;
	protected String message;
	protected String alert;
	protected CyLabel messageLabel;
	protected CyLabel alertLabel;
	protected CyLabel alertLabel2;

	public AlertPanel(String message, String alert) {
		super();
		this.message = message;
		this.alert = alert;
		setBackground(new Color(3));
        setLayout(new GridBagLayout());
        setLabel();
    	
    	GridBagConstraints gc = new GridBagConstraints();

     	gc.gridx = 0;
     	gc.gridy = 0;
     	gc.fill = GridBagConstraints.NONE;
     	add(alertLabel, gc);
     	gc.weighty = 1;
     	gc.gridx = 0;
     	gc.gridy = 1;
     	gc.fill = GridBagConstraints.NONE;
     	add(messageLabel, gc);
     	gc.weighty = 0;
     	gc.gridx = 0;
     	gc.gridy = 2;
     	gc.fill = GridBagConstraints.NONE;
     	add(alertLabel2, gc);
	}
	public void setLabel(){
		setMessageFont();
		messageLabel = new CyLabel(message);
		messageLabel.setFont(messageFont);
		setAlertFont();
		alertLabel = new CyLabel("-\t-\t" + alert + "\t-\t-");
		alertLabel.setFont(alertFont);
		alertLabel2 = new CyLabel("-\t-\t" + alert + "\t-\t-");
		alertLabel2.setFont(alertFont);
		
	}

	public void setMessageFont(){
		messageFont = this.getFont();
		attributes = messageFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffff00ff));
		attributes.put(TextAttribute.SIZE, 48);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		messageFont = messageFont.deriveFont(attributes);
	}
	public void setAlertFont(){
		alertFont = this.getFont();
		attributes = alertFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ffff));
		attributes.put(TextAttribute.SIZE, 24);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		alertFont = alertFont.deriveFont(attributes);
	}

}
