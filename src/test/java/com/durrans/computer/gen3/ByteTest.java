package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Switch;
import com.durrans.computer.gen1.Button;
import org.junit.Test;

import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.assertArrayEquals;

public class ByteTest {

    @Test
    public void TestByteStoresAndClears() {
        POWER.on();
        Switch[] switches = new Switch[8];
        for (int i=0; i<8; i++)
            switches[i] = new Switch("switch-"+i, POWER);

        Button store = new Button("button");
        Byte b = new Byte(switches, store);

//        System.out.println();

        switches[2].on();
        switches[3].on();
        switches[6].on();
        store.press();

        System.out.println(b.toString());

        assertArrayEquals(new boolean[]{false, false, true, true, false, false, true, false}, b.out());

        switches[2].off();
        switches[3].off();
        switches[6].off();
        store.press();

//        System.out.println();

        assertArrayEquals(new boolean[]{false, false, false, false, false, false, false, false}, b.out());

    }
}
