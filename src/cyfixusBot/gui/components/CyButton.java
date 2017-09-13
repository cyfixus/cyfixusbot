package cyfixusBot.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;

public class CyButton extends JButton {
    private Font font;
    
	public CyButton(String text){
		super(text);
		setFont();
		setBackground(new Color(7));
    	setFont(font);
		
	}
	
	public void setFont(){
		font = this.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ffff));
		attributes.put(TextAttribute.SIZE, 16);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		font = font.deriveFont(attributes);
	}
}
