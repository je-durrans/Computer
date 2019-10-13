package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen3.Bit;
import com.durrans.computer.gen3.FullAdder;

public class ByteAdder extends MComponent<FullAdder>{

    private MComponent<FullAdder> adders = new MComponent<>(FullAdder.class, 8);

    public ByteAdder(MComponent<Bit> b1, MComponent<Bit> b2, Component carryIn){

        adders.get(7).registerInput(b1.get(7)); adders.get(7).registerInput(b2.get(7)); adders.get(7).registerInput(carryIn);
        for (int i=1; i<8; i++){
            //adders[7-i] = new FullAdder(b1.get(7-i), b2.get(7-i), adders[8-i].getCarry());
            adders.get(7-i).registerInput(b1.get(7-i)); adders.get(7-i).registerInput(b2.get(7-i)); adders.get(7-i).registerInput(adders.get(8-i).getCarry());

        }

    }

    public boolean[] out(){

        boolean[] ret = new boolean[8];
        for (int i=0; i<8; i++){
            ret[i] = adders.get(i).out();
        }
        return ret;
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



}
