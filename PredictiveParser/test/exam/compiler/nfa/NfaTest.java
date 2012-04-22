package exam.compiler.nfa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class NfaTest {

    @Test
    public void testNfaConcatenation() throws IOException {
        String regex = "volatile";
        Nfa nfa = Nfa.build(regex);

        assertTrue(nfa.match("volatile"));

        assertFalse(nfa.match("volatil"));
        assertFalse(nfa.match("tile"));
    }

    @Test
    public void testNfaEscape() throws IOException {
        Nfa nfa;

        nfa = Nfa.build("meet\\|met");
        assertTrue(nfa.match("meet|met"));
        assertFalse(nfa.match("meet\\|met"));

        nfa = Nfa.build("meet\\\\met");
        assertTrue(nfa.match("meet\\met"));
        assertFalse(nfa.match("meet\\\\met"));
    }

    @Test
    public void testNfaUnion() throws IOException {
        Nfa nfa;

        nfa = Nfa.build("meet|met");
        assertTrue(nfa.match("meet"));
        assertTrue(nfa.match("met"));

        nfa = Nfa.build("tic|tac|toe");
        assertTrue(nfa.match("tic"));
        assertTrue(nfa.match("tac"));
        assertTrue(nfa.match("toe"));

    }
}
