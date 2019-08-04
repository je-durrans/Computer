package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Component;

public class ByteAdder {

    private FullAdder[] adders = new FullAdder[8];

    public ByteAdder(Byte b1, Byte b2, Component carryIn){

        adders[7] = new FullAdder(b1.getOutput(7), b2.getOutput(7), carryIn);
        for (int i=1; i<8; i++){
            adders[7-i] = new FullAdder(b1.getOutput(7-i), b2.getOutput(7-i), adders[8-i].getCarry());
        }

    }

    public boolean[] out(){

        boolean[] ret = new boolean[8];
        for (int i=0; i<8; i++){
            ret[i] = adders[i].out();
        }
        return ret;
    }

    public Component[] getOuts(){
         Component[] outs = new Component[8];
        for (int i=0; i<8; i++){
            outs[i] = adders[i].getSum();
        }
        return outs;
    }


    public boolean didOverflow(){
        return adders[0].getCarry().out();
    }

}
