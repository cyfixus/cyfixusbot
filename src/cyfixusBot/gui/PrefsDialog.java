package cyfixusBot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;

import cyfixusBot.gui.components.CyButton;
import cyfixusBot.gui.components.CyConnectionButton;
import cyfixusBot.gui.components.CyLabel;
import cyfixusBot.gui.components.CyPasswordField;
import cyfixusBot.gui.components.CyTextField;

public class PrefsDialog extends JDialog{
	
	private CyButton showButton;
	private CyButton connectButton;
	private CyButton okButton;
	private CyButton cancelButton;
	
	private CyConnectionButton channelButton;
	
	private CyTextField channelField;
	private CyPasswordField oauthField;
	private Font font;
	
	
	
	private MainFrame mainFame;
	private PrefsListener prefsListener;
	
	public PrefsDialog(MainFrame mainFrame){
		super(mainFrame, "connection", false);
		setUndecorated(true);
		setSize(400, 230);
		setLocationRelativeTo(mainFrame);
		this.mainFame = mainFrame;
		showButton = new CyButton("show");
		connectButton = new CyButton("connect");
		okButton = new CyButton("OK");
		cancelButton = new CyButton("cancel");
		
		channelButton = new CyConnectionButton("");
		
		channelField = new CyTextField(24);
		oauthField = new CyPasswordField(24);
		
		layoutControls();
		
		showButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(showButton.getText() == "show"){
					oauthField.setEchoChar((char)0);
					showButton.setText("hide");
				}
				else{
					oauthField.setEchoChar('*');
					showButton.setText("show");
				}
				
			}
		});
		
		connectButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String channel = channelField.getText();
				char[] oauth = oauthField.getPassword();
				try {
					mainFrame.stageBot(channel, new String(oauth));
				} catch (NickAlreadyInUseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IrcException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		okButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {				
				String channel = channelField.getText();
				char[] oauth = oauthField.getPassword();
				
				if(prefsListener != null){
					prefsListener.preferencesSet(channel, new String(oauth));
				}
				setVisible(false);

			}
		});
		
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
			
		});

	}
	
	public void passButton(CyConnectionButton prefButton){
		this.channelButton = prefButton;
	}
	
	private void layoutControls(){
		
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		controlsPanel.setBackground(new Color(3));
		buttonsPanel.setBackground(new Color(3));
		setFont(controlsPanel);
		setFont(buttonsPanel);
		controlsPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridy = 0;
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		//// first row
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new CyLabel("channel: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(channelField, gc);
		
	    //next row
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlsPanel.add(new CyLabel("oauth: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(oauthField, gc);
		gc.gridy++;
		controlsPanel.add(showButton, gc);

		//  buttons panel
		gc.gridx = 0;
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(connectButton);
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		//panels to dialog
		
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	public void setDefaults(String channel, String oauth){
		channelField.setText(channel);
		oauthField.setText(oauth);
	}
	public void setPrefsListener(PrefsListener prefsListner){
		this.prefsListener = prefsListner;
	}
	
	public void setFont(JPanel panel){
		font = panel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ffff));
		attributes.put(TextAttribute.SIZE, 16);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		font = font.deriveFont(attributes);
		panel.setFont(font);
	}

}