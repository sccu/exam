package exam.compiler.nfa;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class NfaTest {

  @Test
  public void testNfaConcatenation() throws IOException {
    String regex = "volatile";
    Nfa nfa = Nfa.build(regex);

    Assert.assertTrue(nfa.match("volatile"));

    Assert.assertFalse(nfa.match("volatil"));
    Assert.assertFalse(nfa.match("tile"));
  }

  @Test
  public void testNfaEscape() throws IOException {
    Nfa nfa;

    nfa = Nfa.build("meet\\|met");
    Assert.assertTrue(nfa.match("meet|met"));
    Assert.assertFalse(nfa.match("meet\\|met"));

    nfa = Nfa.build("meet\\\\met");
    Assert.assertTrue(nfa.match("meet\\met"));
    Assert.assertFalse(nfa.match("meet\\\\met"));
  }

  @Test
  public void testNfaParenthesis() throws IOException {
    Nfa nfa;

    nfa = Nfa.build("if(cond){stmt}");

    Assert.assertTrue(nfa.match("ifcond{stmt}"));

    Assert.assertFalse(nfa.match("if(cond){stmt}"));

    nfa = Nfa.build("if\\(cond\\){stmt}");
    Assert.assertTrue(nfa.match("if(cond){stmt}"));
  }

  @Test
  public void testNfaParenthesis2() throws IOException {
    Nfa nfa;

    nfa = Nfa.build("ab((cd|ef)g)|hi");

    Assert.assertTrue(nfa.match("abcdg"));
    Assert.assertTrue(nfa.match("abefg"));
    Assert.assertTrue(nfa.match("hi"));

    nfa = Nfa.build("ab\\(cd\\)ef");
    Assert.assertTrue(nfa.match("ab(cd)ef"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNfaParenthesisException() throws IOException {
    Nfa.build("ab(cd");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNfaParenthesisException2() throws IOException {
    Nfa.build("ab)c(d");
  }

  @Test
  public void testNfaUnion() throws IOException {
    Nfa nfa;

    nfa = Nfa.build("meet|met");
    Assert.assertTrue(nfa.match("meet"));
    Assert.assertTrue(nfa.match("met"));

    nfa = Nfa.build("tic|tac\\||\\|toe");
    Assert.assertTrue(nfa.match("tic"));
    Assert.assertTrue(nfa.match("tac|"));
    Assert.assertTrue(nfa.match("|toe"));

    Assert.assertFalse(nfa.match("tac"));
    Assert.assertFalse(nfa.match("toe"));
  }
}
