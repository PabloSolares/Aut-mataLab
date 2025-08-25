/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author pablo
 */
public class Errors {
    private String tipo;
    private String lexeme;
    private int fila;
   
    private int columna;

    public Errors(String tipo, String lexeme, int fila, int columna) {
        this.tipo = tipo;
        this.lexeme = lexeme;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
       
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return "Errors{" + "tipo=" + tipo + ", lexeme=" + lexeme + ", fila=" + fila + ", columna=" + columna + '}';
    }
    
    
    
}
