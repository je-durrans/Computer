package com.durrans.computer.gen1;

import java.util.ArrayList;
import java.util.List;

public class Component {

    static final boolean OFF=false, ON=true;

    protected BooleanTrigger value = new BooleanTrigger(this::onValueChange);
    protected BooleanTrigger grounded = new BooleanTrigger(this::onGroundedChange);

    protected List<Component> inputs = new ArrayList<>();
    protected List<Component> outputs = new ArrayList<>();

    protected int numberOfInputs = Integer.MAX_VALUE;

    protected String name;

    public Component(Component...ins){
        this("", ins);
    }

    public Component(String name, Component...ins){
        if (this.getClass() == Component.class) {
            initialise(name, ins);
            evaluate();
        }
    }

    protected void initialise(String name, Component...ins) {
        this.name = name;
        for (Component i : ins) {
            registerInput(i);
        }
    }

    public final boolean out() {
        return value.get()&&!grounded.get();
    }

    public final boolean isGrounded(){
        return grounded.get();
    }

    public void registerInput(Component i) {
        if (inputs.size()>=this.numberOfInputs){
            String className = getClass().toString().replaceAll(".+\\.", "");
            throw new IllegalArgumentException("Too many inputs for component of type "+className);
        }
        inputs.add(i);
        i.registerOutput(this);
    }

    public void registerOutput(Component o){
        outputs.add(o);
    }

    public void onValueChange(){
        for(Component o : outputs){
            o.evaluate();
        }
    }

    private void onGroundedChange(){
        for(Component i : inputs){
            i.evaluate();
        }
    }

    public void evaluate(){

        boolean g = false;
        for(Component o: outputs){
            if (o.isGrounded()){
                g = true;
            }
        }
        grounded.set(g);

        boolean v = OFF;
        for(Component i: inputs){
            if (i.out()){
                v = ON;
            }
        }
        value.set(v);
    }

    public void clear(){
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        evaluate();
    }

}
