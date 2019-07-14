package com.durrans.computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.Sink.SINK;
import static com.durrans.computer.Switch.POWER;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

        Transistor t = new Transistor(s1, s2);
        SINK.registerInput(t);

        //OFF/OFF
        assertFalse(t.out());

        //OFF/ON
        s2.press();
        assertFalse(t.out());

        System.out.println(t.isGrounded());

        //ON/ON
        s1.press();
        System.out.println(s1.out()+""+s2.out());
        System.out.println("here"+t.out());
        assertFalse(t.out());

        //ON/OFF
        s2.press();
        assertFalse(t.out());
    }

}
