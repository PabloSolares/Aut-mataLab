/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Lexer.Yytoken;
import java.util.LinkedList;

/**
 *
 * @author pablo
 */
public class ModeloMenu {
    public static LinkedList<Yytoken> ListaTokens = new LinkedList<>();
    public static LinkedList<Errors> ListaErrores = new LinkedList<>();

    
    public void ingresarToken(Yytoken token ){
        ListaTokens.add(token);
    }

    public static LinkedList<Yytoken> getListaTokens() {
        return ListaTokens;
    }

    public static LinkedList<Errors> getListaErrores() {
        return ListaErrores;
    }
    
    
    

    
}
