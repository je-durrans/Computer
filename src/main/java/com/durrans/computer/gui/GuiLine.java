package com.durrans.computer.gui;

import com.durrans.computer.gen1.Component;

import javax.swing.*;

import static com.durrans.computer.gui.Images.*;

public class GuiLine extends JLabel {

    Component comp;

    public GuiLine(Component component) {
        comp=component;
        update();
    }

    public void update() {
        if(comp.out()){
            setIcon(active);
        } else {
            setIcon(inactive);
        }
    }
}
