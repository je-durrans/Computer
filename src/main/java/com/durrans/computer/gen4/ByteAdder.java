package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen3.FullAdder;

public class ByteAdder extends MComponent<FullAdder>{

    private MComponent<FullAdder> adders;
    private int n;

        public ByteAdder(MComponent<? extends Component> b1, MComponent<? extends Component> b2, Component carryIn){

            if (b1.size()!=b2.size()){
                throw new IllegalArgumentException("Inputs must be the same length, got "+b1.size()+" and "+b2.size());
            }

            this.n = b1.size();
            adders = new MComponent<>(FullAdder.class, n);

            adders.get(n-1).registerInput(b1.get(n-1));
            adders.get(n-1).registerInput(b2.get(n-1));
            adders.get(n-1).registerInput(carryIn);

            for (int i=n-2; i>=0; i--){
                adders.get(i).registerInput(b1.get(i));
                adders.get(i).registerInput(b2.get(i));
                adders.get(i).registerInput(adders.get(i+1).getCarry());
            }
        }

    public MComponent<Component> getOuts(){
        MComponent<Component> outs = new MComponent<>();
        for (int i=0; i<n; i++){
            outs.add(adders.get(i).getSum());
        }
        return outs;
    }

    public boolean didOverflow(){
        return adders.get(0).getCarry().out();
    }


    @Override
    public boolean[] out(){

        boolean[] ret = new boolean[n];
        for (int i=0; i<n; i++){
            ret[i] = adders.get(i).out();
        }
        return ret;
    }

    @Override
    public void register(Component in) { }

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
