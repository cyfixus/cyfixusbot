package cyfixusBot.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import cyfixusBot.bot.CyfixusBot;
import cyfixusBot.events.FormEvent;
import cyfixusBot.gui.components.CyButton;
import cyfixusBot.gui.components.CyClassCategory;
import cyfixusBot.gui.components.CyCombo;
import cyfixusBot.gui.components.CyLabel;
import cyfixusBot.gui.components.CyList;
import cyfixusBot.gui.components.CyTextField;
import cyfixusBot.util.FormListener;

public class FormPanel extends JPanel {
	
	private String name;
	private String title;
	private int level;
	private double capacity;
	private long exp;
	private long toNextLevel;
    private int health;
    private int mana;
	private double currency;
    private int strength;
    private int stamina;
    private int intelligence;
    private int will;
	
	private CyLabel nameLabel = new CyLabel("name:");
	private CyLabel titleLabel = new CyLabel("title:");
	private CyLabel levelLabel = new CyLabel("level:");
	private CyLabel expLabel = new CyLabel("exp:");
	private CyLabel toNextLevelLabel = new CyLabel("tnl:");
	private CyLabel healthLabel = new CyLabel("health:");
	private CyLabel manaLabel = new CyLabel("mana:");
	private CyLabel currencyLabel = new CyLabel("curr:");
	private CyLabel capacityLabel = new CyLabel("cap:");
	private CyLabel strengthLabel = new CyLabel("str:");
	private CyLabel staminaLabel = new CyLabel("sta:");
	private CyLabel intelligenceLabel = new CyLabel("int:");
	private CyLabel willLabel = new CyLabel("will:");
	private CyLabel classLabel = new CyLabel("class:");
	private CyTextField nameField = new CyTextField(10);
	private CyTextField titleField = new CyTextField(5);
	private CyTextField levelField = new CyTextField(10);
	private CyTextField expField = new CyTextField(5);
	private CyTextField toNextLevelField = new CyTextField(10);
	private CyTextField healthField = new CyTextField(5);
	private CyTextField manaField = new CyTextField(10);
	private CyTextField currencyField = new CyTextField(5);
	private CyTextField capacityField = new CyTextField(10);
	private CyTextField strengthField = new CyTextField(5);
	private CyTextField staminaField = new CyTextField(10);
	private CyTextField intelligenceField = new CyTextField(5);
	private CyTextField willField = new CyTextField(10);
	private CyButton addBtn = new CyButton("++");
	private CyButton rmvBtn = new CyButton("--");
	private CyButton getBtn = new CyButton("get");
	private CyButton setBtn = new CyButton("set");
	private FormListener formListener;
	private CyList classList;
	private CyCombo classCombo;
	private CyfixusBot bot;

	
    public FormPanel(CyfixusBot bot){
    	setBackground(new Color(3));
    	this.bot = bot;
    	Dimension dim = getPreferredSize();
    	dim.width = 410;
    	setPreferredSize(dim);
    	
    	classList = new CyList();
    	classCombo = new CyCombo();
    	
    	DefaultComboBoxModel classModel = new DefaultComboBoxModel();
    	classModel.addElement(new CyClassCategory(0, "Grunt"));
    	classModel.addElement(new CyClassCategory(1,"Seer"));
    	classModel.addElement(new CyClassCategory(2,"Sneak"));
    	classModel.addElement(new CyClassCategory(3,"Tradesmith"));
    	classCombo.setModel(classModel);
    	
    	classList.setPreferredSize(new Dimension(110, 66));
    	classList.setBorder(BorderFactory.createEtchedBorder());
    	classList.setSelectedIndex(1);

    	
    	addBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
    			FormEvent ev = new FormEvent(this, name);
    			if(formListener != null){
    				formListener.formAdd(ev);
    			}
			}
    	});
    	
    	rmvBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			String name = nameField.getText();
    			FormEvent ev = new FormEvent(this, name);
    			if(formListener != null){
    				formListener.formRemove(ev);
    			}
    		}
    	});
    	
    	setBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			name = nameField.getText();
    			title = titleField.getText();
    			level = Integer.parseInt(levelField.getText());
    			capacity = Double.parseDouble(capacityField.getText());
    			exp = Long.parseLong(expField.getText());
    			health = Integer.parseInt(healthField.getText());
    			mana = Integer.parseInt(manaField.getText());
    			currency = Double.parseDouble(currencyField.getText());
    			strength = Integer.parseInt(strengthField.getText());
    			stamina = Integer.parseInt(staminaField.getText());
    			intelligence = Integer.parseInt(intelligenceField.getText());
    			will = Integer.parseInt(willField.getText());
    			FormEvent ev = new FormEvent(this, name, title, 
    					                   level, capacity, 
    					                   exp,
    					                   health, mana,
    					                   currency, strength,
    					                   stamina, intelligence,
    					                   will);
    			if(formListener != null){
    				formListener.setPlayerStats(ev);
    			}
    		}
    	});
    	
    	getBtn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			name = nameField.getText();
    			FormEvent ev = new FormEvent(this, name);
    			if(formListener != null){
    				formListener.getPlayerStats(ev);
    			}
    		}
    	});
    	Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    	Border innerBorder = BorderFactory.createTitledBorder(outerBorder, 
    			"GAME MASTER", TitledBorder.CENTER, TitledBorder.TOP, 
    			nameLabel.getBigFont());
    	
    	setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    	
    	setLayout(new GridBagLayout());
    	
    	GridBagConstraints gc = new GridBagConstraints();
 //                       -   -  COL 1   	
        gc.weightx = 0.25;
    	gc.gridx = 0;
    	gc.gridy = 0;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(nameLabel, gc);
    	

    	gc.gridx = 0;
    	gc.gridy = 1;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(levelLabel, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 2;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(expLabel, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 3;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(healthLabel, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 4;
    	gc.fill = GridBagConstraints.NONE;
    	add(currencyLabel, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 5;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(staminaLabel, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 6;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(willLabel, gc);
    	
    	
    	//Buttons
    	gc.weightx = 1;
    	gc.weighty = 1;
    	gc.gridx = 0;
    	gc.gridy = 8;
    	gc.anchor = GridBagConstraints.FIRST_LINE_START;
    	add(addBtn, gc);
    	gc.gridx = 1;
    	gc.gridy = 8;
    	add(rmvBtn, gc);
    	gc.gridx = 2;
    	gc.gridy = 8;
    	add(getBtn, gc);
    	gc.gridx = 3;
    	gc.gridy = 8;
    	add(setBtn, gc);
    	
//                       -   -  COL 2
        gc.weightx = 0.8;
        gc.weighty = 0;
    	gc.gridx = 1;
    	gc.gridy = 0;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(nameField, gc);
    	
    	gc.gridx = 1;
    	gc.gridy = 1;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(levelField, gc);
    	
    	gc.gridx = 1;
    	gc.gridy = 2;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(expField, gc);
    	
    	gc.gridx = 1;
    	gc.gridy = 3;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(healthField, gc);
    	
    	gc.gridx = 1;
    	gc.gridy = 4;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(currencyField, gc);
    	
    	gc.gridx = 1;
    	gc.gridy = 5;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(staminaField, gc);
    	
    	gc.gridx = 1;
    	gc.gridy = 6;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(willField, gc);

    	
//      -   -  COL 3
    	gc.weightx = 0.1;
    	gc.weighty = 0;
    	gc.gridx = 2;
    	gc.gridy = 0;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(titleLabel, gc);
    	
       	gc.gridx = 2;
    	gc.gridy = 1;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(capacityLabel, gc);
    	
    	
    	gc.gridx = 2;
    	gc.gridy = 2;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(toNextLevelLabel, gc);
    	
    	gc.gridx = 2;
    	gc.gridy = 3;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(manaLabel, gc);
    	
    	gc.gridx = 2;
    	gc.gridy = 4;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(strengthLabel, gc);
    	
    	gc.gridx = 2;
    	gc.gridy = 5;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(intelligenceLabel, gc);
    	
    	gc.gridx = 2;
    	gc.gridy = 6;
    	gc.fill = GridBagConstraints.NONE;
    	gc.anchor = GridBagConstraints.LINE_START;
    	add(classLabel, gc);
    	
    	
//      -   -  COL 4
        gc.weightx = 0;
    	gc.gridx = 3;
    	gc.gridy = 0;
    	gc.anchor = GridBagConstraints.LINE_END;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(titleField, gc);
    	
    	gc.gridx = 3;
    	gc.gridy = 1;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	gc.anchor = GridBagConstraints.LINE_END;
    	add(capacityField, gc);
    	
    	gc.gridx = 3;
    	gc.gridy = 2;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(toNextLevelField, gc);
    	
    	gc.gridx = 3;
    	gc.gridy = 3;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(manaField, gc);
    	
    	gc.gridx = 3;
    	gc.gridy = 4;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(strengthField, gc);
    	
    	gc.gridx = 3;
    	gc.gridy = 5;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(intelligenceField, gc);
    	 	

    	gc.gridx = 3;
    	gc.gridy = 6;
    	gc.anchor = GridBagConstraints.FIRST_LINE_START;
    	add(classCombo, gc);
    	
    	

    	
    }

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public void setStats(String name, String title,
			             int level, double capacity,
			             long exp, long toNextLevel,
			             int health, int mana,
			             double currency, int strength,
			             int stamina, int intelligence,
			             int will) {
		this.name = name;
		this.title = title;
		this.level = level;
		this.capacity = capacity;
		this.exp = exp;
		this.toNextLevel = toNextLevel;
		this.health = health;
		this.mana = mana;
		this.currency = currency;
		this.strength = strength;
		this.stamina = stamina;
		this.intelligence = intelligence;
		this.will = will;
		fillTextFields(name, title,
				level, capacity,
				exp, toNextLevel,
				health, mana,
				currency, strength,
				stamina, intelligence,
				will);
	}
	public void fillTextFields(String name, String title,
            					int level, double capacity,
            					long exp, long toNextLevel,
            					int health, int mana,
            					double currency, int strength,
            					int stamina, int intelligence,
            					int will) {
		nameField.setText(name);
		titleField.setText(title);
		levelField.setText(level+"");
		capacityField.setText(capacity+"");
		expField.setText(exp+"");
		toNextLevelField.setText(toNextLevel+"");
		healthField.setText(health+"");
		manaField.setText(mana+"");
		currencyField.setText(currency+"");
		strengthField.setText(strength+"");
		staminaField.setText(stamina+"");
		intelligenceField.setText(intelligence+"");
		willField.setText(will+"");
	}
}

