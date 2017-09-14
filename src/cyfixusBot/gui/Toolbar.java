package cyfixusBot.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cyfixusBot.gui.components.CyButton;
import cyfixusBot.gui.components.CyConnectionButton;
import cyfixusBot.util.CyButtonListener;
import cyfixusBot.util.StringListener;

public class Toolbar extends JPanel implements ActionListener{
	private CyButton autoJoin;
	private CyButton getUsers;
	private CyConnectionButton prefButton;
	private PrefsDialog prefsDialog;
	private Preferences prefs;
	private Font font;

	private StringListener textListener;
	private CyButtonListener cyButtonListener;
	
	public Toolbar(PrefsDialog prefsDialog, Preferences prefs){
		this.prefsDialog = prefsDialog;
		this.prefs = prefs;
		setBackground(new Color(3));
		setBorder(BorderFactory.createEtchedBorder());
		setFont();
	
		autoJoin = new CyButton("Auto Join");
		getUsers = new CyButton("Get Users");
		prefButton = new CyConnectionButton("#" + prefs.get("channel", ""));

		
		autoJoin.addActionListener(this);
		getUsers.addActionListener(this);
		
		
		prefButton.addActionListener(this);
		prefButton.setBorderPainted(false);
//		prefButton.setPreferredSize(new Dimension(200, 16));
//		prefButton.setMinimumSize(prefButton.getPreferredSize());
		prefButton.setHorizontalAlignment(SwingConstants.RIGHT);
		prefButton.setFont();
		setConnectionIcon(0);
		
		setLayout(new GridBagLayout());
    	
    GridBagConstraints gc = new GridBagConstraints();
 //                       -   -  COL 1   	
    gc.weightx = 0;
  	gc.gridx = 0;
  	gc.gridy = 0;
  	gc.fill = GridBagConstraints.NONE;
  	gc.anchor = GridBagConstraints.LINE_START;
  	add(autoJoin, gc);
  	gc.gridx++;
  	gc.weightx = 3;
  	add(getUsers, gc);
  	gc.weightx = 0;
  	gc.gridx++;
  	add(prefButton, gc);
	  
  }
	
	public void setConnectionIcon(int index){
		String icon = "";
		switch(index){
			case 1:
				icon = "status_connected.png";
				break;
			case 2:
				icon = "status_transmitting.png";
				break;
			case 3:
				icon = "status_disconnected.png";
				break;
			default:
				icon = "status_idle.png";
				break;
		}
		prefButton.setIcon(createIcon(icon));
		prefButton.repaint();
	}
	
	private ImageIcon createIcon(String path){
		URL url = getClass().getResource(path);
		
		if(url == null){
			System.out.println("unable to load icon");
		}
		
		ImageIcon icon = new ImageIcon(url);
		
		return icon;
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
    else if(clicked == prefButton){
    	prefsDialog.setVisible(true);
    	prefsDialog.passButton(prefButton);
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
