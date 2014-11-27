package pe.sccu.lambda;

import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: sccu
 * Date: 2014. 4. 8.
 * Time: 23:28
 * To change this template use File | Settings | File Templates.
 */
public class Lambda {
    public static Callable<String> getCallable(Callable<String> callable) {
        return () -> "Calling: " + callable.call();
    }
}
