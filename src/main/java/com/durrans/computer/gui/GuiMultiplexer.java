package com.durrans.computer.gui;

import com.durrans.computer.gen4.Multiplexer;

import javax.swing.*;

public class GuiMultiplexer extends JPanel {

//    private final Multiplexer m;
    private GuiLabel in1, in2, toggle;

    public GuiMultiplexer(Multiplexer m){
//        this.m = m;
        m.attachGuiComponent(this);
        in1 = new GuiLabel(m.inputs.get(0)); add(in1);
        in2 = new GuiLabel(m.inputs.get(1)); add(in2);
        toggle = new GuiLabel(m.inputs.get(2)); add(toggle);
        updateUI();
    }

    @Override
    public void updateUI(){
        super.updateUI();
        if (toggle!=null) {
            in1.updateUI();
            in2.updateUI();
            toggle.updateUI();
        }
        repaint();
    }
}
