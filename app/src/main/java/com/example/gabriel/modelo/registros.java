package com.example.gabriel.modelo;

import androidx.annotation.NonNull;

public class registros {

    private int id_registros;
    private String nombre;
    private String telefono;
    private String direccion;
    private String descripcion;
    private String valor;
    private String fecha;
    private int estado;
    private String fechaingreso;

    public registros() {
        this.id_registros = 0;
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
        this.descripcion = "";
        this.valor = "";
        this.fecha = "";
        this.estado = 1;
        this.fechaingreso = "";
    }

    public registros(int id_registros, String nombre, String telefono, String direccion, String descripcion, String valor, String fecha, int estado, String fechaingreso) {
        this.id_registros = id_registros;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.valor = valor;
        this.fecha = fecha;
        this.estado = estado;
        this.fechaingreso = fechaingreso;
    }


    public int getId_registros() {
        return id_registros;
    }

    public void setId_registros(int id_registros) {
        this.id_registros = id_registros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    @Override
    public String toString() {
        return "N#: " + this.id_registros + "\n" +
                "Nombre: " + "\n" + this.nombre + "\n" +
                "Telefono: " + "\n" + this.telefono + "\n" +
                "Direcciön: " + "\n" + this.direccion + "\n" +
                "Descripción: " + "\n" + this.descripcion + "\n" +
                "Valor: " + "\n" + this.valor + "\n" +
                "Fecha entrega: " + "\n" + this.fecha + "\n" +
                "Estado: " + this.estado + "\n" +
                "Fecha de registro: " + "\n" + this.fechaingreso;
        //"Fecha ingreso: " + "\n" + this.fechaingreso;
    }
}
