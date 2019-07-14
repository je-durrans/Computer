package com.durrans.computer;

public class Switch extends Component {

    BooleanTrigger closed = new BooleanTrigger(this::evaluate);

    public Switch(Component...ins){
        this("", ins);
    }

    public Switch(String name, Component...ins){
        super(name, ins);
    }

    public static final Switch POWER = new Power();

    public void press() {
        closed.set(!closed.get());
    }

    public void on() {
        closed.set(true);
    }

    public void off() {
        closed.set(false);
    }

    @Override
    public void evaluate(){
        super.evaluate();
        if (closed==null){ // this is stupid
            closed = new BooleanTrigger(this::evaluate);
        }
        value.set(value.get()&&closed.get());
    }

    private static class Power extends Switch{

        @Override
        public void evaluate(){
            value.set(!grounded.get()&&closed.get());
        }
    }

}
