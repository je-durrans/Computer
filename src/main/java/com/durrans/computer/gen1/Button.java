package com.durrans.computer.gen1;

public class Button extends Component {

    public BooleanTrigger closed = new BooleanTrigger(this::evaluate);

    public Button(Component...ins) {this("", ins);}
    public Button(String name, Component...ins) {
        super(name, ins);
    }

    public void press() {
        value.set(true);
        value.set(false);
    }

    @Override
    public void evaluate(){
        super.evaluate();
        value.set(value.get()&&closed.get());
    }

}
