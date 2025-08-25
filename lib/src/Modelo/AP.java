/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pablo
 */
public class AP {
    private String nombre;
    private LinkedHashSet<String> estados;
    private LinkedHashSet<String> alfabeto;
    private LinkedHashSet<String> simbolosPila;

    private String estadoInicial;
    private LinkedHashSet<String> estadosAceptacion;
    
    private LinkedHashMap<String, Map<String, String>> trancisiones;

    public AP() {
        estados = new LinkedHashSet<>();
        alfabeto = new LinkedHashSet<>();
        simbolosPila = new LinkedHashSet<>();
        estadosAceptacion = new LinkedHashSet<>();
        trancisiones = new LinkedHashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedHashSet<String> getEstados() {
        return estados;
    }

    public void setEstados(LinkedHashSet<String> estados) {
        this.estados = estados;
    }

    public LinkedHashSet<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(LinkedHashSet<String> alfabeto) {
        this.alfabeto = alfabeto;
    }

    public LinkedHashSet<String> getSimbolosPila() {
        return simbolosPila;
    }

    public void setSimbolosPila(LinkedHashSet<String> simbolosPila) {
        this.simbolosPila = simbolosPila;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public LinkedHashSet<String> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public void setEstadosAceptacion(LinkedHashSet<String> estadosAceptacion) {
        this.estadosAceptacion = estadosAceptacion;
    }

    public LinkedHashMap<String, Map<String, String>> getTrancisiones() {
        return trancisiones;
    }

    public void setTrancisiones(LinkedHashMap<String, Map<String, String>> trancisiones) {
        this.trancisiones = trancisiones;
    }

    @Override
    public String toString() {
        return "AP{" + "nombre=" + nombre + ", estados=" + estados + ", alfabeto=" + alfabeto + ", simbolosPila=" + simbolosPila + ", estadoInicial=" + estadoInicial + ", estadosAceptacion=" + estadosAceptacion + ", trancisiones=" + trancisiones + '}';
    }
    
    
    
}
