package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Button;
import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Switch;
import com.durrans.computer.gen3.Bit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class ByteAdderTest {

    final int SIZE = 8;

    MultiComponent<Switch> ins;
    Button setB1, setB2, add;
    MultiComponent<Bit> in1, in2, out;
    NBitAdder adder;

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

        ins = new MultiComponent<>(Switch.class, SIZE);
        ins.connectFrom(POWER);

        setB1 = new Button("b1", POWER);
        setB2 = new Button("b2", POWER);
        add = new Button("add", POWER);

        in1 = new MultiComponent<>(Bit.class, SIZE); in1.connectFrom(ins); in1.connectFrom(setB1);
        in2 = new MultiComponent<>(Bit.class, SIZE); in2.connectFrom(ins); in2.connectFrom(setB2);

        adder = new NBitAdder(in1, in2, new Component());
        out = new MultiComponent<>(Bit.class, SIZE); out.connectFrom(adder.getOuts()); out.connectFrom(add);
    }

    @Test
    public void TestAddingWithNoOverLap(){

        POWER.on();
        ins.get(3).on();
        ins.get(5).on();
        ins.get(7).on();

        setB1.press();

        ins.get(0).press();
        ins.get(2).press();
        ins.get(3).press();
        ins.get(4).press();
        ins.get(5).press();
        ins.get(6).press();
        ins.get(7).press();

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
        ins.get(1).on();
        ins.get(3).on();
        ins.get(5).on();
        ins.get(7).on();
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
        ins.get(4).on();
        ins.get(5).on();
        ins.get(6).on();
        ins.get(7).on();
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
        ins.get(7).on();
        setB1.press();
        ins.get(0).on();
        ins.get(1).on();
        ins.get(2).on();
        ins.get(3).on();
        ins.get(4).on();
        ins.get(5).on();
        ins.get(6).on();
        setB2.press();
        add.press();
        assertArrayEquals(new boolean[]{false, false, false, false, false, false, false, true}, in1.out());
        assertArrayEquals(new boolean[]{true, true, true, true, true, true, true, true}, in2.out());
        assertArrayEquals(new boolean[]{false, false, false, false, false, false, false, false}, out.out());
        assertTrue(adder.didOverflow());

    }
}
