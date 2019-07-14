package com.durrans.computer;

import static com.durrans.computer.Switch.POWER;
import static com.durrans.computer.Sink.SINK;

public class NotGate extends Component {

    private Component j;
    private Transistor t;

    {numberOfInputs=1;}

    public NotGate(Component...ins){
        this("", ins);
    }

    public NotGate(String name, Component...ins){
        initialise(name, ins);

        j = new Component("junction", POWER);
        t = new Transistor(j, inputs.get(0));

        SINK.registerInput(t);

        evaluate();
    }

    public NotGate(Component i) {
        this("", i);
    }

    @Override
    public void evaluate() {
        if( j==null || t==null){return;}
        t.evaluate();
        value.set(j.out());

    }

}
