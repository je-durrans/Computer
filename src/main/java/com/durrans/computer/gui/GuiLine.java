package com.durrans.computer.gui;

import com.durrans.computer.gen1.Component;

import javax.swing.*;

//import static com.durrans.computer.gui.Images.*;

public class GuiLine extends JLabel {

    Component component;

    public GuiLine(Component component) {
        this.component =component;
        update();
    }

    public void update() {
        if(component.out()){
//            setIcon(active);
        } else {
//            setIcon(inactive);
        }
    }
}
