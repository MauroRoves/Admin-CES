package proyecto;

public class Administrador extends Usuario {

    public Administrador(String nombre, String apellido, String email, String contrasena, String pais) {
        super(nombre, apellido, email, contrasena, pais);
    }

    @Override
    public void comp() {
        System.out.println(("Administrador " + getNombre() + " " + getApellido()) + " " + getEmail() + " " + " " + getPais());
    }


}
