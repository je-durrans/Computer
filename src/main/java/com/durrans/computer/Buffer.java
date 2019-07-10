package com.durrans.computer;

import sun.jvm.hotspot.debugger.win32.coff.COMDATSelectionTypes;

import static com.durrans.computer.Config.print;

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

//    public Buffer(String name, In ... ins){
//        super(name);
//        print(name+"creating buffer");
//        for(In i:ins){
//            print(name+"adding input");
//            output.registerInput(i);
//            i.registerOutput(output);
//        }
//        evaluate();
//        print(name+"created and evaluated buffer with value "+value);
//    }
//
//    @Override
//    public void checkGround() {
//        for (Out o:input.outputs){
//            if (o.isGrounded()){
//                handleGroundedChange(()->output.grounded=true);
//                return;
//            }
//        }
//        handleGroundedChange(()->output.grounded=false);
//    }
//
//    @Override
//    public void evaluate() {
//        output.evaluate();
//        print(name+"evaluate");
//        if (!output.grounded){
//            print(name+"not grounded");
//            for (In i: output.inputs){
//                if (i.out()){
//                    print(name+"input on");
//                    handleValueChange(()->value=ON);
//                    print(name+"end evaluate");
//                    return;
//                }
//                print(name+"input not on");
//            }
//            print(name+"no inputs on");
//        } else {
//            print(name+"grounded");
//        }
//        handleValueChange(()->value=OFF);
//        print(name+"end evaluate");
//    }
//}


//    private List<In> inputs = new ArrayList<>();
//
//    public Buffer(In ... ins){
//        this("", ins);
//    }
//
//    public Buffer(String name, In ... ins){
//        this.name = name;
//        for (In in: ins){
//            addInput(in);
//            in.register(this);
//        }
//        evaluate();
//    }
//
//    @Override
//    public boolean isSink() {
//        for (Out o:outputs){
//            if (o.isSink()){
//                System.out.println(name+" found sink");
//                return true;
//            }
//        }
//        System.out.println(name+" not grounded");
//        return false;
//    }
//
//    @Override
//    public void evaluate(){
//        if (isSink()){
//            print(name+" evaluate false because sink");
//            handleValueChange(()->value=OFF);
//        } else if (anyInput()){
//            handleValueChange(()->value=ON);
//        } else {
//            print(name+" evaluate false because no inputs");
//            handleValueChange(()->value=OFF);
//        }
//    }
//
//    private boolean anyInput() {
//        for (In i:inputs){
//            if (i.out()){
//                //print("junction anyInput true");
//                return true;
//            }
//        }
//        //print("junction anyInput false");
//        return false;
//    }
//
//    protected void addInput(In i){
//        inputs.add(i);
//        i.register(this);
//        evaluate();
//    }
//
//    @Override
//    public void register(Out o){
//        super.register(o);
//        evaluate();
//    }