package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;

import java.util.ArrayList;

/**
 * MultiComponent represents a collection of components of the same type.
 * Methods allow for faster connection of 1-to-N or N-to-N arrangements
 * @param <T>
 */
public class MultiComponent<T extends Component> extends ArrayList<T> {

    public MultiComponent(Class<T> clazz, int number){
        try {
            for (int i = 0; i < number; i++) {
                this.add(clazz.getConstructor(Component[].class).newInstance((Object) new Component[]{}));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public MultiComponent() {

    }

    public void connectFrom(Component in){
        for (Component c: this){
            c.connectFrom(in);
        }
    }

    public void connectFrom(MultiComponent in){
        for (int i=0; i<size(); i++){
            get(i).connectFrom(in.get(i));
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
