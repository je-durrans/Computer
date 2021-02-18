package com.durrans.computer.gen4;

import com.durrans.computer.gen1.Component;
import com.durrans.computer.gen3.FullAdder;

/**
 * Special case of a MultiComponent<FullAdder> where the internal Components must connect to each other.
 * A standard MultiComponent<FullAdder> will not work as the carries would be ignored, or connected manually.
 */
public class NBitAdder extends MultiComponent<FullAdder> {

    private MultiComponent<FullAdder> adders;
    private int n;

        public NBitAdder(
                MultiComponent<? extends Component> buffer1,
                MultiComponent<? extends Component> buffer2,
                Component carryIn
        ) {

            if (buffer1.size()!=buffer2.size()){
                throw new IllegalArgumentException("Inputs must be the same length, got "+buffer1.size()+" and "+buffer2.size());
            }

            this.n = buffer1.size();
            adders = new MultiComponent<>(FullAdder.class, n);

            adders.get(n-1).connectFrom(buffer1.get(n-1));
            adders.get(n-1).connectFrom(buffer2.get(n-1));
            adders.get(n-1).connectFrom(carryIn);

            for (int i=n-2; i>=0; i--){
                adders.get(i).connectFrom(buffer1.get(i));
                adders.get(i).connectFrom(buffer2.get(i));
                adders.get(i).connectFrom(adders.get(i+1).getCarry());
            }
        }

    public MultiComponent<Component> getOuts(){
        MultiComponent<Component> outs = new MultiComponent<>();
        for (int i=0; i<n; i++){
            outs.add(adders.get(i).getSum());
        }
        return outs;
    }

    public boolean didOverflow(){
        return adders.get(0).getCarry().out();
    }


    @Override
    public boolean[] out(){

        boolean[] ret = new boolean[n];
        for (int i=0; i<n; i++){
            ret[i] = adders.get(i).out();
        }
        return ret;
    }

    @Override
    public void connectFrom(Component in) { }

    @Override
    public void connectFrom(MultiComponent in){
        for (int i=0; i<n; i++){
            adders.get(i).connectFrom(in.get(i));
        }
    }

    @Override
    public FullAdder get(int index){
        if (index<n){
            return adders.get(index);
        }
        return super.get(0);
    }

}
