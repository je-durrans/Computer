package com.durrans.computer.gen3;

import com.durrans.computer.gen1.BooleanTrigger;
import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen2.Gate;
import com.durrans.computer.gen2.OrGate;

public class FullAdder extends Gate {

    HalfAdder ha1, sum;
    private OrGate carry;


    private BooleanTrigger carryBit = new BooleanTrigger(()->{});
    {numberOfInputs=3;}

    public FullAdder(Component...ins){this("", ins);}

    public FullAdder(String name, Component...ins) {
        initialise(name, ins);
    }

    public boolean[] getBoth(){
        return new boolean[]{carry.out(), sum.out()};
    }

    @Override
    protected void setupInnerComponents() {
        ha1 = new HalfAdder(inputs.get(0), inputs.get(1));
        sum = new HalfAdder(ha1, inputs.get(2));
        carry = new OrGate(ha1.carry, sum.carry);
    }

    @Override
    public void evaluate(){
        ha1.evaluate();
        sum.evaluate();
        value.set(sum.out());
        carry.evaluate();
        carryBit.set(carry.out());
    }
}