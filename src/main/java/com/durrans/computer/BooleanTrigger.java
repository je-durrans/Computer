package com.durrans.computer;

import static com.durrans.computer.Config.print;

public class BooleanTrigger {

    private boolean value=false;
    private String name;
    private Runnable task;
    private Component owner;
    private boolean fixed = false;


    public BooleanTrigger(String name, Runnable r, Component c){
        this.name = name;
        task = r;
        owner = c;
    }

    public void set(boolean b) {
        if (fixed){
            return;
        }
        boolean prev = value;
        value = b;
        if (prev != value){
            print("Setting "+owner.name+"'s "+name+" to "+value);
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
