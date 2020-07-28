package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Component;

import javax.swing.*;

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
    public void registerInput(Component i) {
        super.registerInput(i);
        if (inputs.size() == numberOfInputs) {
            setupInnerComponents();
            registerPendingOutputs();
            evaluate();
        }
    }

    private void registerPendingOutputs() {
        for (Component o:outputs){
            outComponent.registerOutput(o);
        }
    }

    @Override
    public void registerOutput(Component o) {
        try {
            outComponent.registerOutput(o);
        } catch (NullPointerException e) {
            super.registerOutput(o);
        }
    }

    @Override
    public void registerGuiComponent(JComponent j){
        outComponent.registerGuiComponent(j);
    }

    @Override
    public void onValueChange(){
        try {
            outComponent.onValueChange();
        } catch (NullPointerException ignored) {}
    }
}
