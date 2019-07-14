package com.durrans.computer;

import static com.durrans.computer.Switch.POWER;

public class OrGate extends Component {

    private Transistor t1, t2;
    private Component linker;

    {numberOfInputs=2;}

    public OrGate(Component...ins) {
        this("", ins);
    }

    public OrGate(String name, Component...ins) {
        initialise(name, ins);
        t1 = new Transistor(POWER, inputs.get(0));
        t2 = new Transistor(POWER, inputs.get(1));
        linker = new Component(t1, t2);
        evaluate();
    }

    @Override
    public void evaluate(){
        if(t1==null||t2==null||linker==null){return;}
        t1.evaluate();
        t2.evaluate();
        value.set(linker.out());
    }

    @Override
    public void registerOutput(Component o){
        linker.registerOutput(o);
    }

}
