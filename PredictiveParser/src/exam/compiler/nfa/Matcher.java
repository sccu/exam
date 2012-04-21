package exam.compiler.nfa;

import java.util.HashSet;
import java.util.Set;

public class Matcher {

    private final Nfa nfa;

    private Set<State> states = new HashSet<State>();

    public Matcher(Nfa nfa) {
        this.nfa = nfa;
        states.add(this.nfa.initialState);
    }

    public boolean isFinal() {
        for (State state : states) {
            if (state.isFinal()) {
                return true;
            }
        }
        return false;
    }

    public boolean matches() {
        for (State s : states) {
            if (s.isFinal()) {
                return true;
            }
        }
        return false;
    }

    public void move(char c) {
        System.out.print(states.toString());

        Set<State> nextStates = new HashSet<State>();
        for (State s : states) {
            Set<State> dest = s.move(c);
            if (dest != null) {
                nextStates.addAll(dest);
            }
        }
        states = State.eClosure(nextStates);

        System.out.print(" -'" + c + "'->");
        System.out.println(states.toString());
    }
}