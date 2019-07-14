package com.durrans.computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.Sink.SINK;
import static com.durrans.computer.Switch.POWER;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChainTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestBufferChain(){
        Component b = new Component(POWER);
        Component c = new Component(b);
        assertFalse(b.out());
        assertFalse(c.out());
        POWER.on();
        assertTrue(b.out());
        assertTrue(c.out());
    }

    @Test
    public void TestBufferChainWithSink(){
        Component b = new Component(POWER);
        Component c = new Component(b);
        SINK.registerInput(c);
        assertFalse(b.out());
        assertFalse(c.out());
        POWER.on();
        assertFalse(b.out());
        assertFalse(c.out());
    }

    @Test
    public void TestTransistorChain(){
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        s1.on();
        s2.on();
        Transistor t = new Transistor(POWER, s1);
        Transistor u = new Transistor(t, s2);
        assertFalse(t.out());
        assertFalse(u.out());
        System.out.println("\nPressing\n");
        POWER.on();
        assertTrue(t.out());
        assertTrue(u.out());
    }

    @Test
    public void TestTransistorChainWithSink(){
        Switch mainIn = new Switch(POWER);
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        s1.on();
        s2.on();
        Transistor t = new Transistor(mainIn, s1);
        Transistor u = new Transistor(t, s2);
        System.out.println("\nAddingSink\n");
        SINK.registerInput(u);
        assertFalse(t.out());
        assertFalse(u.out());
        System.out.println("\nswitching on\n");
        POWER.on();
        assertFalse(t.out());
        assertFalse(u.out());

    }

}
