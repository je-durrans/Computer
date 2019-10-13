package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen2.AndGate;
import com.durrans.computer.gen2.Gate;
import com.durrans.computer.gen2.NorGate;
import com.durrans.computer.gen2.NotGate;

public class Bit extends Gate {

    private Component b;
    private NorGate nor1, nor2;

    {numberOfInputs=2;}

    @Override
    public void evaluate() {
        nor1.evaluate();
        nor2.evaluate();
        value.set(b.out());
    }

    public Bit(Component...ins){
        this("", ins);
    }

    public Bit(Component value, Component set) {
        this("", value, set);
    }

    public Bit(String name, Component...ins) {
        initialise(name, ins);
    }

    @Override
    protected void setupInnerComponents() {
        // Set them up in this order so that nor1 is fully loaded first
        // This makes it default to off
        nor2 = new NorGate("Nor2", new AndGate(new NotGate(inputs.get(0)), inputs.get(1)));
        nor1 = new NorGate("Nor1", new AndGate(inputs.get(0), inputs.get(1)), nor2);
        nor2.registerInput(nor1);
        b = new Component(nor2);
        outComponent = b;
    }

}
