package com.durrans.computer.gen1;

/**
 * Stores the current state (value) and triggers an event when changed.
 * Value may be fixed, preventing change.
 */

public class BooleanTrigger {

    private boolean value=false;
    private boolean fixed = false;
    private Runnable onChange;


    public BooleanTrigger(Runnable r){
        onChange = r;
    }

    public void set(boolean b) {
        if (fixed){
            return;
        }
        boolean prev = value;
        value = b;
        if (prev != value){
            onChange.run();
        }
    }

    public boolean get(){
        return value;
    }

    public void fix() {
        this.fixed=true;
    }
}
