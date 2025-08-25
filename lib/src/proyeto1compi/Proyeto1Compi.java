
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyeto1compi;

import Controlador.ControladorMenu;
import Modelo.ModeloMenu;
import Vista.MenuVista;

/**
 *
 * @author pablo
 */
public class Proyeto1Compi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuVista vistaMenu = new MenuVista();
        ModeloMenu modeloMenu = new ModeloMenu();
        
        ControladorMenu controladorMenu = new ControladorMenu(vistaMenu, modeloMenu);
        controladorMenu.initMenu();
    }
    
}