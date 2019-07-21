package com.durrans.computer.gen1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class TransistorTest {

    @Before
    @After
    public void setUp(){
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestTransistor(){

        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        Transistor t = new Transistor(s1, s2);

        //OFF/OFF
        assertFalse(t.out());

        //OFF/ON
        s2.press();
        assertFalse(t.out());

        //ON/ON
        s1.press();
        assertTrue(t.out());

        //ON/OFF
        s2.press();
        assertFalse(t.out());

    }

    @Test
    public void TestGroundedTransistorActsAsSink(){

        POWER.on();
        Switch s1 = new Switch();
        Switch s2 = new Switch();

        Transistor t = new Transistor("",s1, s2);
        SINK.registerInput(t);

        //OFF/OFF
        assertFalse(t.out());

        //OFF/ON
        s2.press();
        assertFalse(t.out());

        //ON/ON
        s1.press();
        assertFalse(t.out());

        //ON/OFF
        s2.press();
        assertFalse(t.out());
    }

    @Test
    public void TestTransistorOnlyAcceptsTwoInputs(){
        try {
            new Transistor("", new Switch(), new Switch(), new Switch());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Transistor must be instantiated with exactly two inputs");
        }
    }

}
