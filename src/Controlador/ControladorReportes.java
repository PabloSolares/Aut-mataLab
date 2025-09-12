/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Lexer.Yytoken;
import Modelo.Errors;
import Modelo.ModeloMenu;
import Modelo.ModeloReportes;
import Vista.ReporteVista;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class ControladorReportes {
    ModeloReportes modelo;
    ReporteVista vista;
    ModeloMenu modeloMenu;

    public ControladorReportes(ModeloReportes modelo, ReporteVista vista, ModeloMenu modeloMenu) {
        this.modelo = modelo;
        this.vista = vista;
        this.modeloMenu = modeloMenu;
    }

   
    
    public void initReport(){
        this.vista.setLocationRelativeTo(null);
        this.vista.setVisible(true);
        DefaultTableModel tablaTokens = (DefaultTableModel) this.vista.tablaTokens.getModel();
        DefaultTableModel tablaErrores = (DefaultTableModel) this.vista.tablaErrores.getModel();
        tablaTokens.getDataVector().removeAllElements();
        tablaErrores.getDataVector().removeAllElements();

        for (Yytoken tokenL : modeloMenu.getListaTokens()) {
            tablaTokens.addRow(new Object[]{tokenL.n_token,tokenL.n_lexeme, tokenL.n_compo ,tokenL.n_line,tokenL.n_column });
        }
        int count = 1;
        for (Errors errs : modeloMenu.getListaErrores()) {
            
            tablaErrores.addRow(new Object[]{String.valueOf(count++) , errs.getTipo(), errs.getLexeme(),errs.getFila(),errs.getColumna()});
          
        }

    }
}
