package cyfixusBot.gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
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
  private byte level;
  private double capacity;
  private long exp;
  private long toNextLevel;
  private byte health;
  private byte mana;
  private double currency;
  private byte strength;
  private byte stamina;
  private byte intelligence;
  private byte will;
  private int playerClass;
  
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
  private CyCombo nameCombo;
  
  private CyfixusBot bot;

  
    public FormPanel(CyfixusBot bot){
      this.bot = bot;
      setBackground(new Color(3));
      Dimension dim = getPreferredSize();
      dim.width = 500;
      setPreferredSize(dim);
      nameCombo = new CyCombo();
      nameCombo.setEditable(true);
      nameCombo.getEditor().getEditorComponent().setBackground(new Color(7));
      loadNameCombo();
      
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
            loadNameCombo();
            bot.save();
          }
      }
      });
      
      rmvBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String name = nameField.getText();
          FormEvent ev = new FormEvent(this, name);
          if(formListener != null){
            formListener.formRemove(ev);
            loadNameCombo();
            bot.save();
          }
        }
      });
      
      setBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          name = new String(nameCombo.getSelectedItem().toString());
          title = titleField.getText();
          level = Byte.parseByte(levelField.getText());
          capacity = Double.parseDouble(capacityField.getText());
          exp = Long.parseLong(expField.getText());
          health = Byte.parseByte(healthField.getText());
          mana = Byte.parseByte(manaField.getText());
          currency = Double.parseDouble(currencyField.getText());
          strength = Byte.parseByte(strengthField.getText());
          stamina = Byte.parseByte(staminaField.getText());
          intelligence = Byte.parseByte(intelligenceField.getText());
          will = Byte.parseByte(willField.getText());
          playerClass = classCombo.getSelectedIndex();
          System.out.println("Player class: " + playerClass);
          FormEvent ev = new FormEvent(this, name, title, 
                                 level, capacity, 
                                 exp,
                                 health, mana,
                                 currency, strength,
                                 stamina, intelligence,
                                 will, playerClass);
          if(formListener != null){
            formListener.setPlayerStats(ev);
            loadNameCombo();
            bot.save();
          }
        }
      });
      
      getBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          name = new String(nameCombo.getSelectedItem().toString());
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
      add(nameCombo, gc);
      
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
    
    public void loadNameCombo(){
      int count = 0;
      LinkedList<String> players = bot.getPlayerNames();
      DefaultComboBoxModel classModel = new DefaultComboBoxModel();
      for(String player: players){
        classModel.addElement(new CyClassCategory(count++, player));
      }
      nameCombo.setModel(classModel);
    }

  public void setFormListener(FormListener listener) {
    this.formListener = listener;
  }

  public void setStats(String name, String title,
                   byte level, double capacity,
                   long exp, long toNextLevel,
                   byte health, byte mana,
                   double currency, byte strength,
                   byte stamina, byte intelligence,
                   byte will, int playerClass) {
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
    this.playerClass = playerClass;
    
    fillTextFields(name, title,
        level, capacity,
        exp, toNextLevel,
        health, mana,
        currency, strength,
        stamina, intelligence,
        will, playerClass);
  }
  public void fillTextFields(String name, String title,
                      byte level, double capacity,
                      long exp, long toNextLevel,
                      byte health, byte mana,
                      double currency, byte strength,
                      byte stamina, byte intelligence,
                      byte will, int playerClass) {
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
    classCombo.setSelectedIndex(playerClass);
  }
}

