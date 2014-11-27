/**
 * 
 */
package exam.compiler.predictive;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jujang@sk.com
 * 
 */
public class LexicalAnalyzerTest {

  @Test
  public void test() {
    LexicalAnalyzer lex = new LexicalAnalyzer(" 9 + 2 - 5 ");

    Assert.assertEquals("9", lex.get());
    Assert.assertEquals("+", lex.get());
    Assert.assertEquals("2", lex.get());
    Assert.assertEquals("-", lex.get());
    Assert.assertEquals("5", lex.get());

    Assert.assertEquals("", lex.get());
  }

}
