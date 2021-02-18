package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Switch;
import org.junit.Test;

import static com.durrans.computer.gen1.Switch.POWER;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MultiplexerTest {

    @Test
    public void TestMultiplexer(){
        POWER.on();
        boolean[] ba = new boolean[]{false, true};
        for (boolean b1:ba){
            for (boolean b2:ba){
                for (boolean b3:ba){
                    Switch input1 = new Switch(POWER);
                    if (b1) { input1.press(); }
                    Switch input2 = new Switch(POWER);
                    if (b2) { input2.press(); }
                    Switch toggle = new Switch(POWER);
                    if (b3) { toggle.press(); }
                    Multiplexer m = new Multiplexer(input1, input2, toggle);
                    assertArrayEquals(new boolean[]{b1, b2, b3}, new boolean[]{input1.out(), input2.out(), toggle.out()});
                    assertEquals(toggle.out()? input2.out(): input1.out(), m.out());
                }
            }
        }
    }

    @Test
    public void TestStackedMultiplexers(){
        POWER.on();
        int count = 0;
        boolean[] ba = new boolean[]{false, true};
        for (boolean b1:ba){
            for (boolean b2:ba){
                for (boolean b3:ba){
                    for (boolean b4:ba) {
                        for (boolean b5:ba){
                            for (boolean b6:ba){
                                System.out.println("count is: "+count);
                                count++;
                                Switch input1 = new Switch(POWER);
                                if (b1) { input1.press(); }
                                Switch input2 = new Switch(POWER);
                                if (b2) { input2.press(); }
                                Switch input3 = new Switch(POWER);
                                if (b3) { input3.press(); }
                                Switch input4 = new Switch(POWER);
                                if (b4) { input4.press(); }
                                Switch toggle1 = new Switch(POWER);
                                if (b5) { toggle1.press(); }
                                Switch toggle2 = new Switch(POWER);
                                if (b6) { toggle2.press(); }
                                Multiplexer m11 = new Multiplexer(input1, input2, toggle1);
                                Multiplexer m12 = new Multiplexer(input3, input4, toggle1);
                                Multiplexer m2 = new Multiplexer(m11, m12, toggle2);
                                assertArrayEquals(new boolean[]{b1, b2, b3, b4, b5, b6},
                                        new boolean[]{input1.out(), input2.out(), input3.out(), input4.out(), toggle1.out(), toggle2.out()});
                                assertEquals(toggle2.out() ? toggle1.out() ? input4.out() : input3.out() : toggle1.out() ? input2.out() : input1.out(), m2.out());
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    public void TestMultiplexerArray(){
        POWER.on();
        boolean[] ba = new boolean[]{false, true};
        MultiComponent<Switch> input1 = new MultiComponent<>(Switch.class, 8);
        MultiComponent<Switch> input2 = new MultiComponent<>(Switch.class, 8);
        MultiComponent<Switch> toggle = new MultiComponent<>(Switch.class, 8);
        input1.connectFrom(POWER);
        input2.connectFrom(POWER);
        toggle.connectFrom(POWER);

      //input1.get(0).off();//input2.get(0).off();//toggle.get(0).off();
      /*input1.get(1).off();//input2.get(1).off();*/toggle.get(1).on() ;
      /*input1.get(2).off();*/input2.get(2).on() ;//toggle.get(2).off();
      /*input1.get(3).off();*/input2.get(3).on() ;  toggle.get(3).on() ;
        input1.get(4).on() ;//input2.get(4).off();//toggle.get(4).off();
        input1.get(5).on() ;/*input2.get(5).off();*/toggle.get(5).on() ;
        input1.get(6).on() ;  input2.get(6).on() ;//toggle.get(6).off();
        input1.get(7).on() ;  input2.get(7).on() ;  toggle.get(7).on() ;

        MultiComponent<Multiplexer> m = new MultiComponent<>(Multiplexer.class, 8);
        m.connectFrom(input1);
        m.connectFrom(input2);
        m.connectFrom(toggle);
        boolean[] expected = new boolean[8];
        for (int i=0; i<8; i++){
            expected[i] = toggle.get(i).out()?input2.get(i).out():input1.get(i).out();
        }
        assertArrayEquals(expected, m.out());
    }

}
