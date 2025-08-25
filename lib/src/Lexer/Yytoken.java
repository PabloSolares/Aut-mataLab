/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lexer;

/**
 *
 * @author pablo
 */
public class Yytoken {

    public Yytoken(int numToken, String lexeme, Tokens compo, int line, int column) {
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

    public String toString() {
        return "Token #" + n_token + ", Lexeme: " + n_lexeme + ", Tipo: " + n_compo + ", Linea: " + n_line + ", Columna: " + n_column;
    }

}
