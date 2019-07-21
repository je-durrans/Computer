package com.durrans.computer.gen1;

public class Switch extends Component {

    BooleanTrigger closed = new BooleanTrigger(this::evaluate);

    public Switch(Component...ins){
        this("", ins);
    }

    public Switch(String name, Component...ins){
        initialise(name, ins);
        evaluate();
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
        value.set(value.get()&&closed.get());
        System.out.println(name+" evaluating to "+value.get());
    }

    private static class Power extends Switch{

        @Override
        public void evaluate(){
            value.set(closed.get());
        }
    }

}
