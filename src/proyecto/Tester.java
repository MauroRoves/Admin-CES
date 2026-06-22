package proyecto;

public class Tester extends Usuario{

    public Tester(String nombre, String apellido, String email, String contrasena, String pais, String rol) {
        super(nombre, apellido, email, contrasena, pais, rol);
    }

    @Override
    public void comp() {
        System.out.println(("Tester " + getNombre() + " " + getApellido()) + " " + getEmail() + " " + getRol() + " " + getPais());
    }

}

