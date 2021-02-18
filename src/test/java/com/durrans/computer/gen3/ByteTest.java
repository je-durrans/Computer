package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Switch;
import com.durrans.computer.gen1.Button;
import com.durrans.computer.gen4.MultiComponent;
import org.junit.Test;

import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.assertArrayEquals;

public class ByteTest {

    @Test
    public void TestByteStoresAndClears() {
        POWER.on();
        MultiComponent<Switch> switches = new MultiComponent<>(Switch.class, 8);//new Switch[8];
        switches.connectFrom(POWER);
//        for (int i=0; i<8; i++)
//            switches[i] = new Switch("switch-"+i, POWER);

        Button store = new Button("button");
        MultiComponent<Bit> b = new MultiComponent<>(Bit.class, 8);
        b.connectFrom(switches);
        b.connectFrom(store);
        //Byte b = new Byte(switches, store);

//        System.out.println();

        switches.get(2).on();
        switches.get(3).on();
        switches.get(6).on();
        store.press();

        System.out.println(b.toString());

        assertArrayEquals(new boolean[]{false, false, true, true, false, false, true, false}, b.out());

        switches.get(2).off();
        switches.get(3).off();
        switches.get(6).off();
        store.press();

//        System.out.println();

        assertArrayEquals(new boolean[]{false, false, false, false, false, false, false, false}, b.out());

    }
}
