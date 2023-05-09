package com.example.coche;
import java.util.Date;

public class Reserva {
    private long idReserva;
    private long idCliente;
    private long idVehiculo;
    private long idZonaAlquiler;
    private Date fechaInicio;
    private Date fechaFin;

    public long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(long idReserva) {
        this.idReserva = idReserva;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public long getIdZonaAlquiler() {
        return idZonaAlquiler;
    }

    public void setIdZonaAlquiler(long idZonaAlquiler) {
        this.idZonaAlquiler = idZonaAlquiler;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
