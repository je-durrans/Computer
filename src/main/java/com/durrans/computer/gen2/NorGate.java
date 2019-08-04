package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;

public class NorGate extends Gate {

    private OrGate or;
    private NotGate not;

    {numberOfInputs=2;}

    public NorGate(Component...ins){
        this("", ins);
    }

    public NorGate(String name, Component...ins){
        initialise(name, ins);
    }

    protected void setupInnerComponents() {
        or = new OrGate(inputs.get(0), inputs.get(1));
        not = new NotGate(or);
        outComponent = not;
    }

    @Override
    public void evaluate() {
        if(or==null||not==null){
            throw new IllegalStateException("NandGate inputs have not been set");
        }
        or.evaluate();
        not.evaluate();
        value.set(not.out());
    }

}

