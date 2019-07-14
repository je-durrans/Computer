package com.durrans.computer;

public class BooleanTrigger {

    private boolean value=false;
    private Runnable task;
    private boolean fixed = false;


    public BooleanTrigger(Runnable r){
        task = r;
    }

    public void set(boolean b) {
        if (fixed){
            return;
        }
        boolean prev = value;
        value = b;
        if (prev != value){
            task.run();
        }
    }

    public boolean get(){
        return value;
    }

    public void fix() {
        this.fixed=true;
    }
}
