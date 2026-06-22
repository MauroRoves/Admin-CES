package proyecto;

public class Administrador extends Usuario {

    public Administrador(String nombre, String apellido, String email, String contrasena, String pais, String rol) {
        super(nombre, apellido, email, contrasena, pais, rol);
    }

    @Override
    public void comp() {
        System.out.println(("Administrador " + getNombre() + " " + getApellido()) + " " + getEmail() + " " + getRol() + " " + getPais());
    }


}
