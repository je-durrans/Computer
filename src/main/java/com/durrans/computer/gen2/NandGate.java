package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;

public class NandGate extends Gate {

    private AndGate and;
    private NotGate not;

    {numberOfInputs=2;}

    public NandGate(Component...ins){
        this("", ins);
    }

    public NandGate(String name, Component...ins){
        initialise(name, ins);
    }

    protected void setupInnerComponents() {
        and = new AndGate(inputs.get(0), inputs.get(1));
        not = new NotGate(and);
        outComponent = not;
    }

    @Override
    public void evaluate() {
        if(and==null||not==null){
            throw new IllegalStateException("NandGate inputs have not been set");
        }
        and.evaluate();
        not.evaluate();
        value.set(not.out());
    }

}

