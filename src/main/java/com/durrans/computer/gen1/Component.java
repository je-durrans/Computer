package com.durrans.computer.gen1;

import com.durrans.computer.gen4.MultiComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Base Component class.
 * Stores inputs, outputs, value of output, and whether it is electrically grounded.
 * May optionally be given a name, and/or be linked to a Gui representation.
 */
public class Component {

    static final boolean OFF=false, ON=true;
    protected JComponent j;

    protected BooleanTrigger value = new BooleanTrigger(this::onValueChange);
    protected BooleanTrigger grounded = new BooleanTrigger(this::onGroundedChange);

    public List<Component> inputs = new ArrayList<>();
    protected List<Component> outputs = new ArrayList<>();

    protected int numberOfInputs = Integer.MAX_VALUE;

    protected String name;

    public Component(Component...ins){
        this("", ins);
    }

    public Component(String name, Component...ins){
        //all.add(this);
        if (this.getClass() == Component.class) {
            initialise(name.equals("")?"Component":name, ins);
            evaluate();
        }
//        new Thread(() -> {
//            while (true){
//                try{
//                    Component.this.evaluate();
//                    Thread.sleep(500);
//                } catch (Exception ignored){
//                }
//            }
//        }).start();
    }

    protected void initialise(String name, Component...ins) {
        this.name = name;
        for (Component i : ins) {
            connectFrom(i);
        }
    }

    public final boolean out() {
        return value.get()&&!grounded.get();
    }

    public final boolean isGrounded(){
        return grounded.get();
    }

    public void connectFrom(Component i) {
        if (inputs.size()>=this.numberOfInputs){
            String className = getClass().toString().replaceAll(".+\\.", "");
            throw new IllegalArgumentException("Too many inputs for component of type "+className);
        }
        inputs.add(i);
        i.connectTo(this);
    }

    public void connectTo(Component o){
        outputs.add(o);
    }

    public void attachGuiComponent(JComponent j){
        this.j = j;
        j.updateUI();
    }

    public void onValueChange(){
        for(Component o : outputs){
            o.evaluate();
        }
        if (j!=null){
            j.updateUI();
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

    public MultiComponent toMComponent(){
        MultiComponent m = new MultiComponent<>();
        m.add(this);
        return m;
    }

}
