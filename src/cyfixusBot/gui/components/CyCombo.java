package cyfixusBot.gui.components;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JComboBox;

public class CyCombo extends JComboBox {
	private Font font;
    
	public CyCombo(){
		super();
		setFont();
		setBackground(new Color(7));
  	setFont(font);
  	setSelectionBackground(new Color(3476));
  	setForeground(new Color(0xffff00ff));
	}
		
	private void setSelectionBackground(Color color) {
		// TODO Auto-generated method stub
		
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