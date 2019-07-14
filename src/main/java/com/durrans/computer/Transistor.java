package com.durrans.computer;

public class Transistor extends Component {

    Component collector;
    Component base;

    public Transistor(Component collector, Component base) {
        this(collector, base, "");
    }

    public Transistor(Component collector, Component base, String name){

        super(name, collector);
        this.collector = collector;
        this.base = new BaseContact(base);

        registerInput(collector);
        registerInput(this.base);

        evaluate();
    }

    @Override
    public void evaluate(){
        System.out.println("Transistor evaluating");
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
        System.out.println("Transistor evaluated: "+out()+" because "+collector.out()+base.out()+grounded.get());
    }

    private class BaseContact extends Component{

        BaseContact(Component base) {
            grounded.fix();
            registerInput(base);
        }

        @Override
        public void evaluate(){
            System.out.println("BaseConnector evaluating");
            super.evaluate();
            System.out.println("BaseConnector evaluated "+out());
        }
    }


}
