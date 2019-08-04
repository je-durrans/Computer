package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MultiComponentFactory {

    static Component[] createComponentArray(Class clazz, int number, Component[]... inputs) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Component[] array = new Component[number];
        Constructor ctor = clazz.getConstructor(Component[].class);

        for (int i = 0; i < number; i++) {
            Component[] args = new Component[inputs.length];
            for (int j = 0; j < inputs.length; j++) {
                if (inputs[j].length == 1) {
                    args[j] = inputs[j][0];
                } else {
                    args[j] = inputs[j][i];
                }
            }

            array[i] = (Component) ctor.newInstance((Object) args);
        }

        return array;
    }

}
