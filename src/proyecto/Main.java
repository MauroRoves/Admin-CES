package proyecto;

public class Main {
    public static void main(String[] args) {
        SistemaUsuarios acceso = SistemaUsuarios.getInstancia();
        acceso.mostrarMenu();
    }
}