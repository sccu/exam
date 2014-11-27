
package name.sccu.scope;

import java_cup.runtime.*;
import java.io.IOException;

import name.sccu.scope.ScopeSym;
import static name.sccu.scope.ScopeSym.*;

%%

%class ScopeLex

%unicode
%line
%column

// %public
%final
// %abstract
%debug

%cupsym name.sccu.scope.ScopeSym
%cup
%cupdebug

%init{
	// TODO: code that goes to constructor
%init}

%{
	private Symbol sym(int type)
	{
	  //System.out.println(yytext());
		return sym(type, yytext());
	}

	private Symbol sym(int type, Object value)
	{
	   print(value);
		return new Symbol(type, yyline, yycolumn, value);
	}

	private void error()
	throws IOException
	{
		throw new IOException("illegal text at line = "+yyline+", column = "+yycolumn+", text = '"+yytext()+"'");
	}
	
	private void print(Object val) {
	  //System.out.println("DEBUG:" + val);
	}
%}

DIGIT   = [0-9]
NUM     = {DIGIT}+
ALPHA   = [a-zA-Z_]
PLUS    = "+"
SEMI    = ";"
DOT     = "."
PKG     = "package"
ID      = {ALPHA}({ALPHA}|{DIGIT})*
WS      = [\n\r\t\b ]

%%
<YYINITIAL> {WS}+   { }
"{"     { return sym(LBRACE); }
"}"     { return sym(RBRACE); }
"[(]"     { return sym(LPAREN); }
"[)]"     { return sym(RPAREN); }
"public" { return sym(PUBLIC); }
"private" { return sym(PRIVATE); }
"class" { return sym(CLASS); }
"static" { return sym(STATIC); }
{PKG}   { return sym(PKG); }
{ID}    { return sym(ID); }
{DOT}   { return sym(DOT); }
{NUM}   { return sym(NUM); }
{PLUS}  { return sym(PLUS); }
{SEMI}  { return sym(SEMI); }
"="     { return sym(EQUAL); }

 <YYINITIAL> . {
    System.out.println("ERROR: Illegal character: <" + yytext() + ">");
 }

