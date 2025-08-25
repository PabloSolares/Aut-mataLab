/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pablo
 */
public class AFD {
    private String nombre;
    private LinkedHashSet<String> estados;
    private LinkedHashSet<String> alfabeto;
    private String estadoInicial;
    private LinkedHashSet<String> estadosAceptacion;
    private LinkedHashMap<String, Map<String, String>> trancisiones;
    
    public AFD() {
       estados = new LinkedHashSet<>();
       alfabeto = new LinkedHashSet<>();
       estadosAceptacion = new LinkedHashSet<>();
       trancisiones = new LinkedHashMap<>();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addEstado(String e){
        estados.add(e);
    }
    
    public void addAlfabeto(String a){
        alfabeto.add(a);
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }
    
    public void addEstadoAcept(String ea){
        estadosAceptacion.add(ea);
    }
    
    public void addTransicion(String origen, String simbolo, String destino){
        trancisiones.putIfAbsent(origen, new HashMap<>());
        
        if(trancisiones.get(origen).containsKey(simbolo)){
            ModeloMenu.ListaErrores.add(new Modelo.Errors("Sintactico - Recuperado", "Transicion duplicada desde Estado " + origen + "con simobolo " + simbolo , -1,-1 ));
        } else {
            trancisiones.get(origen).put(simbolo, destino);
        }
       
    }

    public Set<String> getEstados() {
        return estados;
    }

    public Set<String> getAlfabeto() {
        return alfabeto;
    }

    public Set<String> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public Map<String, Map<String, String>> getTrancisiones() {
        return trancisiones;
    }

    @Override
    public String toString() {
        return "AFD{" + "nombre=" + nombre + ", estados=" + estados + ", alfabeto=" + alfabeto + ", estadoInicial=" + estadoInicial + ", estadosAceptacion=" + estadosAceptacion + ", trancisiones=" + trancisiones + '}';
    }
    
    
    
    
    
    
    

}
