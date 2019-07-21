package com.durrans.computer.gen2;

import com.durrans.computer.gen1.Switch;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class NandGateTest {

    @Before
    @After
    public void setUp() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestNandGate() {
        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        NandGate nand = new NandGate(s1, s2);

        //OFF/OFF
        assertTrue(nand.out());

        //OFF/ON
        s2.press();
        assertTrue(nand.out());

        //ON/ON
        s1.press();
        assertFalse(nand.out());

        //ON/OFF
        s2.press();
        assertTrue(nand.out());
    }

    @Test
    public void TestAddingInputsAfter(){

        POWER.on();
        Switch s1 = new Switch(POWER);
        Switch s2 = new Switch(POWER);
        Switch tooMany = new Switch(POWER);

        NandGate nand  = new NandGate("");

        nand.registerInput(s1);
        nand.registerInput(s2);
        try{
            nand.registerInput(tooMany);
            fail();
        } catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Too many inputs for component of type NandGate");
        }
    }

}
