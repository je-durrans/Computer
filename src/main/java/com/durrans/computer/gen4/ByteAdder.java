package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen3.FullAdder;

public class ByteAdder extends MComponent<FullAdder>{

    private MComponent<FullAdder> adders = new MComponent<>(FullAdder.class, 8);
    private int n;

//    public ByteAdder(MComponent<Bit> b1, MComponent<Bit> b2, Component carryIn){
//
//        adders.get(7).registerInput(b1.get(7));
//        adders.get(7).registerInput(b2.get(7));
//        adders.get(7).registerInput(carryIn);
//
//        for (int i=1; i<8; i++){
//            adders.get(7-i).registerInput(b1.get(7-i));
//            adders.get(7-i).registerInput(b2.get(7-i));
//            adders.get(7-i).registerInput(adders.get(8-i).getCarry());
//        }
//    }

        public ByteAdder(MComponent<? extends Component> b1, MComponent<? extends Component> b2, Component carryIn, int n){
            this.n = n;
            adders = new MComponent<>(FullAdder.class, n);

            adders.get(n-1).registerInput(b1.get(n-1));
            adders.get(n-1).registerInput(b2.get(n-1));
            adders.get(n-1).registerInput(carryIn);

            for (int i=n-2; i>=0; i--){
                adders.get(i).registerInput(b1.get(i));
                adders.get(i).registerInput(b2.get(i));
                adders.get(i).registerInput(adders.get(i+1).getCarry());
                //size-1-i i=1>size-1 size-2 > 0
                //size-i   i=1-size-1 size-1 > 1
            }
        }

    public MComponent<Component> getOuts(){
        MComponent<Component> outs = new MComponent<>();
        for (int i=0; i<8; i++){
            outs.add(adders.get(i).getSum());
        }
        return outs;
    }

    public boolean didOverflow(){
        return adders.get(0).getCarry().out();
    }


    @Override
    public boolean[] out(){

        boolean[] ret = new boolean[8];
        for (int i=0; i<8; i++){
            ret[i] = adders.get(i).out();
        }
        return ret;
    }

    @Override
    public void register(Component in) {
        throw new RuntimeException("Don't call this");
    }

    @Override
    public void register(MComponent in){
        for (int i=0; i<n; i++){
            adders.get(i).registerInput(in.get(i));
        }
    }

    @Override
    public FullAdder get(int index){
        if (index<n){
            return adders.get(index);
        }
        return super.get(0);
    }

}
