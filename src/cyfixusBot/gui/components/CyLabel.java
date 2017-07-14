package cyfixusBot.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JLabel;

public class CyLabel extends JLabel {
	
	private Font font;
	private Map attributes;
	public CyLabel(String text){
		super(text);
		setFont();
		setFont(font);
	}
	public void setFont(){
		font = this.getFont();
		attributes = font.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xffff00ff));
		attributes.put(TextAttribute.SIZE, 18);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		font = font.deriveFont(attributes);
	}
	public Font getBigFont() {
		Font bigFont = font;
		attributes.put(TextAttribute.SIZE, 24);
		bigFont = bigFont.deriveFont(attributes);
		return bigFont;
	}
}
