package com.durrans.computer;

public class Junction extends Component {

    public Junction(Component... ins) {
        this("", ins);
    }

    public Junction(String name, Component...ins){
        super(name, ins);
    }

    @Override
    public void evaluate(){
        System.out.println("Junction evaluating");
        super.evaluate();
        System.out.println("Junction evaluated: "+out());

    }

}
