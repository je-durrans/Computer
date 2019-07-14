package com.durrans.computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.Sink.SINK;
import static com.durrans.computer.Switch.POWER;
import static org.junit.Assert.*;

public class NotGateTest {

    @Before
    @After
    public void setUp(){
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestNotGateInvertsOff(){
        POWER.on();
        Switch s = new Switch(POWER);
        NotGate not = new NotGate(s);
        assertTrue(not.out());
    }

    @Test
    public void TestNotGateInvertsOn(){
        POWER.on();
        Switch s = new Switch(POWER);
        s.on();
        NotGate not = new NotGate(s);
        assertFalse(not.out());
    }


    @Test
    public void TestNotGateInverts(){
        POWER.on();
        Switch s = new Switch(POWER);
        NotGate not = new NotGate(s);

        assertTrue(not.out());

        s.press();

        assertFalse(not.out());

    }

    @Test
    public void TestNotGateOnlyUsesFirstInput(){
        try {
            new NotGate("", new Switch(), new Switch());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Too many inputs for component of type NotGate");
        }
    }

}
