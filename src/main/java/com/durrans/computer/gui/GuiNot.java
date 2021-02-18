package com.durrans.computer.gui;

import com.durrans.computer.gen2.NotGate;

import javax.swing.*;

import java.awt.*;

import static com.durrans.computer.gen1.Switch.POWER;

public class GuiNot extends JPanel implements GuiComponent {

    NotGate not;
    JLabel label = new JLabel();
    GuiLine in, out;

    public GuiNot(NotGate not){
        this.not = not;
        in = new GuiLine(not.inputs.get(0));
        out = new GuiLine(not);
        add(in);
        add(label);
//        label.setIcon(Images.not);
        add(out);
        setBackground(Color.RED);
    }

    public void update(){
        label.setText(not.inputs.get(0).out() + "N" + not.out());
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.add(new GuiSwitch(POWER));
        NotGate not1 = new NotGate(POWER);
        NotGate not2 = new NotGate(not1);
        NotGate not3 = new NotGate(not2);
        gui.add(new GuiNot(not1));
        gui.add(new GuiNot(not2));
        gui.add(new GuiNot(not3));
    }

}
