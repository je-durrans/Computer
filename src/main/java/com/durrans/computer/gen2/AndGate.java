package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Transistor;

import static com.durrans.computer.gen1.Switch.POWER;

/**
 * Logical AND gate. Transistors in series.
 */
public class AndGate extends Gate {

    private Transistor t1, t2;

    {numberOfInputs=2;}

    public AndGate(Component...ins){
        this("", ins);
    }

    public AndGate(String name, Component...ins){
        initialise(name, ins);
    }

    protected void setupInnerComponents() {
        t1 = new Transistor(POWER, inputs.get(0));
        t2 = new Transistor(t1, inputs.get(1));
        outComponent = t2;
    }

    @Override
    public void evaluate() {
        if(t1==null||t2==null){
            throw new IllegalStateException("AndGate inputs have not been set");
        }
        t1.evaluate();
        t2.evaluate();
        value.set(t2.out());
    }

}
