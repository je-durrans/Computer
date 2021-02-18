package com.durrans.computer.gen1;

/**
 * Connecting Sink as an output electrically grounds another component.
 */
public final class Sink extends Component {

    public final static Sink SINK;

    static {
        SINK = new Sink();
    }

    private Sink(){
        name="Sink";
        value.set(OFF);
        value.fix();
        grounded.set(true);
        grounded.fix();
    }

    @Override
    public void connectTo(Component o){}

}