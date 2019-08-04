package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen1.Switch;
import com.durrans.computer.gen2.NotGate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static com.durrans.computer.gen1.Sink.SINK;
import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.*;

public class MultiComponentTest {

    @Before
    @After
    public void setup() {
        POWER.off();
        POWER.clear();
        SINK.clear();
    }

    @Test
    public void TestMultiSwitch(){
        try {
            POWER.on();
            Component[] switches8 = MultiComponentFactory.createComponentArray(Switch.class, 8, new Component[]{POWER});
            assertEquals(switches8.length, 8);
            for (int i = 0; i < 8; i++) {
                assertTrue(switches8[i] instanceof Switch);
            }

            ((Switch)switches8[2]).press();
            ((Switch)switches8[3]).press();
            ((Switch)switches8[5]).press();

            assertEquals(switches8[0].out(), false);
            assertEquals(switches8[1].out(), false);
            assertEquals(switches8[2].out(), true);
            assertEquals(switches8[3].out(), true);
            assertEquals(switches8[4].out(), false);
            assertEquals(switches8[5].out(), true);
            assertEquals(switches8[6].out(), false);
            assertEquals(switches8[7].out(), false);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void TestMultiNotGate(){
        try {
            POWER.on();
            Component[] switches8 = MultiComponentFactory.createComponentArray(Switch.class, 8, new Component[]{POWER});
            Component[] not8 = MultiComponentFactory.createComponentArray(NotGate.class, 8, switches8);
            assertEquals(not8.length, 8);
            for (int i = 0; i < 8; i++) {
                assertTrue(not8[i] instanceof NotGate);
            }

            ((Switch)switches8[2]).press();
            ((Switch)switches8[3]).press();
            ((Switch)switches8[5]).press();

            assertEquals(not8[0].out(), true);
            assertEquals(not8[1].out(), true);
            assertEquals(not8[2].out(), false);
            assertEquals(not8[3].out(), false);
            assertEquals(not8[4].out(), true);
            assertEquals(not8[5].out(), false);
            assertEquals(not8[6].out(), true);
            assertEquals(not8[7].out(), true);


        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            fail();
        } finally {
            POWER.off();
        }
    }
}
