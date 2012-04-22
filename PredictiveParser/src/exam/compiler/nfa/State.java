package exam.compiler.nfa;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class State {

    private static final Set<State> EMPTY_SET = Collections.unmodifiableSet(new HashSet<State>());

    public static Set<State> eClosure(Set<State> states) {
        Set<State> visited = new HashSet<State>();
        Queue<State> unvisited = new LinkedList<State>();
        unvisited.addAll(states);
        while (!unvisited.isEmpty()) {
            State state = unvisited.poll();
            if (!visited.contains(state)) {
                visited.add(state);
                unvisited.addAll(state.eClosure());
            }
        }
        return visited;
    }

    public static void resetIds() {
        currentId = 0;
    }

    private boolean isFinal;
    private Map<Character, Set<State>> outgoings = new HashMap<Character, Set<State>>();
    private final int id;

    private static volatile int currentId = 0;

    public State(boolean isFinal) {
        this.isFinal = isFinal;

        id = currentId++;
    }

    public void addTranstion(Character c, State state) {
        Set<State> states = this.getStates(c);
        states.add(state);
    }

    private Set<State> eClosure() {
        return this.move(null);
    }

    private Set<State> getStates(Character c) {
        Set<State> states = outgoings.get(c);
        if (states == null) {
            states = new HashSet<State>();
            outgoings.put(c, states);
        }
        return states;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void merge(State other) {
        isFinal = other.isFinal;

        if (outgoings.isEmpty()) {
            outgoings = new HashMap<Character, Set<State>>(other.outgoings);
            return;
        }

        for (Entry<Character, Set<State>> entry : other.outgoings.entrySet()) {
            Set<State> states = this.getStates(entry.getKey());
            states.addAll(entry.getValue());
        }
    }

    public Set<State> move(Character c) {
        Set<State> states = outgoings.get(c);
        if (states == null) {
            return EMPTY_SET;
        }
        return states;
    }

    public void setFinal(boolean b) {
        isFinal = b;
    }

    @Override
    public String toString() {
        if (isFinal) {
            return "((" + id + "))";
        } else {
            return "(" + id + ")";
        }
    }

}
