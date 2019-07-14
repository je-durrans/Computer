package com.durrans.computer;

import static com.durrans.computer.Switch.POWER;

public class OrGate extends Component {

    private Transistor t1, t2;
    private Component linker;

    public OrGate(Component in1, Component in2) {
        this("", in1, in2);
    }

    public OrGate(String name, Component in1, Component in2) {
        super(name, in1, in2);
        t1 = new Transistor(POWER, in1);
        t2 = new Transistor(POWER, in2);
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
