package cyfixusBot.gui;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import cyfixusBot.gui.components.CyButton;
import cyfixusBot.util.CyButtonListener;
import cyfixusBot.util.StringListener;

public class Toolbar extends JPanel implements ActionListener{
	private CyButton autoJoin;
	private CyButton getUsers;
	private Font font;
	
	private StringListener textListener;
	private CyButtonListener cyButtonListener;
	
	public Toolbar(){
		setBackground(new Color(3));
		setBorder(BorderFactory.createEtchedBorder());
		setFont();
	
		autoJoin = new CyButton("Auto Join");
		getUsers = new CyButton("Get Users");
		
		autoJoin.addActionListener(this);
		getUsers.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(autoJoin);
		add(getUsers);
	  
    }
	
	public void setStringListener(StringListener listener){
		this.textListener = listener;
	}
	
	public void setCyButtonListener(CyButtonListener listener){
		this.cyButtonListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        if(clicked == autoJoin){
        	if(textListener != null){
        		textListener.textEmitted("autojoin\n");
        	}
        	if(cyButtonListener != null){
        		cyButtonListener.cyButtonClicked("autojoin");
        	}
        }
        else if(clicked == getUsers){
        	if(textListener != null){
        		textListener.textEmitted("getusers\n");
        	}
        	if(cyButtonListener != null){
        		cyButtonListener.cyButtonClicked("getusers");
        	}
        }
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
