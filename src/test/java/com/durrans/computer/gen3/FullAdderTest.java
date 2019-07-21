package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Switch;
import org.junit.Test;

import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.assertArrayEquals;

public class FullAdderTest {

    @Test
    public void TestHalfAdderAddsTwoBinaryDigits(){
        Component off = new Switch();
        Component on = POWER;
        POWER.on();
        assertArrayEquals(new boolean[]{false, false}, new FullAdder(off, off, off).getBoth());
        assertArrayEquals(new boolean[]{false, true}, new FullAdder(off, off, on).getBoth());
        assertArrayEquals(new boolean[]{false, true}, new FullAdder(off, on, off).getBoth());
        assertArrayEquals(new boolean[]{true, false}, new FullAdder(off, on, on).getBoth());
        assertArrayEquals(new boolean[]{false, true}, new FullAdder(on, off, off).getBoth());
        assertArrayEquals(new boolean[]{true, false}, new FullAdder(on, off, on).getBoth());
        assertArrayEquals(new boolean[]{true, false}, new FullAdder(on, on, off).getBoth());
        assertArrayEquals(new boolean[]{true, true}, new FullAdder(on, on, on).getBoth());
    }

}
