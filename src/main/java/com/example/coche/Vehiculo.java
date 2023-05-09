package com.example.coche;


public class Vehiculo {
    private long idVehiculo;
    private String nombreModelo;
    private String matricula;
    private String marca;
    private long precioSemana;
    private long capacidadSitio;

    private long idTipo;

    public long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(long idTipo) {
        this.idTipo = idTipo;
    }

    public long getIdVehiculo() {
        return idVehiculo;
    }


    public void setIdVehiculo(long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getPrecioSemana() {
        return precioSemana;
    }

    public void setPrecioSemana(long precioSemana) {
        this.precioSemana = precioSemana;
    }

    public long getCapacidadSitio() {
        return capacidadSitio;
    }

    public void setCapacidadSitio(long capacidadSitio) {
        this.capacidadSitio = capacidadSitio;
    }
}
