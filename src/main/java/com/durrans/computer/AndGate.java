package com.durrans.computer;

import static com.durrans.computer.Switch.POWER;

public class AndGate extends Component {

    private Transistor t1, t2;

    public AndGate(Component in1, Component in2){
        this("", in1, in2);
    }

    public AndGate(String name, Component in1, Component in2){
        super(name, in1, in2);
        t1 = new Transistor(POWER, in1);
        t2 = new Transistor(t1, in2);
        evaluate();
    }

    @Override
    public void evaluate() {
        if(t1==null||t2==null){return;}
        t1.evaluate();
        t2.evaluate();
        value.set(t2.out());
    }

    @Override
    public void registerOutput(Component o) {
        t2.registerOutput(o);
    }
}
