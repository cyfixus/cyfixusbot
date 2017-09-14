package cyfixusBot.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{
	private Font font;
  
	private JTextArea textArea;
	private JScrollPane scrollPane;
	
	public TextPanel(){
		Dimension dim = getPreferredSize();
  	dim.width = 300;
  	dim.height = 250;
  	setPreferredSize(dim);
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(3));
		font = textArea.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.FOREGROUND, new Color(0xff00ff00));
		attributes.put(TextAttribute.SIZE, 16);
		attributes.put(TextAttribute.WIDTH, TextAttribute.WIDTH_REGULAR);
		attributes.put(TextAttribute.FAMILY, "Nimbus Mono L");
		attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
		textArea.setFont(font.deriveFont(attributes));
		
		setLayout(new BorderLayout());
		scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().scrollRectToVisible(getBounds());
	}
	
	public void appendText(String text){
		textArea.append(text);
	}
	
	public void updateView(){
		scrollPane.getViewport().scrollRectToVisible(getBounds());
	}
	
	public JTextArea getTextArea(){
		return textArea;
	}


}
