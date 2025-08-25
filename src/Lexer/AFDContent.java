/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lexer;

import java.util.ArrayList;

/**
 *
 * @author pablo
 */
public class AFDContent {
    private ArrayList<String> estados = new ArrayList<>();
    private ArrayList<String> alfabeto = new ArrayList<>();
    private String estadoInicial;
    private ArrayList<String> estadosAceptacion = new ArrayList<>();
    private ArrayList<ArrayList<String>> transiciones = new ArrayList<>();


    public void addEstado(String e){ estados.add(e); }
    public void addAlfabeto(String a){ alfabeto.add(a); }
    public void setEstadoInicial(String e){ estadoInicial = e; }
    public void addEstadoAcept(String e){ estadosAceptacion.add(e); }
    public void addTransicion(ArrayList<String> t){ transiciones.add(t); }

    public ArrayList<String> getEstados(){ return estados; }
    public ArrayList<String> getAlfabeto(){ return alfabeto; }
    public String getEstadoInicial(){ return estadoInicial; }
    public ArrayList<String> getEstadosAceptacion(){ return estadosAceptacion; }
    public ArrayList<ArrayList<String>> getTransiciones(){ return transiciones; }
}
