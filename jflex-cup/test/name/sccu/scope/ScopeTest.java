package name.sccu.scope;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

public class ScopeTest {

  @Test
  public void testParser() throws Exception {
    String expr = "3+4;";
    ScopeCup cup = new ScopeCup(new ScopeLex(new ByteArrayInputStream(expr.getBytes())));
    cup.debug_parse();
  }

  @Test
  public void testParseSample() throws Exception {
    InputStream is = new FileInputStream("sample/Sample.java");
    ScopeCup cup = new ScopeCup(new ScopeLex(is));
    cup.parse();
  }

  @Test
  public void testScanner() throws Exception {
    String expr = "34+6;";
    ScopeLex scanner = new ScopeLex(new ByteArrayInputStream(expr.getBytes()));

    scanner.debug_next_token();
    scanner.debug_next_token();
    scanner.debug_next_token();
    scanner.debug_next_token();
    scanner.debug_next_token();
  }
}
