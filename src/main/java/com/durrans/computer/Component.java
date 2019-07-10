package com.durrans.computer;

import java.util.ArrayList;
import java.util.List;

import static com.durrans.computer.Config.print;

public abstract class Component {

    public static final boolean OFF=false, ON=true;

    protected BooleanTrigger value = new BooleanTrigger("value", this::onValueChange, this);
    protected BooleanTrigger grounded = new BooleanTrigger("grounded", this::onGroundedChange, this);

    private List<Component> inputs = new ArrayList<>();
    private List<Component> outputs = new ArrayList<>();

    String name;

    Component(){ this(""); }
    Component(String name){ this.name = name; }

    public final boolean out() {
        return value.get();
    }

    public final boolean isGrounded(){
        return grounded.get();
    }

    public void registerInput(Component i){
        inputs.add(i);
        evaluate();
    }

    public void registerOutput(Component o){
        outputs.add(o);
        checkGround();
    }

    private void onValueChange(){
        for(Component o : outputs){
            o.evaluate();
        }
    }

    private void onGroundedChange(){
        for(Component i : inputs){
            i.checkGround();
        }
    }

    public void checkGround(){
        for(Component o: outputs){
            if (o.isGrounded()){
                grounded.set(true);
                value.set(OFF);
                return;
            }
        }
        grounded.set(false);
        evaluate();
    }

    public void evaluate(){
        if (grounded.get()) {
            value.set(OFF);
            return;
        }
        for(Component i: inputs){
            if (i.out()){
                value.set(ON);
                return;
            }
        }
        value.set(OFF);
    }

}
