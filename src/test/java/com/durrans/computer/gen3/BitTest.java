package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Button;
import com.durrans.computer.gen1.Switch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BitTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestBitIsSetAndStaysSet() {

        Switch value = new Switch(POWER);
        Button set = new Button(POWER);
        Bit bit = new Bit(value, set);
        POWER.on();
        value.press();
        set.press();

        assertTrue(bit.out());

        value.press();

        assertTrue(bit.out());
        value.press();

        assertTrue(bit.out());
    }

    @Test
    public void TestBitIsSetAndUnset() {
        POWER.on();
        Switch value = new Switch("value", POWER);
        Button set = new Button("set", POWER);
        Bit bit = new Bit(value, set);

        // Starts off
        assertFalse(bit.out());

        // Not set yet
        value.on();
        assertFalse(bit.out());

        // Set to on
        set.press();
        assertTrue(bit.out());

        // Stays on
        value.off();
        assertTrue(bit.out());

        // Goes off
        set.press();
        assertFalse(bit.out());

        // Comes back on
        value.on();
        set.press();
        assertTrue(bit.out());
    }
}
