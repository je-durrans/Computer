package com.durrans.computer;

public class Buffer extends Component {

    public Buffer(Component... ins) {
        this("", ins);
    }

    public Buffer(String name, Component...ins){
        this.name=name;
        for (Component i:ins){
            registerInput(i);
            i.registerOutput(this);
        }
        evaluate();
        checkGround();
    }

}
