package com.durrans.computer;

class ConstantInput extends Component {

    public static final Component GROUND, LIVE;

    static {
        GROUND = new ConstantInput(OFF){{name = "Ground";}};
        LIVE = new ConstantInput(ON){{name = "Live";}};
    }

    private ConstantInput(boolean value){
        this.value.set(value);
        this.value.fix();
    }

}
