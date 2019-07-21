package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Switch;
import org.junit.Test;

import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.assertArrayEquals;

public class HalfAdderTest {

    @Test
    public void TestHalfAdderAddsTwoBinaryDigits(){
        Switch off = new Switch();
        Component on = POWER;
        POWER.on();
        assertArrayEquals(new boolean[]{false, false}, new boolean[]{new HalfAdder(off, off).getCarry(), new HalfAdder(off, off).out()});
        assertArrayEquals(new boolean[]{false, true}, new boolean[]{new HalfAdder(off, on).getCarry(), new HalfAdder(off, on).out()});
        assertArrayEquals(new boolean[]{false, true}, new boolean[]{new HalfAdder(on, off).getCarry(), new HalfAdder(on, off).out()});
        assertArrayEquals(new boolean[]{true, false}, new boolean[]{new HalfAdder(on, on).getCarry(), new HalfAdder(on, on).out()});
    }

}
