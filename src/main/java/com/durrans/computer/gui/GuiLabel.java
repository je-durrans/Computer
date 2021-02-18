package com.durrans.computer.gui;

import com.durrans.computer.gen1.Component;

import javax.swing.*;

public class GuiLabel extends JLabel {

    private final Component component;

    public GuiLabel(Component c){
        component = c;
        component.attachGuiComponent(this);
        updateText();
    }

    private void updateText() {
        if (component!=null) {
            setText(component.out() ? "1" : "0");
        }
    }

    @Override
    public void updateUI(){
        System.out.println("called updateUI on GuiLabel");
        super.updateUI();
        updateText();
        repaint();
    }
}
