package com.example.coche;

public class ZonaAlquiler {
    private long idZonaAlquiler;
    private String direccion;
    private String provincia;

    public long getIdZonaAlquiler() {
        return idZonaAlquiler;
    }

    public void setIdZonaAlquiler(long idZonaAlquiler) {
        this.idZonaAlquiler = idZonaAlquiler;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
