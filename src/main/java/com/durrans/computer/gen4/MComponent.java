package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;

import java.util.ArrayList;

public class MComponent<T extends Component> extends ArrayList<T> {

    public MComponent(Class<T> clazz, int number){
        try {
            for (int i = 0; i < number; i++) {
                this.add(clazz.getConstructor(Component[].class).newInstance((Object) new Component[]{}));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public MComponent() {

    }

    public void register(Component in){
        for (Component c: this){
            c.registerInput(in);
        }
    }

    public void register(MComponent in){
        for (int i=0; i<size(); i++){
            get(i).registerInput(in.get(i));
        }
    }

    public T get(int index){
        if (index<size()){
            return super.get(index);
        }
        return super.get(0);
    }

    public boolean[] out(){
        boolean[] ret = new boolean[size()];
        for (int i=0; i<size(); i++){
            ret[i] = get(i).out();
        }
        return ret;
    }

}
