package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;

import javax.swing.*;

/**
 * Base logic gate class.
 * Allows partial setting of inputs.
 * Delays internal implementation until sufficiently many inputs are provided.
 */
public abstract class Gate extends Component {

    protected Component outComponent;

    public Gate(Component...ins){
        this("", ins);
    }
    public Gate(String name, Component...ins){
        super(name, ins);
    }

    abstract protected void setupInnerComponents();

    @Override
    public void connectFrom(Component i) {
        super.connectFrom(i);
        if (inputs.size() == numberOfInputs) {
            setupInnerComponents();
            registerPendingOutputs();
            evaluate();
        }
    }

    private void registerPendingOutputs() {
        for (Component o:outputs){
            outComponent.connectTo(o);
        }
    }

    @Override
    public void connectTo(Component o) {
        try {
            outComponent.connectTo(o);
        } catch (NullPointerException e) {
            super.connectTo(o);
        }
    }

    @Override
    public void attachGuiComponent(JComponent j){
        try {
            outComponent.attachGuiComponent(j);
        } catch (NullPointerException e) {
            super.attachGuiComponent(j);
        }
    }

    @Override
    public void onValueChange(){
        try {
            outComponent.onValueChange();
        } catch (NullPointerException ignored) {}
    }
}
