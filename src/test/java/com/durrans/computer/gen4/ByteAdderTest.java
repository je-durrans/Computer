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

    MComponent<Switch> ins;
    Button setB1, setB2, add;
    MComponent<Bit> in1, in2, out;
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

        ins = new MComponent<>(Switch.class, 8);
        ins.register(POWER);

        setB1 = new Button("b1", POWER);
        setB2 = new Button("b2", POWER);
        add = new Button("add", POWER);

        in1 = new MComponent<>(Bit.class, 8); in1.register(ins); in1.register(setB1);
        in2 = new MComponent<>(Bit.class, 8); in2.register(ins); in2.register(setB2);

        adder = new ByteAdder(in1, in2, new Component(), 8);
        out = new MComponent<>(Bit.class, 8); out.register(adder.getOuts()); out.register(add);
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
