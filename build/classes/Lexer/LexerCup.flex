package Lexer;
import java_cup.runtime.Symbol;

%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}

%eof{
%eof}
%line
%public
%char
%column
L=[a-zA-z_]+
D=[0-9]+
S="[a-zA-z_]+"
C="[\s\S]*?"
LineTerminator = \r|\n|\r\n
EndLineComment = "//" [^\r\n]* {LineTerminator}?
%state COMMENT
%%

AFD { return new Symbol(sym.R_AFD, yyline, yycolumn, yytext());}
AP { return new Symbol(sym.R_AP, yyline, yycolumn, yytext());}
Nombre {return new Symbol(sym.R_NAME, yyline, yycolumn);}
Transiciones { return new Symbol(sym.R_TRANSICIONES, yyline, yycolumn, yytext());}
{L}({L}|{D})* { return new Symbol(sym.ID, yyline, yycolumn);}
("(-"{D}+")")|{D}+ { return new Symbol(sym.INTEGER, yyline, yycolumn, yytext());}
";" { return new Symbol(sym.SEMICOLON, yyline, yycolumn, yytext());}
"<" { return new Symbol(sym.LESS, yyline, yycolumn, yytext());}
"\"" { return new Symbol(sym.QUOTATION, yyline, yycolumn, yytext());}
">" { return new Symbol(sym.GREATHER, yyline, yycolumn,yytext());}
"|" { return new Symbol(sym.OR, yyline, yycolumn,yytext());}
"->" {return new Symbol(sym.PROD, yyline, yycolumn, yytext());}
"," { return new Symbol(sym.COMMA, yyline, yycolumn, yytext());}
"{" { return new Symbol(sym.KEY_OPEN, yyline, yycolumn, yytext());}
"}" { return new Symbol(sym.KEY_CLOSE, yyline, yycolumn, yytext());}
"=" { return new Symbol(sym.EQUAL, yyline, yycolumn, yytext());}
":" { return new Symbol(sym.COLON, yyline, yycolumn, yytext());}
"/" { return new Symbol(sym.CLOSE, yyline, yycolumn, yytext());}
"." { return new Symbol(sym.DOT, yyline, yycolumn, yytext());}
"#" { return new Symbol(sym.HASTAG, yyline, yycolumn, yytext());}
"$" { return new Symbol(sym.DOLLAR, yyline, yycolumn, yytext());}
"(" { return new Symbol(sym.PAR_OPEN, yyline, yycolumn, yytext());}
")" { return new Symbol(sym.PAR_CLOSE, yyline, yycolumn,yytext());}


(\"{L}({L}|{D})*\") { return new Symbol(sym.STRING, yyline, yycolumn, yytext());}
(" "|\n|\t|\r)+ {/*Ignore*/}
"//".* {/*Ignore*/}
<YYINITIAL>"/*" {yybegin(COMMENT);}
<COMMENT>"*" {/*Ignore*/}
<COMMENT>[^"*/"]* {/*Ignore*/}
<COMMENT>"*/" {yybegin(YYINITIAL);}

. {{ return new Symbol(sym.ERROR, yyline, yycolumn);}}
