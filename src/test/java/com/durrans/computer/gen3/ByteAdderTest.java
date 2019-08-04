package com.durrans.computer.gen3;

import com.durrans.computer.gen1.Button;
import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Switch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class ByteAdderTest {

    Switch[] ins;
    Button setB1, setB2, add;
    Byte in1, in2, out;
    ByteAdder adder;

    @After
    public void tearDown() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Before
    public void setup(){
        POWER.off();
        POWER.clear();
        SINK.clear();

        ins = new Switch[8];
        for (int i=0; i<8; i++)
            ins[i] = new Switch("switch"+i, POWER);

        setB1 = new Button("b1", POWER);
        setB2 = new Button("b2", POWER);
        add = new Button("add", POWER);

        in1 = new Byte(ins, setB1);
        in2 = new Byte(ins, setB2);

        adder = new ByteAdder(in1, in2, new Component());
        out = new Byte(adder.getOuts(), add);
    }

    @Test
    public void TestAddingWithNoOverLap(){

        POWER.on();
        ins[3].on();
        ins[5].on();
        ins[7].on();

        setB1.press();

        ins[0].press();
        ins[2].press();
        ins[3].press();
        ins[4].press();
        ins[5].press();
        ins[6].press();
        ins[7].press();

        setB2.press();

        add.press();

        assertArrayEquals(new boolean[]{false, false, false, true, false, true, false, true}, in1.out());
        assertArrayEquals(new boolean[]{true, false, true, false, true, false, true, false}, in2.out());
        assertArrayEquals(new boolean[]{true, false, true, true, true, true, true, true}, out.out());
        assertFalse(adder.didOverflow());
    }

    @Test
    public void TestAddingWithOverlap(){

        POWER.on();
        ins[1].on();
        ins[3].on();
        ins[5].on();
        ins[7].on();
        setB1.press();
        setB2.press();
        add.press();
        assertArrayEquals(new boolean[]{false, true, false, true, false, true, false, true}, in1.out());
        assertArrayEquals(in1.out(), in2.out());
        assertArrayEquals(new boolean[]{true, false, true, false, true, false, true, false}, out.out());
        assertFalse(adder.didOverflow());
    }

    @Test
    public void TestAddingWithDenserOverlap(){

        POWER.on();
        ins[4].on();
        ins[5].on();
        ins[6].on();
        ins[7].on();
        setB1.press();
        setB2.press();
        add.press();
        assertArrayEquals(new boolean[]{false, false, false, false, true, true, true, true}, in1.out());
        assertArrayEquals(in1.out(), in2.out());
        assertArrayEquals(new boolean[]{false, false, false, true, true, true, true, false}, out.out());
        assertFalse(adder.didOverflow());
    }

    @Test
    public void TestOverflow(){

        POWER.on();
        ins[7].on();
        setB1.press();
        ins[0].on();
        ins[1].on();
        ins[2].on();
        ins[3].on();
        ins[4].on();
        ins[5].on();
        ins[6].on();
        setB2.press();
        add.press();
        assertArrayEquals(new boolean[]{false, false, false, false, false, false, false, true}, in1.out());
        assertArrayEquals(new boolean[]{true, true, true, true, true, true, true, true}, in2.out());
        assertArrayEquals(new boolean[]{false, false, false, false, false, false, false, false}, out.out());
        assertTrue(adder.didOverflow());

    }
}
