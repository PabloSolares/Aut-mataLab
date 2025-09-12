/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pablo
 */
public class AFD implements Automata{
    private String nombre;
    private String tipo;
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
    
    @Override
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
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

    @Override
    public LinkedHashSet<String> getEstados() {
        return estados;
    }

    @Override
    public LinkedHashSet<String> getAlfabeto() {
        return alfabeto;
    }

    @Override
    public LinkedHashSet<String> getEstadosAceptacion() {
        return estadosAceptacion;
    }
    String tran = "";
    @Override
    public String getTrancisiones() {
        trancisiones.forEach((t, u) -> {
            tran += (t + " -> ");
            u.forEach((k, v) -> {
                tran += k + ", " + v ;
                if (k != null ){
                    tran += " | ";
                }
            });
            tran += " \n \t \t";
        });
         
        
        return tran;
    }
    @Override
    public boolean validate(String c){
        String i = estadoInicial;
        int x = 0;
        while( x <= c.length() - 1 ){
            String ch = String.valueOf(c.charAt(x));
            
            Map<String, String> trans = trancisiones.get(i);
            
            while(x + 1 < c.length() &&  (trans == null || !trans.containsKey(ch))){
                x++;
                ch += String.valueOf(c.charAt(x));    
            }
            if (trans == null || !trans.containsKey(ch)) {
                return false;  
            }
            
            i = trans.get(ch);
            
            x+=1;
            
        }
        
  
         return estadosAceptacion.contains(i);
    }

    @Override
    public String toString() {
        return "AFD{" + "nombre=" + nombre + ", estados=" + estados + ", alfabeto=" + alfabeto + ", estadoInicial=" + estadoInicial + ", estadosAceptacion=" + estadosAceptacion + ", trancisiones=" + trancisiones + '}';
    }
    
    private Map<String, Map<String, List<String>>> aristasGraficar(){
        Map<String, Map<String, List<String>>> aristas = new LinkedHashMap<>();
        for(var entrada : trancisiones.entrySet()){
            String origen = entrada.getKey();
            Map<String, String> destinos = entrada.getValue();
            
            if(!aristas.containsKey(origen)) aristas.put(origen, new LinkedHashMap<>());
            Map<String, List<String>> destinosOrigen = aristas.get(origen);
            for(var entradaE : destinos.entrySet()){
                String etiqueta = entradaE.getKey();
                String destino = entradaE.getValue();
                
                if(!destinosOrigen.containsKey(destino)){
                    destinosOrigen.put(destino, new ArrayList<>());
                }
                
                destinosOrigen.get(destino).add(etiqueta);
            }
                            
        }
        return aristas;
    }
    
    
    public String dotValidacion(String cadena) {
        StringBuilder sb = new StringBuilder();

        sb.append("digraph ").append(nombre).append(" {\n");
        sb.append("  rankdir=LR;\n");
        sb.append("  node [shape=circle];\n");
        sb.append("  graph [labelloc=t, label=\"Cadena Evaluada: ")
          .append(cadena)
          .append("\", fontsize=18];\n");

        
        sb.append("  _ini [shape=point,label=\"\"];\n");

        String i = estadoInicial;
        String prevNodeId = "n0";  
        int step = 0;

        
        sb.append("  ").append(prevNodeId)
          .append(" [label=\"").append(i).append("\"];\n");
        sb.append("  _ini -> ").append(prevNodeId).append(";\n");

        int x = 0;
        boolean valido = true;

        while (x <= cadena.length() - 1) {
            String ch = String.valueOf(cadena.charAt(x));
            Map<String, String> trans = trancisiones.get(i);

            
            while (x + 1 < cadena.length() && (trans == null || !trans.containsKey(ch))) {
                x++;
                ch += String.valueOf(cadena.charAt(x));
            }

            if (trans == null || !trans.containsKey(ch)) {
                valido = false; 
                break;
            }

            
            i = trans.get(ch);
            step++;
            String currNodeId = "n" + step;

            
            sb.append("  ").append(currNodeId)
              .append(" [label=\"").append(i).append("\"];\n");

      
            sb.append("  ").append(prevNodeId).append(" -> ")
              .append(currNodeId)
              .append(" [label=\"").append(ch).append("\"];\n");

            prevNodeId = currNodeId;
            x++;
        }

       
        if (valido && estadosAceptacion.contains(i)) {
            sb.append("  ").append(prevNodeId)
              .append(" [label=\"").append(i).append("\", shape=doublecircle, color=green];\n");
        } else {
            sb.append("  ").append(prevNodeId)
              .append(" [label=\"").append(i).append("\", color=red];\n");
        }

        sb.append("}\n");
        return sb.toString();
    }
    
