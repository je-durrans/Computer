package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Transistor;

import static com.durrans.computer.gen1.Switch.POWER;
import static com.durrans.computer.gen1.Sink.SINK;

/**
 * Logical NOT gate. Circuit grounded if Transistor is not activated.
 */
public class NotGate extends Gate {

    private Component j;
    private Transistor t;

    {numberOfInputs=1;}

    public NotGate(Component...ins){
        this("", ins);
    }

    public NotGate(String name, Component...ins){
        initialise(name, ins);
    }

    public NotGate(Component i) {
        this("", i);
    }

    @Override
    public void evaluate() {
        if( j==null || t==null){
            throw new IllegalStateException("NotGate input has not been set");
        }
        t.evaluate();
        value.set(j.out());

    }

    @Override
    protected void setupInnerComponents() {
        j = new Component("junction", POWER);
        t = new Transistor(j, inputs.get(0));
        SINK.connectFrom(t);
        outComponent = j;
    }
}
