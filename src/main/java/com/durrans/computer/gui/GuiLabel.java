package com.durrans.computer.gui;

import com.durrans.computer.gen1.Component;

import javax.swing.*;

public class GuiLabel extends JLabel {

    private final Component s;

    public GuiLabel(Component s){
        this.s = s;
        s.registerGuiComponent(this);
        updateText();
    }

    private void updateText() {if (s==null){
        System.out.println("it's null");return;}
        System.out.println("updating label");
        setText(s.out()?"1":"0");
    }

    @Override
    public void updateUI(){
        System.out.println("called updateUI on GuiLabel");
        super.updateUI();
        updateText();
        repaint();
    }
}
