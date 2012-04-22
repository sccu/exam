package exam.compiler.nfa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class Nfa {

    public static Nfa build(String regex) throws IOException {
        System.out.println("********************************************");
        System.out.println("NFA representing \"" + regex + "\"");
        System.out.println("********************************************");

        State.resetIds();

        PushbackInputStream is = new PushbackInputStream(new ByteArrayInputStream(regex.getBytes()));
        return buildInternally(is);
    }

    private static Nfa buildInternally(PushbackInputStream is) throws IOException {
        Nfa current = null;
        boolean escaped = false;
        int c;
        while ((c = is.read()) != -1) {
            if (escaped) {
                escaped = false;
            } else {
                if (c == '\\') {
                    escaped = true;
                    continue;
                }
            }

            is.unread(c);
            Nfa nfa = buildUntilUnion(is);
            if (current == null) {
                current = nfa;
            } else {
                current.union(nfa);
            }

        }
        return current;
    }

    private static Nfa buildUntilUnion(PushbackInputStream is) throws IOException {
        Nfa current = null;
        boolean escaped = false;
        int c;
        while ((c = is.read()) != -1) {
            if (escaped) {
                escaped = false;
            } else {
                if (c == '\\') {
                    escaped = true;
                    continue;
                } else if (c == '|') {
                    break;
                }
            }

            Nfa nfa = new Nfa((char) c);
            if (current == null) {
                current = nfa;
            } else {
                current.concatenate(nfa);
            }
        }
        return current;
    }

    State initialState;

    private State finalState;

    private Nfa(Character c) {
        initialState = new State(false);
        finalState = new State(true);
        initialState.addTranstion(c, finalState);
    }

    private void concatenate(Nfa nfa) {
        finalState.merge(nfa.initialState);
        finalState = nfa.finalState;
    }

    public boolean match(String str) {
        System.out.println("Matching Test for \"" + str + "\"");
        Matcher m = new Matcher(this);
        for (char c : str.toCharArray()) {
            m.move(c);
        }
        if (m.isFinal()) {
            System.out.println("Matched!!!");
        } else {
            System.out.println("NOT Matched!!!");
        }
        System.out.println();
        return m.isFinal();
    }

    private void union(Nfa other) {
        State init = new State(false);
        State fin = new State(true);

        init.addTranstion(null, initialState);
        init.addTranstion(null, other.initialState);

        finalState.addTranstion(null, fin);
        other.finalState.addTranstion(null, fin);

        finalState.setFinal(false);
        other.finalState.setFinal(false);

        initialState = init;
        finalState = fin;
    }
}
