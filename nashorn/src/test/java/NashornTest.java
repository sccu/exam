import org.junit.Test;

import javax.script.*;
import javax.xml.ws.Holder;
import java.util.Comparator;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NashornTest {
    @Test
    public void testNashorn() throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("function call() { return -1}");

        Invocable invocable = (Invocable) engine;
//        Object obj = engine.get("cmp");
//        assertNotNull(obj);
        Object sum = invocable.invokeFunction("call");
        assertEquals(-1, sum);
        Callable<Integer> cmp = invocable.getInterface(Callable.class);
        assertNotNull(cmp);
        assertEquals(-1, (int)cmp.call());

        Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        engine.put("c", c);
        engine.eval("c.compare = function(a, b) { return 1; };");
        Object c2 = engine.get("c");
        assertEquals(0, ((Comparator<Integer>) c2).compare(2, 3));
        engine.eval("print(c.compare(2, 3));");

    }

    @Test
    public void testInjection() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Holder<Comparator<String>> holder = new Holder<>();
        engine.put("holder", holder);
        engine.eval("var cmp = new java.util.Comparator({compare: function(a, b) { return -1; }});");
        engine.eval("holder.value = cmp;");
        assertNotNull(holder.value);
        assertEquals(-1, holder.value.compare("a","b"));
    }
}
