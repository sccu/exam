/**
 * 
 */
package exam.compiler.predictive;

/**
 * @author jujang@sk.com
 * 
 */
public class PredictiveParser {
  /**
   * @param t
   */
  public static void match(String t) {
    // TODO Auto-generated method stub

  }

  private LexicalAnalyzer lexer = new LexicalAnalyzer(null);

  public void expr() {
    String t = this.lexer.get();
    PredictiveParser.match(t);
    this.rest();
  }

  /**
   * 
   */
  public void rest() {
  }
}
