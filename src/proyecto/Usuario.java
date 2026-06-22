package proyecto;

import java.util.Scanner;


public class Usuario implements Comportamiento {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String pais;
    private String rol;

    public Usuario(String nombre, String apellido, String email, String contrasena, String pais, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.pais = pais;
        this.rol = rol;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTipo() {
        return "Usuario";
    }

    @Override
    public void comp() {
        System.out.println("Usuario: " + nombre + " " + apellido + " " + email);
    }


}


