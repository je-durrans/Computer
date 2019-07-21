package com.durrans.computer.gen1;

import com.durrans.computer.gen1.Component;

public class Transistor extends Component {

    private Component collector;
    private Component base;

    {numberOfInputs=2;}

    public Transistor(Component...ins) {
        this("", ins);
    }

    public Transistor(String name, Component...ins){
        if(ins.length!=2){
            throw new IllegalArgumentException("Transistor must be instantiated with exactly two inputs");
        }
        initialise(name, ins);
        collector = inputs.get(0);
        base = inputs.get(1);
        evaluate();
    }

    @Override
    public void evaluate(){
        if (base==null){ return; }
        boolean g = false;
        base.evaluate();
        for(Component o: outputs){
            if (o.isGrounded()){
                g = true;
            }
        }
        grounded.set(g&&base.out());
        value.set(collector.out()&&base.out());
    }

    private static class BaseContact extends Component{

        BaseContact(Component base) {
            grounded.fix();
            registerInput(base);
        }
    }

    @Override
    protected void initialise(String name, Component...ins) {
        super.initialise(name, ins[0], new BaseContact(ins[1]));
    }

}
