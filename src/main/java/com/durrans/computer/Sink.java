package com.durrans.computer;

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

}