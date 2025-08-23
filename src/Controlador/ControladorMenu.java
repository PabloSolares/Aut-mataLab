/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ModeloMenu;
import Vista.MenuVista;
import Lexer.Lexer;
import Lexer.LexerCup;
import Lexer.Yytoken;
import Lexer.sintax;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

/**
 *
 * @author pablo
 */
public class ControladorMenu {

    MenuVista vista;
    ModeloMenu modelo;

    public ControladorMenu(MenuVista vista, ModeloMenu modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void initMenu() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);

        this.vista.btnProcesar.addActionListener((e) -> {
            String input = this.vista.txtEntrada.getText();
            File file = new File("archivo.txt");
            sintax s = new sintax(new LexerCup(new StringReader(input)));
            try {
                Symbol result = s.parse();
                System.out.println("TODO BIEN");   
                
            } catch (Exception ex) {
                Symbol sym = s.getS();
                System.out.println("Error : " + sym.value + "Columna:" + (sym.right + 1) + "Linea" + (sym.left + 1) );
            }
            try {
                PrintWriter write = new PrintWriter(file);
                write.print(input);
                write.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Reader reader = new BufferedReader(new FileReader("archivo.txt"));
                Lexer lexer = new Lexer(reader);
                Yytoken token = null;
                do {
                    try {
                        token = lexer.nextToken();
                    } catch (IOException ex) {
                        Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (token != null) {
                        System.out.println(token.n_lexeme);
                    }
                } while (token != null);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
