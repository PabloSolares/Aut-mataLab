/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lexer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author pablo
 */
public class Principal {
    public static void main(String[] args) throws Exception {
        String ruta = "C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/Lexer.flex";
        String rutacup = "C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/LexerCup.flex";
            String[] rutaS = {"-parser", "sintax", "C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/Sintax.cup"};
       
        generar(ruta,rutacup, rutaS);
    }

    public static void generar(String rutaflex, String rutacup, String[] rutaS) throws IOException, Exception{
        File archivo;
        archivo = new File(rutaflex);
        jflex.Main.generate(archivo);
        archivo = new File(rutacup);
        jflex.Main.generate(archivo);
        java_cup.Main.main(rutaS);
        Path rutaSym = Paths.get("C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/sym.java");
        if (Files.exists(rutaSym)){
            Files.delete(rutaSym);
        }
        Files.move(Paths.get("C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/sym.java"),Paths.get("C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/sym.java"));
        
        Path rutaSyn = Paths.get("C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/Sintax.java");
        if (Files.exists(rutaSyn)){
            Files.delete(rutaSyn);
        }
        
        Files.move(Paths.get("C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/sintax.java"),Paths.get("C:/Users/pablo/Documents/NetBeansProjects/Proyeto1Compi/src/Lexer/sintax.java"));
        
        
        
    }
}
