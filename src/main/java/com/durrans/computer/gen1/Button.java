package com.durrans.computer.gen1;

public class Button extends Component {


    public Button(String name) {
        super(name);
    }

    public void press() {
        value.set(true);
        value.set(false);
    }

}
