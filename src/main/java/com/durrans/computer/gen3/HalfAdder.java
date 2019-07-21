package com.durrans.computer.gen3;

import com.durrans.computer.gen1.BooleanTrigger;
import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen2.AndGate;
import com.durrans.computer.gen2.Gate;
import com.durrans.computer.gen2.XorGate;

public class HalfAdder extends Gate {

    private BooleanTrigger carryBit = new BooleanTrigger(()->{});
    private XorGate sum;
    AndGate carry;
    {numberOfInputs=2;}


    public HalfAdder(Component...ins) {
        this("", ins);
    }

    public HalfAdder(String name, Component...ins) {
        initialise(name, ins);
    }

    @Override
    protected void setupInnerComponents(){
        sum = new XorGate(inputs.get(0), inputs.get(1));
        carry = new AndGate(inputs.get(0), inputs.get(1));
    }

    @Override
    public void evaluate(){
        sum.evaluate();
        value.set(sum.out());
        carry.evaluate();
        carryBit.set(carry.out());
    }

    public boolean getCarry() {
        return carry.out();
    }
}
