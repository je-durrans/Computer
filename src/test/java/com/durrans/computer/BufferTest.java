package com.durrans.computer;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import static com.durrans.computer.Config.debug;
import static com.durrans.computer.Config.print;
import static com.durrans.computer.ConstantInput.*;
import static com.durrans.computer.Sink.SINK;
import static org.junit.Assert.*;

public class BufferTest {

    private Component on = LIVE;
    private Component off = GROUND;
    private Component sink = SINK;

    @After
    public void tearDown(){
        debug=false;
    }
//    @Ignore
    @Test
    public void OldTestBuffer(){
        assertFalse(new Buffer(off, off).out());
        debug=true;
        assertTrue(new Buffer("Buffer", off, on).out());
        assertTrue(new Buffer(on, off).out());
        assertTrue(new Buffer(on, on).out());
    }

    @Test
    public void TestBuffer(){
        Switch s1 = new Switch("Switch1");
        Switch s2 = new Switch("Switch2");
        Buffer j = new Buffer("Buffer", s1, s2);

        //OFF/OFF
        assertFalse(j.out());
        print("test1 done");

        print("");
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

//    @Ignore
    @Test
    public void OLDTestBufferWithSink(){

        Buffer j;

        j = new Buffer(off, off);
        j.registerOutput(SINK);
        assertFalse(j.out());

        j = new Buffer(off, on);
        j.registerOutput(sink);
        assertTrue(j.isGrounded());
        assertFalse(j.out());

        j = new Buffer(on, off);
        j.registerOutput(sink);
        assertFalse(j.out());

        j = new Buffer(on, on);
        j.registerOutput(sink);
        assertFalse(j.out());
    }

    @Test
    public void TestBufferWithSink(){

        Switch s1 = new Switch("S1");
        Switch s2 = new Switch("S2");
        Buffer j = new Buffer("B", s1, s2);
        j.registerOutput(SINK);

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
