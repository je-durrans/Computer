package com.durrans.computer.gui;

import com.durrans.computer.gen1.Button;

import javax.swing.*;

public class GuiSwitch extends JButton {

    private final Button b;
    private final String s;
    private final String s2;

    public GuiSwitch(Button b, String...strings){
        this.b = b;
        b.attachGuiComponent(this);
        addActionListener((e) -> {
            b.press();
            GuiSwitch.this.updateText();
            GuiSwitch.this.updateUI();
        });
        this.s = strings.length>0?strings[0]:null;
        this.s2 = strings.length>1?strings[1]:null;
        updateText();
    }

    private void updateText() {
        if (b==null){return;}

        String closed = s2==null?s==null?b.closed.get()?"ON":"OFF":s:b.closed.get()?s2:s;

        setText(closed+" "+(b.out()?"1":"0"));
    }

    @Override
    public void updateUI(){
        super.updateUI();
        updateText();
        repaint();
    }
}
