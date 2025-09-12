/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 *
 * @author pablo
 */
public interface Automata {
   
    public String getNombre(); 
    public String getTipo();
    public LinkedHashSet<String> getEstados();
    public String getEstadoInicial();
    public LinkedHashSet<String> getAlfabeto();
    public LinkedHashSet<String> getEstadosAceptacion();
    public Object getTrancisiones();
    public boolean validate(String a);

    
  

}
