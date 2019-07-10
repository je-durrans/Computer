package com.durrans.computer;

import org.junit.Test;

import static com.durrans.computer.ConstantInput.GROUND;
import static com.durrans.computer.ConstantInput.LIVE;
import static org.junit.Assert.*;

public class ConstantInputTest {

    @Test
    public void TestConstantInput(){
        assertFalse(GROUND.out());
        assertTrue(LIVE.out());
    }

}
