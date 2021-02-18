package com.durrans.computer.gen1;

/**
 * Simple switch implementation, stays open/closed.
 */
public class Switch extends Button {

    public Switch(Component...ins){
        this("", ins);
    }

    public Switch(String name, Component...ins){
        initialise(name, ins);
        evaluate();
    }

    public void press() {
        closed.set(!closed.get());
    }

    public void on() {
        closed.set(true);
    }

    public void off() {
        closed.set(false);
    }

    public static final Switch POWER = new Switch(){

        @Override
        public void evaluate(){
            value.set(closed.get());
        }
    };

}
