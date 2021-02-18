package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Switch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class XorGateTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestXorGate() {
        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        XorGate xor = new XorGate(s1, s2);

        //OFF/OFF
        assertFalse(xor.out());

        //OFF/ON
        s2.press();
        assertTrue(xor.out());

        //ON/ON
        s1.press();
        assertFalse(xor.out());

        //ON/OFF
        s2.press();
        assertTrue(xor.out());
    }

    @Test
    public void TestAddingInputsAfter(){

        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        Switch tooMany = new Switch(POWER);

        XorGate xor  = new XorGate("");

        xor.connectFrom(s1);
        xor.connectFrom(s2);
        try{
            xor.connectFrom(tooMany);
            fail();
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Too many inputs for component of type XorGate");
        }
    }

}
