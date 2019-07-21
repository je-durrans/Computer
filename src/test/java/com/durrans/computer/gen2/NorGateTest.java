package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Switch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class NorGateTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestNorGate() {
        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        NorGate nor = new NorGate(s1, s2);

        //OFF/OFF
        assertTrue(nor.out());

        //OFF/ON
        s2.press();
        assertFalse(nor.out());

        //ON/ON
        s1.press();
        assertFalse(nor.out());

        //ON/OFF
        s2.press();
        assertFalse(nor.out());
    }

    @Test
    public void TestAddingInputsAfter(){

        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        Switch tooMany = new Switch(POWER);

        NorGate nor  = new NorGate("");

        nor.registerInput(s1);
        nor.registerInput(s2);
        try{
            nor.registerInput(tooMany);
            fail();
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Too many inputs for component of type NorGate");
        }
    }

}
