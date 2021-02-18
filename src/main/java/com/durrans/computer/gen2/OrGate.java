package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Transistor;

import static com.durrans.computer.gen1.Switch.POWER;

/**
 * Logical OR gate. Transistors in parallel.
 */
public class OrGate extends Gate {

    private Transistor t1, t2;
    private Component linker;

    {numberOfInputs=2;}

    public OrGate(Component...ins) {
        this("", ins);
    }

    public OrGate(String name, Component...ins) {
        initialise(name, ins);
    }

    @Override
    public void evaluate(){
        if(t1==null||t2==null||linker==null){
            throw new IllegalStateException("OrGate inputs have not been set");
        }
        t1.evaluate();
        t2.evaluate();
        value.set(linker.out());
    }

    @Override
    protected void setupInnerComponents() {
        t1 = new Transistor(POWER, inputs.get(0));
        t2 = new Transistor(POWER, inputs.get(1));
        linker = new Component(t1, t2);
        outComponent = linker;
    }
}
