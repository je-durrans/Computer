package com.durrans.computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.Sink.SINK;
import static com.durrans.computer.Switch.POWER;
import static org.junit.Assert.*;

public class ComponentTest {

    @Before
    @After
    public void setUp(){
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestComponent(){

        POWER.on();
        Switch s1 = new Switch("Switch1", POWER);
        Switch s2 = new Switch("Switch2", POWER);

        Component j = new Component(s1, s2);

        //OFF/OFF
        assertFalse(j.out());

        //OFF/ON
        s2.press();
        assertTrue(j.out());

        //ON/ON
        s1.press();
        assertTrue(j.out());

        //ON/OFF
        s2.press();
        assertTrue(j.out());
    }

    @Test
    public void TestBufferWithSink(){

        POWER.on();
        Switch s1 = new Switch("S1");
        Switch s2 = new Switch("S2");

        Component j = new Component(s1, s2);
        SINK.registerInput(j);

        //OFF/OFF
        assertFalse(j.out());

        //OFF/ON
        s2.press();
        assertFalse(j.out());

        //ON/ON
        s1.press();
        assertFalse(j.out());

        //ON/OFF
        s2.press();
        assertFalse(j.out());

    }

}
