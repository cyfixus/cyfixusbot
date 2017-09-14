package cyfixusBot.minigames.lotto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

import javax.swing.Timer;

import cyfixusBot.gui.AlertPanel;
import cyfixusBot.gui.components.CyLabel;

public class WinnerPanel extends AlertPanel implements ActionListener{
  private int i = 0;
  private int time = 0;

	public WinnerPanel(String message, String alert) {
		super(message, alert);
		Timer timer = new Timer(400, this);
        timer.setInitialDelay(0);
        timer.start();
        }

	public void actionPerformed(ActionEvent e) {
		flash(i);
	}
	
	public void flash(int i){
		switch(i){
		case 0:
			setAlertFont2();
			break;
		case 1:
			setAlertFont3();
			setMessageFont2();
            break;
		case 2:
			setAlertFont4();
			break;
		case 3:
			setAlertFont5();
			setMessageFont3();
			break;
		case 4:
			setAlertFont6();
			break;
		default:
			setAlertFont();
			setMessageFont();
			break;
		}
		this.messageLabel.setFont(messageFont);
		this.alertLabel.setFont(alertFont);
		this.alertLabel2.setFont(alertFont);
		this.i++;
		if(this.i > 5){
			this.i = 0;
		}
	}
	
	public void setMessageFont2(){
		messageFont = this.getFont();
		attributes = messageFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffffff00));
		attributes.put(TextAttribute.SIZE, 64);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		messageFont = messageFont.deriveFont(attributes);
	}
	public void setMessageFont3(){
		messageFont = this.getFont();
		attributes = messageFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ffff));
		attributes.put(TextAttribute.SIZE, 80);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		messageFont = messageFont.deriveFont(attributes);
	}

	public void setAlertFont2(){
		this.alertFont = this.getFont();
		attributes = this.alertFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffff00ff));
		attributes.put(TextAttribute.SIZE, 26);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		this.alertFont = this.alertFont.deriveFont(attributes);
	}
	public void setAlertFont3(){
		this.alertFont = this.getFont();
		attributes = this.alertFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffffff00));
		attributes.put(TextAttribute.SIZE, 28);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		this.alertFont = this.alertFont.deriveFont(attributes);
	}
	public void setAlertFont4(){
		this.alertFont = this.getFont();
		attributes = this.alertFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffff0000));
		attributes.put(TextAttribute.SIZE, 32);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		this.alertFont = this.alertFont.deriveFont(attributes);
	}
	public void setAlertFont5(){
		this.alertFont = this.getFont();
		attributes = this.alertFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ff00));
		attributes.put(TextAttribute.SIZE, 28);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		this.alertFont = this.alertFont.deriveFont(attributes);
	}
	public void setAlertFont6(){
		this.alertFont = this.getFont();
		attributes = this.alertFont.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff0000ff));
		attributes.put(TextAttribute.SIZE, 26);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		this.alertFont = this.alertFont.deriveFont(attributes);
	}
}