    public String ConvertirADot(){
        StringBuilder sb = new StringBuilder();

        sb.append("digraph ").append(nombre).append(" {\n");
        sb.append("rankdir=LR;\n");
        sb.append("node [shape=circle];\n");
        sb.append("  graph [labelloc=t, label=\"")
      .append("Automata: ").append(nombre).append("\\n")
      .append("Tipo: ").append(tipo != null ? tipo : "No definido").append("\\n")
      .append("Estados: ").append(estados).append("\\n")
      .append("Alfabeto: ").append(alfabeto).append("\\n")
      .append("Estado Inicial: ").append(estadoInicial).append("\\n")
      .append("Estados de Aceptación: ").append(estadosAceptacion).append("\\n")
      .append("Transiciones: ").append(this.getTrancisiones()).append("\"")
      .append(", fontsize=18];\n");

        sb.append("  _ini [shape=point,label=\"\"];\n");
        sb.append(" _ini -> \"").append(estadoInicial).append("\";\n");

      
        for (String q : estadosAceptacion) {
         sb.append("  \"").append(q).append("\" [shape=doublecircle];\n");
        }


        var edges = aristasGraficar();

        for (var origen : edges.keySet()) {
            for ( var destino : edges.get(origen).keySet()) {

                List<String> simbolos = edges.get(origen).get(destino);

                String label = String.join(",", simbolos);
                sb.append("  \"").append(origen).append("\" -> \"").append(destino).append("\" [label=\"")
                        .append(label).append("\"];\n");
            }

        }
        sb.append("}\n");
        return sb.toString();
    }
    
    public Path generarDot(Path dotPath) throws IOException{

        Files.createDirectories(dotPath.getParent() == null ? Paths.get(".") : dotPath.getParent());
        Files.writeString(dotPath,ConvertirADot(), StandardCharsets.UTF_8);
        return dotPath;
    }


    public void exportarGrafica(Path outputWithoutExt)throws IOException, InterruptedException{
        Path dot = generarDot(outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".dot"));
        Process p = new ProcessBuilder("dot", "-Tpng", dot.toString(), "-o",
                outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".png").toString())
                .redirectErrorStream(true)
                .start();
        int code = p.waitFor();
        if (code != 0) {
            try (var r = p.inputReader()) {
                System.err.println("Graphviz 'dot' fallo con código " + code + ". Log:");

            }
        } else {
            System.out.println("Graficado: " + outputWithoutExt + ".png");
        }
    }
    
    public void exportarGraficaValidar(Path outputWithoutExt,String cadena)throws IOException, InterruptedException{
        Path dot = generarDotValidar(outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".dot"),  cadena);
        Process p = new ProcessBuilder("dot", "-Tpng", dot.toString(), "-o",
                outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".png").toString())
                .redirectErrorStream(true)
                .start();
        int code = p.waitFor();
        if (code != 0) {
            try (var r = p.inputReader()) {
                System.err.println("Graphviz 'dot' fallo con código " + code + ". Log:");

            }
        } else {
            System.out.println("Graficado: " + outputWithoutExt + ".png");
        }
    }
    
    public Path generarDotValidar(Path dotPath, String cadena) throws IOException{

        Files.createDirectories(dotPath.getParent() == null ? Paths.get(".") : dotPath.getParent());
        Files.writeString(dotPath,dotValidacion(cadena), StandardCharsets.UTF_8);
        return dotPath;
    }
    

}
