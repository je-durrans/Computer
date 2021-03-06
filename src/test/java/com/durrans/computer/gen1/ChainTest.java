package com.durrans.computer.gen1;

import com.durrans.computer.gen2.NotGate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

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
        SINK.connectFrom(c);
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

        SINK.connectFrom(u);

        assertFalse(t.out());
        assertFalse(u.out());

        POWER.on();

        assertFalse(t.out());
        assertFalse(u.out());

    }

    @Test
    public void TestNotChain(){
        Switch s = new Switch(POWER);
        NotGate n = new NotGate("second", new NotGate("first", s));
        POWER.on();
        assertFalse(n.out());

        s.on();
        assertTrue(n.out());

        s.off();
        assertFalse(n.out());
    }

}
