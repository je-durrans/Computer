package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen2.AndGate;
import com.durrans.computer.gen2.Gate;
import com.durrans.computer.gen2.NotGate;
import com.durrans.computer.gen2.OrGate;

public class Multiplexer extends Gate {

    static int number = 0;
    {numberOfInputs=3;}
    Component in1;
    Component in2;
    Component toggle;
    AndGate and1, and2;
    OrGate or;
    int n;

    public Multiplexer(Component...ins){
        this("", ins);
    }

    public Multiplexer(String name, Component[] ins) {
        super(name, ins);
        initialise(name+"Multiplexer", ins);
        n=number++;
    }

    @Override
    protected void setupInnerComponents() {
        in1 = inputs.get(0);
        in2 = inputs.get(1);
        toggle = inputs.get(2);
        and1 = new AndGate(in1, new NotGate(toggle));
        and2 = new AndGate(in2, toggle);
        or = new OrGate(and1, and2);
        outComponent = or;
    }

    @Override
    public void evaluate() {
        and1.evaluate();
        and2.evaluate();
        or.evaluate();
        value.set(outComponent.out());
        //System.out.println(n+" "+in1.out()+" "+in2.out()+" "+and1.out()+" "+and2.out()+" "+or.out());
    }

}