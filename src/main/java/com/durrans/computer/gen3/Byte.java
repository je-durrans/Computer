package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Component;

public class Byte {

    private Component[] outs = new Component[8];
    private String name;

    public Byte(String name, Component[] ins, Component setter){
        this.name = name;
        for (int i=0; i<8; i++){
            outs[i] = new Bit(name+"-bit"+i, ins[i], setter);
        }
    }

    public Byte(Component[] ins, Component setter){
        this("", ins, setter);
    }

    public boolean[] out() {
        boolean[] out = new boolean[8];
        for (int i=0; i<8; i++) {
            out[i] = outs[i].out();
        }
        return out;
    }

    public boolean out(int index){
        return outs[index].out();
    }

    Component getOutput(int index){
        return outs[index];
    }

    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(boolean b : out()){
            ret.append(b).append(", ");
        }
        return ret.toString();
    }

    public String getName() {
        return name;
    }
}
