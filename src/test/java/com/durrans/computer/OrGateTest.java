package com.durrans.computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.Sink.SINK;
import static com.durrans.computer.Switch.POWER;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrGateTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestOrGate() {
        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        OrGate or = new OrGate(s1, s2);

        //OFF/OFF
        assertFalse(or.out());

        //OFF/ON
        s2.press();
        assertTrue(or.out());

        //ON/ON
        s1.press();
        assertTrue(or.out());

        //ON/OFF
        s2.press();
        assertTrue(or.out());
    }

}
