package com.durrans.computer;

public class Switch extends Component {

    public Switch(){
        this("");
    }

    public Switch(String name){
        super(name);
    }

    public static final Switch POWER = new Switch("POWER");

    public void press() {
        value.set(!value.get());
    }

    public void on() {
        value.set(ON);
    }

    public void off() {
        value.set(OFF);
    }

    @Override
    public void checkGround() {}
}
