
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

%cupsym name.sccu.scope.ScopeSym
%cup
%cupdebug

%init{
	// TODO: code that goes to constructor
%init}

%{
	private Symbol sym(int type)
	{
	  System.out.println(yytext());
		return sym(type, yytext());
	}

	private Symbol sym(int type, Object value)
	{
		return new Symbol(type, yyline, yycolumn, value);
	}

	private void error()
	throws IOException
	{
		throw new IOException("illegal text at line = "+yyline+", column = "+yycolumn+", text = '"+yytext()+"'");
	}
%}

num     = [0-9]+
plus    = "+"
SEMI    = ";"

%%

{num}   { return sym(num, Double.parseDouble(yytext())); }
{plus}  { return sym(plus); }
{SEMI}  { return sym(SEMI); }

