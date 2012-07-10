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
    Nfa result = Nfa.buildInternally(is);
    if (is.read() == -1) {
      return result;
    } else {
      throw new IllegalArgumentException("Invalid Regular Expression:\"" + regex + "\"");
    }
  }

  private static Nfa buildInternally(PushbackInputStream is) throws IOException {
    Nfa current = null;
    while (true) {
      int c = is.read();
      if (c == -1) {
        break;
      } else if (c == ')') {
        is.unread(c);
        break;
      }

      is.unread(c);
      Nfa nfa = Nfa.buildUntilUnion(is);
      if (current == null) {
        current = nfa;
      } else {
        current.union(nfa);
      }
    }
    return current;
  }

  private static Nfa buildUntilUnion(PushbackInputStream is) throws IOException {
    Nfa current = new Nfa();
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
        } else if (c == '(') {
          Nfa nfa = Nfa.buildInternally(is);
          if (is.read() != ')') {
            throw new IllegalArgumentException("Parentheses are not matched.");
          }
          current.concatenate(nfa);
          continue;
        } else if (c == ')') {
          is.unread(c);
          break;
        }
      }

      current.concatenate((char) c);
    }
    return current;
  }

  State initialState;

  private State finalState;

  /**
   * 
   */
  private Nfa() {
    this.initialState = this.finalState = new State(true);
  }

  private Nfa(Character c) {
    this.initialState = new State(false);
    this.finalState = new State(true);
    this.initialState.addTranstion(c, this.finalState);
  }

  /**
   * @param c
   */
  private void concatenate(char c) {
    State state = new State(true);
    this.finalState.addTranstion(c, state);
    this.finalState.setFinal(false);
    this.finalState = state;
  }

  private void concatenate(Nfa nfa) {
    this.finalState.merge(nfa.initialState);
    this.finalState = nfa.finalState;
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

    init.addTranstion(null, this.initialState);
    init.addTranstion(null, other.initialState);

    this.finalState.addTranstion(null, fin);
    other.finalState.addTranstion(null, fin);

    this.finalState.setFinal(false);
    other.finalState.setFinal(false);

    this.initialState = init;
    this.finalState = fin;
  }
}
