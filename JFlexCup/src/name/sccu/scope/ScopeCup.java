
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Wed Jul 11 00:21:43 KST 2012
//----------------------------------------------------

package name.sccu.scope;

import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Wed Jul 11 00:21:43 KST 2012
  */
public class ScopeCup extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public ScopeCup() {super();}

  /** Constructor which sets the default scanner. */
  public ScopeCup(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public ScopeCup(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\002\000\002\002\003\000\002\002\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\004\000\004\004\005\001\002\000\004\002\006\001" +
    "\002\000\004\002\001\001\002\000\004\002\000\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\004\000\004\002\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$ScopeCup$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$ScopeCup$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$ScopeCup$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$ScopeCup$actions {



  private final ScopeCup parser;

  /** Constructor */
  CUP$ScopeCup$actions(ScopeCup parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$ScopeCup$do_action(
    int                        CUP$ScopeCup$act_num,
    java_cup.runtime.lr_parser CUP$ScopeCup$parser,
    java.util.Stack            CUP$ScopeCup$stack,
    int                        CUP$ScopeCup$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$ScopeCup$result;

      /* select the action based on the action number */
      switch (CUP$ScopeCup$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= grammar EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$ScopeCup$stack.elementAt(CUP$ScopeCup$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$ScopeCup$stack.elementAt(CUP$ScopeCup$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$ScopeCup$stack.elementAt(CUP$ScopeCup$top-1)).value;
		RESULT = start_val;
              CUP$ScopeCup$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$ScopeCup$stack.elementAt(CUP$ScopeCup$top-1)), ((java_cup.runtime.Symbol)CUP$ScopeCup$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$ScopeCup$parser.done_parsing();
          return CUP$ScopeCup$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // grammar ::= ANY 
            {
              Object RESULT =null;
		
				
              CUP$ScopeCup$result = parser.getSymbolFactory().newSymbol("grammar",0, ((java_cup.runtime.Symbol)CUP$ScopeCup$stack.peek()), ((java_cup.runtime.Symbol)CUP$ScopeCup$stack.peek()), RESULT);
            }
          return CUP$ScopeCup$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}
