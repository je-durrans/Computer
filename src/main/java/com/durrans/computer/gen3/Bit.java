package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen2.AndGate;
import com.durrans.computer.gen2.Gate;
import com.durrans.computer.gen2.NorGate;
import com.durrans.computer.gen2.NotGate;

public class Bit extends Gate {

    private NorGate nor1, nor2;

    {numberOfInputs=2;}

    @Override
    public void evaluate() {
        if (!inputs.get(0).out()){
            nor1.evaluate();
            nor2.evaluate();
        } else {
            nor2.evaluate();
            nor1.evaluate();
        }
        value.set(nor2.out());
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
    public void onValueChange(){
        super.onValueChange();
        System.out.println("called bit.onValueChange");
    }

    @Override
    protected void setupInnerComponents() {
        // Set them up in this order so that nor1 is fully loaded first
        // This makes it default to off

        Component reset = new AndGate(new NotGate(inputs.get(0)), inputs.get(1));
        Component set = new AndGate(inputs.get(0), inputs.get(1));

        nor2 = new NorGate("Nor2", reset);
        nor1 = new NorGate("Nor1", set, nor2);
        nor2.registerInput(nor1);

        outComponent = nor2;
    }

}
