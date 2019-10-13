package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Button;
import com.durrans.computer.gen1.Switch;
import com.durrans.computer.gen1.Transistor;
import com.durrans.computer.gen2.XorGate;
import com.durrans.computer.gen3.Bit;

import java.util.Arrays;

import static com.durrans.computer.gen1.Switch.POWER;

public class ALU {

    public static void main(String[] args) {
        int size = 8;
        MComponent<Switch> inputs = new MComponent<>(Switch.class, size);
        inputs.register(POWER);
        MComponent<Bit> buffer1 = new MComponent<>(Bit.class, size);
        MComponent<Bit> buffer2 = new MComponent<>(Bit.class, size);
        Button set1 = new Button(POWER);
        Button set2 = new Button(POWER);
        buffer1.register(inputs); buffer1.register(set1);
        buffer2.register(inputs); buffer2.register(set2);
        Switch operation = new Switch(POWER);
        MComponent<XorGate> flipper = new MComponent<>(XorGate.class, size);
        flipper.register(operation);
        flipper.register(buffer2);
        ByteAdder adder = new ByteAdder(buffer1, flipper, operation, size);
        POWER.on();
        inputs.get(5).on();
        set1.press();
        inputs.get(6).on();
        set2.press();
        operation.press();

        System.out.println(Arrays.toString(buffer1.out()));
        System.out.println(Arrays.toString(flipper.out()));

        System.out.println(Arrays.toString(adder.out()));
        System.out.println(Transistor.howMany());
    }

}
