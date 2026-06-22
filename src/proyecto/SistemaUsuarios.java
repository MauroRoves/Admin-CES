package proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuarios {

    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Scanner scan = new Scanner(System.in);

    public SistemaUsuarios() {
        cargarUsuarios();
    }

    //  Carga usuarios de prueba
    private void cargarUsuarios() {
        usuarios.add(new Administrador("Julio", "Perez",     "julio@gmail.com", "13245678", "Uruguay", "Senior"));
        usuarios.add(new Administrador("Pepe",  "Rodriguez", "pepe@gmail.com",  "54321678", "Uruguay", "Semisenior"));
        usuarios.add(new Tester("Gusman", "Figueredo", "gus@gmail.com", "67890123", "Uruguay", "Junior"));
        usuarios.add(new Tester("Ana",    "Gonzales",  "ana@gmail.com", "34567890", "Uruguay", "Senior"));
    }

    // Verificar si un email ya existe
    public boolean existeEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // Buscar usuario por email y contraseña (para login)
    public Usuario login (String email, String contrasena) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(email) && usuarios.get(i).getContrasena().equals(contrasena)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    // Listar todos los usuarios
    public void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).comp();
            }

    }

    // Buscar usuario por nombre o email
    public void buscarUsuario(String criterio) {
        System.out.println("\n--- RESULTADO DE LA BUSQUEDA ---");
        boolean encontrado = false;
        for (int i = 0; i < usuarios.size(); i++ ) {
            Usuario u = usuarios.get(i);
            if (u.getNombre(). equalsIgnoreCase(criterio) || u.getEmail().equalsIgnoreCase(criterio)) {
                u.comp();
                encontrado = true;
            }
        }
        if (!encontrado){
            System.out.println("No se encontro ningun usuario con ese criterio");
        }
    }



    // Menú principal
    public void mostrarMenu() {
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n--- MENU ---");
            System.out.println("1- Login");
            System.out.println("2- Registro");
            System.out.println("3- Listar Usuarios");
            System.out.println("4- Buscar Usuarios");
            System.out.println("5- Salir");

            opcion = Integer.parseInt(scan.nextLine());

            if (opcion == 1) {
                menuLogin();
            } else if (opcion == 2) {
                menuRegistro();
            } else if (opcion == 3) {
                listarUsuarios();
            } else if (opcion == 4) {
                System.out.println("Ingrese nombre o email a buscar");
                String criterio = scan.nextLine();
                buscarUsuario(criterio);
            } else if (opcion == 5) {
                System.out.println("Saliendo.");
            } else {
                System.out.println("Opcion invalida");
            }
        }
    }

    // Pantalla de login
    private void menuLogin() {
        System.out.println("Ingrese email:");
        String email = scan.nextLine();

        System.out.println("Ingrese contrasena:");
        String contrasena = scan.nextLine();

        Usuario resultado = login(email, contrasena);

        if (resultado != null) {
            System.out.println("Login exitoso. Bienvenido " + resultado.getNombre());
            resultado.comp();
        } else {
            System.out.println("Email o contraseña incorrectos");
        }
    }

    //  Pantalla de registro
    private void menuRegistro() {
        System.out.println("Que tipo de usuario desea registrar?");
        System.out.println("1- Administrador");
        System.out.println("2- Tester");
        int tipo = Integer.parseInt(scan.nextLine());

        System.out.println("Ingrese nombre:");
        String nombre = scan.nextLine();

        System.out.println("Ingrese apellido:");
        String apellido = scan.nextLine();

        System.out.println("Ingrese email:");
        String email = scan.nextLine();

        if (existeEmail(email)) {
            System.out.println("El email ya esta registrado.");
            return;
        }

        System.out.println("Ingrese pais:");
        String pais = scan.nextLine();

        System.out.println("Ingrese contrasena:");
        String contrasena = scan.nextLine();

        System.out.println("Ingrese rol (Junior / Senior / Lider):");
        String rol = scan.nextLine();

        if (tipo == 1) {
            usuarios.add(new Administrador(nombre, apellido, email, contrasena, pais, rol));
            System.out.println("Administrador registrado con exito.");
        } else if (tipo == 2) {
            usuarios.add(new Tester(nombre, apellido, email, contrasena, pais, rol));
            System.out.println("Tester registrado con exito.");
        } else {
            System.out.println("Tipo de usuario invalido.");
        }
    }
}
