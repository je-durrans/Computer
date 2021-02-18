package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Button;
import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Switch;
import com.durrans.computer.gen2.AndGate;
import com.durrans.computer.gen2.OrGate;
import com.durrans.computer.gen2.XorGate;
import com.durrans.computer.gen3.Bit;
import com.durrans.computer.gui.Gui;
import com.durrans.computer.gui.GuiLabel;
import com.durrans.computer.gui.GuiMultiplexer;
import com.durrans.computer.gui.GuiSwitch;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.durrans.computer.gen1.Switch.POWER;

/**
 * Arithmetic Logic Unit
 * Performs Add/Subtract/Logical-AND/Logical-OR operations on two byte buffer inputs.
 * Switches type (math/logic) and operation (add/sub or and/or) define the operation to be performed
 * 00 ADD
 * 01 SUB
 * 10 AND
 * 11 OR
 * Current implementation contains the setup of the required inputs.
 */
public class ALU extends Component{
    private static final int SIZE = 8;

    private static Switch type;
    private static Switch operation;

    private static MultiComponent<Bit> buffer1, buffer2;
    private static MultiComponent<XorGate> flipper;
    private static NBitAdder adder;
    private static MultiComponent<AndGate> andGates;
    private static MultiComponent<OrGate> orGates;
    private static MultiComponent<Multiplexer> logic, out;

//    private ALU(Component...ins){
//        this("", ins);
//    }

    private ALU(String name, Component...ins) {
        super(name, ins);
        initialise(name, ins);
    }

    public static void main(String[] args) {

        MultiComponent<Switch> inputs = new MultiComponent<>(Switch.class, SIZE);
        inputs.connectFrom(POWER);

        Gui gui = new Gui();

        Button set1 = new Button(POWER);
        Button set2 = new Button(POWER);


        buffer1 = new MultiComponent<>(Bit.class, SIZE);
        buffer1.connectFrom(inputs);
        buffer1.connectFrom(set1);

        buffer2 = new MultiComponent<>(Bit.class, SIZE);
        buffer2.connectFrom(inputs);
        buffer2.connectFrom(set2);

        operation = new Switch(POWER);
        type = new Switch(POWER);


        flipper = new MultiComponent<>(XorGate.class, SIZE);
        flipper.connectFrom(operation);
        flipper.connectFrom(buffer2);

        adder = new NBitAdder(buffer1, flipper, operation);

        andGates = new MultiComponent<>(AndGate.class, SIZE);
        andGates.connectFrom(buffer1);
        andGates.connectFrom(buffer2);

        orGates = new MultiComponent<>(OrGate.class, SIZE);
        orGates.connectFrom(buffer1);
        orGates.connectFrom(buffer2);

        logic = new MultiComponent<>(Multiplexer.class, SIZE);
        logic.connectFrom(andGates);
        logic.connectFrom(orGates);
        logic.connectFrom(operation);

        out = new MultiComponent<>(Multiplexer.class, SIZE);
        out.connectFrom(adder);
        out.connectFrom(logic);
        out.connectFrom(type);


        createSwitches(gui, 0, POWER.toMComponent());
        createSwitches(gui, 1, inputs);
        createLabels(gui, 2, 0, inputs);
        createButton(gui, 3, 0, set1, "Set1");
        createButton(gui, 3, 1, set2, "Set2");
        createLabels(gui, 4, 0, buffer1);
        createLabels(gui, 4, 1, buffer2);
        createButton(gui, 5, 0, operation, "Add/And", "Sub/Or");
        createButton(gui, 5, 1, type, "Math", "Logic");
        createLabels(gui, 6,1, flipper);
        createLabels(gui, 6,0, adder);
        createLabels(gui, 7,0, andGates);
        createLabels(gui, 7,1, orGates);
        createLabels(gui, 8,0, logic);
        createLabels(gui, 9,0, out);

        JPanel panel = new JPanel();
        for (Multiplexer m: logic){
            panel.add(new GuiMultiplexer(m));
        }

        JPanel panel2 = new JPanel();
        for (Multiplexer m: out){
            panel2.add(new GuiMultiplexer(m));
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;c.gridy=10;c.gridwidth=2;
        gui.add(panel, c);
        c.gridy=11;
        gui.add(panel2, c);

        gui.pack();
        gui.repaint();
        System.out.println(logic.size());
    }

    private static void printSwitches(){
        System.out.println(type.out()?"1 "+(operation.out()?"1 OR":"0 AND"):"0 "+(operation.out()?"1 -":"0 +"));
    }

    private static void printComponentOuts(){
        MultiComponent<AndGate> a = new MultiComponent<>();
        MultiComponent<AndGate> b = new MultiComponent<>();
        for (Multiplexer m:out){
            a.add(m.and1);
            b.add(m.and2);
        }
        Map<String, MultiComponent> components = new HashMap<>();
        components.put("buffer1",buffer1);
        components.put("buffer2", buffer2);
        components.put("flipper",flipper);
        components.put("adder  ",adder);
        components.put("ands   ",andGates);
        components.put("ors    ",orGates);
        components.put("logic  ",logic);
        components.put("and1   ",a);
        components.put("and2   ",b);
        components.put("out    ",out);
        //MComponent[] components = {buffer1, buffer2, flipper, adder, andGates, orGates, logic, a, b, out};
        for (Map.Entry<String, MultiComponent> e:components.entrySet()){
            String s = e.getKey();
            MultiComponent c = e.getValue();
            System.out.print(s+" : ");
            String bin = Arrays.toString(c.out()).replace("true", "1").replace("false","0").replace(", ","");
            bin = bin.substring(1, bin.length()-1);
            int dec = Integer.parseInt(bin, 2);
            int msb = (int)Math.pow(2, SIZE-1);
            if (dec > msb) dec -= 2*msb;
            System.out.println(bin+" "+dec);
        }

        System.out.println();
    }

    private static void createButton(Gui gui, int yPos, int xPos, Button comp, String...strings){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=xPos;
        c.gridy=yPos;
        GuiSwitch gs = new GuiSwitch(comp, strings);
        gui.add(gs, c);
    }

    private static void createSwitches(Gui gui, int yPos, MultiComponent<? extends Switch> mc, String...strings){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=yPos;
        for (Switch s : mc) {
            GuiSwitch gs = new GuiSwitch(s, strings);
            gui.add(gs, c);
            c.gridx++;
        }
    }

    private static void createLabels(Gui gui, int yPos, int xPos, MultiComponent<? extends Component> mc){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=xPos;
        c.gridy=yPos;
        JPanel panel = new JPanel();
        for (Component comp : mc) {
            GuiLabel gl = new GuiLabel(comp);
            panel.add(gl);
        }
        gui.add(panel, c);
    }

}
