package proyecto;

public class Tester extends Usuario{

    private String rol;

    public Tester(String nombre, String apellido, String email, String contrasena, String pais, String rol) {
        super(nombre, apellido, email, contrasena, pais);
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public void comp() {
        System.out.println(("Tester " + getNombre() + " " + getApellido()) + " " + getEmail() + " " + getRol() + " " + getPais());
    }

}

