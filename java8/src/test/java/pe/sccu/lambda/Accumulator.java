package pe.sccu.lambda;

import java.util.function.IntConsumer;

/**
* Created with IntelliJ IDEA.
* User: sccu
* Date: 2014. 4. 9.
* Time: 10:04
* To change this template use File | Settings | File Templates.
*/
public class Accumulator {
    private int accumul;

    public void add(int val) {
        accumul += val;
    }

    public void combine(Accumulator o) {
        accumul += o.accumul;
    }

    public int get() {
        return accumul;
    }

    public void accept(int val1, int val2) {
        add(val2);
    }
}
