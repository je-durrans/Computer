package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Switch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class AndGateTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestAndGate() {
        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        AndGate and = new AndGate(s1, s2);

        //OFF/OFF
        assertFalse(and.out());

        //OFF/ON
        s2.press();
        assertFalse(and.out());

        //ON/ON
        s1.press();
        assertTrue(and.out());

        //ON/OFF
        s2.press();
        assertFalse(and.out());
    }

    @Test
    public void TestAddingInputsAfter(){

        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        Switch tooMany = new Switch(POWER);

        AndGate and  = new AndGate("");

        and.connectFrom(s1);
        and.connectFrom(s2);
        try{
            and.connectFrom(tooMany);
            fail();
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Too many inputs for component of type AndGate");
        }
    }

}
