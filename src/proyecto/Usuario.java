package proyecto;


public class Usuario implements Comportamiento {

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String pais;


    public Usuario(String nombre, String apellido, String email, String contrasena, String pais) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.pais = pais;
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


    public String getTipo() {
        return "Usuario";
    }

    @Override
    public void comp() {
        System.out.println("Usuario: " + nombre + " " + apellido + " " + email);
    }


}


