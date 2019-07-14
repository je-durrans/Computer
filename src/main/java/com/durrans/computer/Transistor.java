package com.durrans.computer;

public class Transistor extends Component {

    private Component collector;
    private Component base;

    {numberOfInputs=2;}

    public Transistor(Component...ins) {
        this("", ins);
    }

    public Transistor(String name, Component...ins){
        if(ins.length!=2){
            throw new IllegalArgumentException("Transistor must be instantiated with exactly two inputs");
        }

        this.collector = ins[0];
        this.base = new BaseContact(ins[1]);
        initialise(name, collector, this.base);
        evaluate();
    }

    @Override
    public void evaluate(){
        if (base==null){ return; }
        boolean g = false;
        base.evaluate();
        for(Component o: outputs){
            if (o.isGrounded()){
                g = true;
            }
        }
        grounded.set(g&&base.out());
        value.set(collector.out()&&base.out());
    }

    private class BaseContact extends Component{

        BaseContact(Component base) {
            grounded.fix();
            registerInput(base);
        }
    }


}


/*package com.durrans.computer;

public class Transistor extends Component {

    private Component collector;
    private Component base;


    public Transistor(Component...ins) {
        this("", ins);
    }

    public Transistor(String name, Component...ins){
        if (ins.length!=2){
            throw new IllegalArgumentException("Transistor must be instantiated with exactly two component inputs");
        }
        initialise(name, collector);
        this.collector = collector;
        this.base = new BaseContact(base);

        registerInput(collector);
        registerInput(this.base);

        evaluate();
    }

    @Override
    public void evaluate(){
        if (base==null){ return; }
        boolean g = false;
        base.evaluate();
        for(Component o: outputs){
            if (o.isGrounded()){
                g = true;
            }
        }
        grounded.set(g&&base.out());
        value.set(collector.out()&&base.out());
    }

    private class BaseContact extends Component{

        BaseContact(Component base) {
            grounded.fix();
            registerInput(base);
        }
    }


}
*/
