package Lexer;
import static Lexer.Tokens.*;
import java.lang.System;
/*class Yytoken {
    Yytoken(int numToken, String lexeme, Tokens compo, int line, int column){
        n_token = numToken;
        n_lexeme = new String(lexeme);
        n_compo = compo;
        n_line = line;
        n_column = column;
    }
    
    public int n_token;
    public String n_lexeme;
    public Tokens n_compo;
    public int n_line;
    public int n_column;
    
    public String toString(){
        return "Token #" + n_token + ", Lexeme: " + n_lexeme + ", Tipo: " +  n_compo + ", Linea: " + n_line + ", Columna: " +  n_column;
    }
}*/
%% 
%class Lexer
//%type Yytoken
%public
%unicode
%char

%function nextToken
%{
    private int contador;
%}
%init{
    contador = 0;
%init}
%eof{
%eof}
%line
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

AFD {contador++; return new Yytoken(contador, yytext(),R_AFD, yyline+1 , yycolumn);}
AP {contador++; return new Yytoken(contador, yytext(),R_AP, yyline+1, yycolumn);}
Nombre {contador++; return new Yytoken(contador, yytext(),R_NAME, yyline+1, yycolumn);}
Transiciones {contador++; return new Yytoken(contador, yytext(),R_TRANSICIONES, yyline+1, yycolumn);}
{L}({L}|{D})* {contador++; return new Yytoken(contador, yytext(), ID , yyline+1, yycolumn);}
("(-"{D}+")")|{D}+ {contador++; return new Yytoken(contador, yytext(), INTEGER , yyline+1, yycolumn);}
";" {contador++; return new Yytoken(contador, yytext(), SEMICOLON , yyline+1, yycolumn);}
"<" {contador++; return new Yytoken(contador, yytext(), LESS , yyline+1, yycolumn);}
"\"" {contador++; return new Yytoken(contador, yytext(), QUOTATION, yyline+1, yycolumn);}
">" {contador++; return new Yytoken(contador, yytext(), GREATHER , yyline+1, yycolumn);}
"|" {contador++; return new Yytoken(contador, yytext(), OR , yyline+1, yycolumn);}
"->" {contador++; return new Yytoken(contador, yytext(), PROD , yyline+1, yycolumn);}
"," {contador++; return new Yytoken(contador, yytext(), COMMA , yyline+1, yycolumn);}
"{" {contador++; return new Yytoken(contador, yytext(), KEY_OPEN , yyline+1, yycolumn);}
"}" {contador++; return new Yytoken(contador, yytext(), KEY_CLOSE , yyline+1, yycolumn);}
"=" {contador++; return new Yytoken(contador, yytext(), EQUAL , yyline+1, yycolumn);}
":" {contador++; return new Yytoken(contador, yytext(), COLON , yyline+1, yycolumn);}
"/" {contador++; return new Yytoken(contador, yytext(), CLOSE , yyline+1, yycolumn);}
"." {contador++; return new Yytoken(contador, yytext(), DOT , yyline+1, yycolumn);}
"#" {contador++; return new Yytoken(contador, yytext(), HASTAG , yyline+1, yycolumn);}
"$" {contador++; return new Yytoken(contador, yytext(), DOLLAR , yyline+1, yycolumn);}
"(" {contador++; return new Yytoken(contador, yytext(), PAR_OPEN , yyline+1, yycolumn);}
")" {contador++; return new Yytoken(contador, yytext(), PAR_CLOSE , yyline+1, yycolumn);}


(\"{L}({L}|{D})*\") {contador++; return new Yytoken(contador, yytext(), STRING , yyline+1, yycolumn);}
(" "|\n|\t|\r)+ {/*Ignore*/}
"//".* {/*Ignore*/}
<YYINITIAL>"/*" {yybegin(COMMENT);}
<COMMENT>"*" {/*Ignore*/}
<COMMENT>[^"*/"]* {/*Ignore*/}
<COMMENT>"*/" {yybegin(YYINITIAL);}

. {Modelo.ModeloMenu.ListaErrores.add(new Modelo.Errors("Lexico", yytext(), yyline+1, yycolumn));}