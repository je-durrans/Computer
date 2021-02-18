package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;

/**
 * Logical XOR gate. Compound AND-then-NOT.
 * Implemented as A XOR B = AND(OR(A,B), NAND(A,B)).
 */
public class XorGate extends Gate {

    private OrGate or;
    private NandGate nand;
    private AndGate and;

    {numberOfInputs=2;}

    public XorGate(Component...ins){
        this("", ins);
    }

    public XorGate(String name, Component...ins){
        initialise(name, ins);
    }

    protected void setupInnerComponents() {
        or = new OrGate(inputs.get(0), inputs.get(1));
        nand = new NandGate(inputs.get(0), inputs.get(1));
        and = new AndGate(or, nand);
        outComponent = and;
    }

    @Override
    public void evaluate() {
        if(outComponent==null){
            throw new IllegalStateException("NandGate inputs have not been set");
        }
        or.evaluate();
        nand.evaluate();
        and.evaluate();
        value.set(and.out());
    }

}

