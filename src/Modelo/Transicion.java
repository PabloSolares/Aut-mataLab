/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author pablo
 */
public class Transicion {
    private String simboloEntrada; 
    private String simboloExtrae;  
    private String estadoDestino;   
    private String simboloInserta;  

    public Transicion(String simboloEntrada, String simboloExtrae, String estadoDestino, String simboloInserta) {
        this.simboloEntrada = simboloEntrada;
        this.simboloExtrae = simboloExtrae;
        this.estadoDestino = estadoDestino;
        this.simboloInserta = simboloInserta;
    }

    public String getSimboloEntrada() {
        return simboloEntrada;
    }

    public String getSimboloExtrae() {
        return simboloExtrae;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public String getSimboloInserta() {
        return simboloInserta;
    }
    

    // getters y setters...
    @Override
    public String toString() {
        return "(" + simboloEntrada + "," + simboloExtrae + ") -> (" + estadoDestino + "," + simboloInserta + ")";
    }
}
