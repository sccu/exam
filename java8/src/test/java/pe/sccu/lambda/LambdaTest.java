package pe.sccu.lambda;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import junit.framework.Assert;

import org.junit.Test;
import sun.jvm.hotspot.utilities.soql.MapScriptObject;

public class LambdaTest {
    @Test
    public void testGetCaller() throws Exception {
        IntStream.range(2, 100)
                .parallel()
                .filter(n -> n%2!=0 && n%3!=0 && n%5!=0)
                .average();
        Callable<String> callable = Lambda.getCallable(() -> "Lambda!!");
        System.out.println(callable.call());
        Assert.assertEquals(callable.call(), "Calling: Lambda!!");

        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("124");

        list.stream().map(Integer::parseInt).reduce(Integer::sum).get();
        List<Integer> avg = list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println("Average: " + avg);

        List<Runnable> runners = new ArrayList<>();
        runners.add(() -> {
            System.out.println(Thread.currentThread().getId() + ": Started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getId() + ": Ended");
        });

        runners.parallelStream().forEach(r -> r.run());

    }

    static class Averager implements IntConsumer
    {
        private int total = 0;
        private int count = 0;

        public double average() {
            return count > 0 ? ((double) total) / count : 0;
        }

        public void accept(int i) {
            total += i;
            count++;
        }

        public void combine(Averager other) {
            total += other.total;
            count += other.count;
        }
    }

}
