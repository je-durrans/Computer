package com.durrans.computer;

import static com.durrans.computer.Switch.POWER;
import static com.durrans.computer.Sink.SINK;

public class NotGate extends Component {

    private Junction j;
    private Transistor t;

    public NotGate(String name, Component input){
        super(name, input);
        j = new Junction("junction", POWER);
        t = new Transistor(j, input);

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
