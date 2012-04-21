package exam.compiler.nfa;

public class Nfa {

    public static Nfa build(String regex) {
        System.out.println("********************************************");
        System.out.println("NFA representing \"" + regex + "\"");
        System.out.println("********************************************");

        State.resetIds();

        boolean insideParenthesis = false;

        return buildRecursively(regex);
    }

    private static Nfa buildRecursively(String regex) {
        Nfa current = null;
        boolean escaped = false;
        for (Character c : regex.toCharArray()) {
            if (escaped) {
                escaped = false;
            } else {
                if (c.equals('\\')) {
                    escaped = true;
                    continue;
                }
            }

            Nfa nfa = new Nfa(c);
            if (current == null) {
                current = nfa;
            } else {
                current.concatenate(nfa);
            }
        }
        return current;
    }

    final State initialState;

    private State finalState;

    public Nfa(Character c) {
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
}
