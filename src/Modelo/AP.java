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
import java.util.Stack;

/**
 *
 * @author pablo
 */
public class AP implements Automata {

    private String nombre;
    private String tipo;
    private LinkedHashSet<String> estados;
    private LinkedHashSet<String> alfabeto;
    private LinkedHashSet<String> simbolosPila;

    private String estadoInicial;
    private LinkedHashSet<String> estadosAceptacion;

    private LinkedHashMap<String, Set<Transicion>> trancisiones;

    public AP() {
        estados = new LinkedHashSet<>();
        alfabeto = new LinkedHashSet<>();
        simbolosPila = new LinkedHashSet<>();
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

    @Override
    public LinkedHashSet<String> getEstados() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados.add(estados);
    }

    @Override
    public LinkedHashSet<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto.add(alfabeto);
    }

    public LinkedHashSet<String> getSimbolosPila() {
        return simbolosPila;
    }

    public void setSimbolosPila(String simbolosPila) {
        this.simbolosPila.add(simbolosPila);
    }

    @Override
    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    @Override
    public LinkedHashSet<String> getEstadosAceptacion() {
        return estadosAceptacion;
    }

    public void setEstadosAceptacion(String estadosAceptacion) {
        this.estadosAceptacion.add(estadosAceptacion);
    }

    @Override
    public LinkedHashMap<String, Set<Transicion>> getTrancisiones() {
        return trancisiones;
    }

    public String generarDotAP() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ").append(nombre).append(" {\n");
        sb.append("  rankdir=LR;\n");
        sb.append("  node [shape=circle];\n");

        
    sb.append("  graph [labelloc=t, label=\"")
            .append("Automata: ").append(nombre).append("\\n")
            .append("Tipo: ").append(tipo).append("\\n")
            .append("Estados: ").append(estados).append("\\n")
            .append("Alfabeto: ").append(alfabeto).append("\\n")
            .append("Simbolos Pila: ").append(simbolosPila).append("\\n")
            .append("Estado Inicial: ").append(estadoInicial).append("\\n")
            .append("Estados Aceptación: ").append(estadosAceptacion).append("\\n")
            .append("Transiciones: ").append(trancisiones)
            .append("\" fontsize=18];\n"); 

        sb.append("  _ini [shape=point,label=\"\"];\n");
        sb.append("  _ini -> \"").append(estadoInicial).append("\";\n");

        for (String q : estadosAceptacion) {
            sb.append("  \"").append(q).append("\" [shape=doublecircle];\n");
        }

        for (Map.Entry<String, Set<Transicion>> entry : trancisiones.entrySet()) {
            String origen = entry.getKey();
            for (Transicion t : entry.getValue()) {
                String destino = t.getEstadoDestino();
                sb.append("  \"").append(origen).append("\" -> \"").append(destino).append("\"")
                        .append(" [label=\"")
                        .append(t.getSimboloEntrada()).append(", ")
                        .append(t.getSimboloExtrae()).append(" → ")
                        .append(t.getSimboloInserta()).append("\"];\n");
            }
        }

        sb.append("}\n");
        return sb.toString();
    }
    
    
    
    public Path generarDotAP(Path dotPath) throws IOException {

        Files.createDirectories(dotPath.getParent() == null ? Paths.get(".") : dotPath.getParent());
        Files.writeString(dotPath, generarDotAP(), StandardCharsets.UTF_8);
        return dotPath;
    }

    public void exportarGraficaAP(Path outputWithoutExt) throws IOException, InterruptedException {
        Path dot = generarDotAP(outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".dot"));
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

    @Override
    public boolean validate(String c) {
        Stack<String> pila = new Stack<>();
        String estadoA = estadoInicial;
        int i = 0;

        while (true) {
            boolean transicion = false;

            Set<Transicion> tran = trancisiones.get(estadoA);

            if (tran != null) {
                for (Transicion t : tran) {
                    String simboloEntrada = t.getSimboloEntrada();
                    String simboloExtrae = t.getSimboloExtrae();
                    String estadosDestino = t.getEstadoDestino();
                    String simboloInsertar = t.getSimboloInserta();

                    boolean coincideEntrada = simboloEntrada.equals("$") || (i < c.length() && simboloEntrada.equals(String.valueOf(c.charAt(i))));

                    boolean coincePila = simboloExtrae.equals("$") || (!pila.isEmpty() && pila.peek().equals(simboloExtrae));

                    if (coincideEntrada && coincePila) {
                        if (!simboloExtrae.equals("$") && !pila.isEmpty()) {
                            pila.pop();
                        }

                        if (!simboloInsertar.equals("$")) {
                            pila.push(simboloInsertar);
                        }

                        if (!simboloEntrada.equals("$")) {
                            i++;
                        }

                        estadoA = estadosDestino;
                        transicion = true;
                        break;
                    }

                }
            }

            if (!transicion) {
                break;
            }

            if (i == c.length() && estadosAceptacion.contains(estadoA)) {
                return true;
            }
        }
        return false;
    }

    public void setTrancisiones(String origen, String simboloEntrada, String simboloExtrae, String estadoDestino, String SimboloInserta) {
        trancisiones.putIfAbsent(origen, new LinkedHashSet<>());
        trancisiones.get(origen).add(new Transicion(simboloEntrada, simboloExtrae, estadoDestino, SimboloInserta));
    }

    @Override
    public String toString() {
        return "AP{" + "nombre=" + nombre + ", estados=" + estados + ", alfabeto=" + alfabeto + ", simbolosPila=" + simbolosPila + ", estadoInicial=" + estadoInicial + ", estadosAceptacion=" + estadosAceptacion + ", trancisiones=" + trancisiones + '}';
    }

    public String generarDotValidacion(String cadena) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ").append(nombre).append(" {\n");
        sb.append("rankdir=LR;\n");
        sb.append("node [shape=circle];\n");
        sb.append("  graph [labelloc=t, label=\"Cadena Evaluada: ")
                .append(cadena)
                .append("\", fontsize=18];\n");

        Stack<String> pila = new Stack<>();
        pila.push("#");

        String estadoActual = estadoInicial;
        int i = 0;
        int paso = 0;

        while (true) {

            sb.append("subgraph cluster").append(paso).append(" {\n");
            sb.append("style=rounded;\n");
            sb.append("label=\"Paso ").append(paso)
                    .append("\\nEntrada: ")
                    .append(i < cadena.length() ? cadena.substring(i) : "$")
                    .append("\\nPila: ").append(pila).append("\";\n");

            for (String e : estados) {
                if (e.equals(estadoActual)) {
                    sb.append("\"").append(e).append(paso).append("\"")
                            .append(" [label=\"").append(e).append("\" style=filled fillcolor=lightgreen];\n");
                } else if (estadosAceptacion.contains(e)) {
                    sb.append("\"").append(e).append(paso).append("\"")
                            .append(" [label=\"").append(e).append("\" peripheries=2];\n");
                } else {
                    sb.append("\"").append(e).append(paso).append("\" [label=\"").append(e).append("\"];\n");
                }
            }

            for (Map.Entry<String, Set<Transicion>> entry : trancisiones.entrySet()) {
                String origen = entry.getKey();
                for (Transicion t : entry.getValue()) {
                    String destino = t.getEstadoDestino();
                    sb.append("\"").append(origen).append(paso).append("\" -> \"")
                            .append(destino).append(paso).append("\" [label=\"")
                            .append(t.getSimboloEntrada()).append(", ")
                            .append(t.getSimboloExtrae()).append(" → ")
                            .append(t.getSimboloInserta()).append("\"];\n");
                }
            }
            sb.append("}\n");

            boolean hizoTransicion = false;
            Set<Transicion> posibles = trancisiones.get(estadoActual);

            if (posibles != null) {
                for (Transicion t : posibles) {
                    String simboloEntrada = t.getSimboloEntrada();
                    String simboloExtrae = t.getSimboloExtrae();
                    String estadoDestino = t.getEstadoDestino();
                    String simboloInserta = t.getSimboloInserta();

                    boolean coincideEntrada
                            = simboloEntrada.equals("$")
                            || (i < cadena.length() && simboloEntrada.equals(String.valueOf(cadena.charAt(i))));

                    boolean coincidePila
                            = simboloExtrae.equals("$")
                            || (!pila.isEmpty() && pila.peek().equals(simboloExtrae));

                    if (coincideEntrada && coincidePila) {

                        if (!simboloExtrae.equals("$") && !pila.isEmpty()) {
                            pila.pop();
                        }

                        if (!simboloInserta.equals("$")) {
                            pila.push(simboloInserta);
                        }

                        if (!simboloEntrada.equals("$")) {
                            i++;
                        }

                        estadoActual = estadoDestino;
                        hizoTransicion = true;
                        break;
                    }
                }
            }

            paso++;

            if (!hizoTransicion) {
                sb.append("subgraph cluster").append(paso).append(" {\n");
                sb.append("style=rounded;\n");
                sb.append("label=\"Cadena rechazada en paso ").append(paso).append("\";\n");
                sb.append("}\n");
                break;
            }

            if (i == cadena.length() && estadosAceptacion.contains(estadoActual) && pila.peek().equals("#")) {

                sb.append("subgraph cluster").append(paso).append(" {\n");
                sb.append("style=rounded;\n");
                sb.append("label=\"Paso ").append(paso)
                        .append("\\nEntrada: $ \\nPila: $ (aceptada)\";\n");
                for (String e : estados) {
                    if (estadosAceptacion.contains(e) && e.equals(estadoActual)) {
                        pila.pop();

                        sb.append("\"").append(e).append(paso).append("\" [label=\"")
                                .append(e).append("\" peripheries=2 style=filled fillcolor=lightgreen];\n");
                    } else if (estadosAceptacion.contains(e)) {
                        sb.append("\"").append(e).append(paso).append("\" [label=\"").append(e).append("\" peripheries=2];\n");
                    } else {
                        sb.append("\"").append(e).append(paso).append("\" [label=\"").append(e).append("\"];\n");
                    }
                }
                sb.append("}\n");
                break;
            }
        }

        sb.append("}\n");
        return sb.toString();
    }

    public Path generarDot(Path dotPath, String cadena) throws IOException {

        Files.createDirectories(dotPath.getParent() == null ? Paths.get(".") : dotPath.getParent());
        Files.writeString(dotPath, generarDotValidacion(cadena), StandardCharsets.UTF_8);
        return dotPath;
    }

    public void exportarGrafica(Path outputWithoutExt, String cadena) throws IOException, InterruptedException {
        Path dot = generarDot(outputWithoutExt.resolveSibling(outputWithoutExt.getFileName() + ".dot"), cadena);
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

}
