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
import Modelo.AFD;
import Modelo.AP;
import Modelo.Automata;
import Modelo.Errors;
import Modelo.ModeloReportes;
import Vista.ReporteVista;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.parser;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author pablo
 */
public class ControladorMenu {

    MenuVista vista;
    ModeloMenu modelo;
    
    ReporteVista reporteVista = new ReporteVista();
    ModeloReportes modeloReportes = new ModeloReportes();
    
    ControladorReportes reportes = new ControladorReportes(modeloReportes, reporteVista, modelo);
    

    public ControladorMenu(MenuVista vista, ModeloMenu modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void initMenu() {
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        sintax.textAreaSalida = this.vista.txtSalida;
        
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("atm", "atm");
        fileChooser.setFileFilter(filtro);
        
        this.vista.btnVerReportes.addActionListener((e) -> {
            this.reportes.initReport();
        });
        
        this.vista.btnAbrir.addActionListener((e) -> {
             fileChooser.setCurrentDirectory(new File("."));

            int respuesta = fileChooser.showOpenDialog(null);
            try {
                if (respuesta == 0) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()))) {
                        String linea;
                        String texto = "";
                        while ((linea = reader.readLine()) != null ){
                            texto += linea+ "\n";
                        }

                        this.vista.txtEntrada.setText(texto);
                        reader.close();
                    }
                }
                
            } catch (Exception er) {
                JOptionPane.showMessageDialog(null, "Ningun Archivo fue seleccionado. Intenta nuevamente", "Archivo No Seleccionado", JOptionPane.WARNING_MESSAGE);
            }
        
        });
        
        this.vista.btnGuardarArchivo.addActionListener((e) -> {
             
            String contenido = this.vista.txtEntrada.getText();
            int op = fileChooser.showSaveDialog(null);
            
            if (op == 0){
                File fileGuardar = fileChooser.getSelectedFile();
                if (!fileGuardar.getName().toLowerCase().endsWith(".atm")){
                    fileGuardar = new File(fileGuardar.getAbsolutePath() + ".atm");
                }
                try (FileWriter writer = new FileWriter(fileGuardar)){
                    writer.write(contenido);
                    JOptionPane.showMessageDialog(null, "Exito Archivo Guardado con exito", "Archivo Guardado con exito", JOptionPane.INFORMATION_MESSAGE);

                } catch (IOException x){
                     JOptionPane.showMessageDialog(null, "Error", "Error al guardar el archivo", JOptionPane.ERROR_MESSAGE);
                }
            } else if(op == JFileChooser.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Ningun Archivo fue seleccionado. Intenta nuevamente", "Archivo No Seleccionado", JOptionPane.WARNING_MESSAGE);
            } else if(op == JFileChooser.ERROR_OPTION){
                JOptionPane.showMessageDialog(null, "Error", "Error al mostrar el selector de archivos", JOptionPane.ERROR_MESSAGE);
            } 
        });
        
        
        this.vista.btnProcesar.addActionListener((e) -> {
            sintax.listaAutomatas.clear();
            ModeloMenu.ListaErrores.clear();
            ModeloMenu.ListaTokens.clear();

            
            String input = this.vista.txtEntrada.getText();
            this.vista.txtSalida.setText("");
            File file = new File("archivo.txt");
            LexerCup lexerCup =  new LexerCup(new StringReader(input));
            sintax s = new sintax(lexerCup);
            try {
                s.parse();  
                
                
                //System.out.println("TODO BIEN");
                ArrayList<Automata> automatas = s.getlistaAutomatas();
                
                /*
                for (Automata automata : automatas) {
                    System.out.println(automata.toString());
                }
                */
                
            } catch (Exception ex) {
                StackTraceElement element = ex.getStackTrace()[0];
                System.out.println("Error: " + ex.getMessage());
                System.out.println("Clase: " + element.getClassName());
                System.out.println("Método: " + element.getMethodName());
                System.out.println("Línea: " + element.getLineNumber());

        
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
                        modelo.ingresarToken(token);
                    }
                } while (token != null);
                
                /*
                for (Yytoken tokenL : modelo.getListaTokens()) {
                    //System.out.println(tokenL.toString());
                }
*/
                for (Errors errs : modelo.getListaErrores()) {
                    System.out.println(errs.toString());
                }
               
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
