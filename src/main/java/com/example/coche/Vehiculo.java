package com.example.coche;
public class Vehiculo {
    private long id_vehiculo;
    private String nombre_modelo;
    private String matricula;
    private String marca;
    private long precio_semana;
    private long capacidad_sitio;
    private long id_tipo_vechiculo;
    public long getId_tipo_vechiculo() {
        return id_tipo_vechiculo;
    }
    public void setId_tipo_vechiculo(long id_tipo_vechiculo) {
        this.id_tipo_vechiculo = id_tipo_vechiculo;
    }

    public long getId_vehiculo() {
        return id_vehiculo;
    }
    public void setId_vehiculo(long id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getNombre_modelo() {
        return nombre_modelo;
    }

    public void setNombre_modelo(String nombre_modelo) {
        this.nombre_modelo = nombre_modelo;
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

    public long getPrecio_semana() {
        return precio_semana;
    }

    public void setPrecio_semana(long precio_semana) {
        this.precio_semana = precio_semana;
    }

    public long getCapacidad_sitio() {
        return capacidad_sitio;
    }

    public void setCapacidad_sitio(long capacidad_sitio) {
        this.capacidad_sitio = capacidad_sitio;
    }
}
