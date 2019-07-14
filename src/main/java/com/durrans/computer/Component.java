package com.durrans.computer;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {

    static final boolean OFF=false, ON=true;

    protected BooleanTrigger value = new BooleanTrigger(this::onValueChange);
    protected BooleanTrigger grounded = new BooleanTrigger(this::onGroundedChange);

    protected List<Component> inputs = new ArrayList<>();
    protected List<Component> outputs = new ArrayList<>();

    String name;

    Component(Component...ins){ this("", ins); }
    Component(String name, Component...ins){
        this.name = name;
        for (Component i:ins){
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
        inputs.add(i);
        i.registerOutput(this);
        evaluate();
    }

    void registerOutput(Component o){
        outputs.add(o);
        evaluate();
    }

    void onValueChange(){
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
