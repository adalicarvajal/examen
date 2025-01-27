package com.espe.micro_taller.model;

public class Participantes {

    private Long id;            // Identificador único del participante
    private String nombre;      // Nombre del participante
    private String apellido;    // Apellido del participante
    private String email;       // Email del participante
    private String telefono;    // Teléfono del participante

    // Constructor vacío
    public Participantes() {
    }

    // Constructor con todos los campos
    public Participantes(Long id, String nombre, String apellido, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Participantes{id=" + id + ", nombre='" + nombre + "', apellido='" + apellido + "', email='" + email + "', telefono='" + telefono + "'}";
    }
}
