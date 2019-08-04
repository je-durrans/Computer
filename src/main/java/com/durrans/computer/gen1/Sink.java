package com.durrans.computer.gen1;

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
    public void registerOutput(Component o){}

}