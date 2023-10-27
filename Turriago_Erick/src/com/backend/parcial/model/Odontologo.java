package com.backend.parcial.model;

public class Odontologo {

    private Integer id;
    private Integer numMatricula;
    private String nombre;
    private  String apellido;

    public Odontologo(Integer id, Integer numMatricula, String nombre, String apellido) {
        this.id = id;
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Integer numMatricula, String nombre, String apellido) {
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(Integer numMatricula) {
        this.numMatricula = numMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", numMatricula=" + numMatricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
