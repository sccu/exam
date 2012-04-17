/**
 * 
 */
package exam.compiler.predictive;

import java.nio.CharBuffer;

/**
 * @author jujang@sk.com
 * 
 */
public class LexicalAnalyzer {

  private CharBuffer buffer;

  /**
   * @param string
   */
  public LexicalAnalyzer(String string) {
    this.buffer = CharBuffer.wrap(string);
  }

  /**
   * @return
   */
  public String get() {
    char c;
    while (Character.isWhitespace(c = this.buffer.get())) {
      if (!this.buffer.hasRemaining()) {
        return "";
      }
    }

    if (Character.isDigit(c)) {
      return String.valueOf(c - '0');
    } else {
      return String.valueOf(c);
    }
  }
}
