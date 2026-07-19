package proyecto;

import exceptions.EmailDuplicadoException;
import exceptions.UsuarioNoEncontradoException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuarios {

    private static final int largoMinimo = 5;
    private static final String formatoEmail = "^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$";

    // Atributo estatico y privado que guarda la unica instancia de la clase (patron Singleton)
    private static SistemaUsuarios instancia;

    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Scanner scan = new Scanner(System.in);

    // Constructor privado: impide crear instancias desde afuera con "new SistemaUsuarios()"
    private SistemaUsuarios() {
        cargarUsuarios();
    }

    // Unico punto de acceso a la instancia. Si no existe, la crea; si ya existe, la devuelve.
    public static SistemaUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }

    // Carga usuarios de prueba
    private void cargarUsuarios() {
        usuarios.add(new Administrador("Julio", "Perez", "julio@gmail.com", "13245678", "Uruguay"));
        usuarios.add(new Administrador("Pepe", "Rodriguez", "pepe@gmail.com", "54321678", "Uruguay"));
        usuarios.add(new Tester("Gusman", "Figueredo", "gus@gmail.com", "67890123", "Uruguay", "Junior"));
        usuarios.add(new Tester("Ana", "Gonzales", "ana@gmail.com", "34567890", "Uruguay", "Senior"));
    }

    // ---------------- VALIDACIONES ----------------

    // Valida los campos obligatorios comunes a todo usuario (Administrador o Tester).
    private void validarCampos(String nombre, String apellido, String email, String contrasena, String pais) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
        if (!email.matches(formatoEmail)) {
            throw new IllegalArgumentException("El email ingresado no tiene un formato valido.");
        }
        if (pais == null || pais.trim().isEmpty()) {
            throw new IllegalArgumentException("El pais es obligatorio.");
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("La contrasena es obligatoria.");
        }
        if (contrasena.length() < largoMinimo) {
            throw new IllegalArgumentException("La contrasena debe tener al menos " + largoMinimo + " caracteres.");
        }
    }

    // Verificar si un email ya existe
    public boolean existeEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Buscar usuario por email y contraseña
    public Usuario login(String email, String contrasena) throws UsuarioNoEncontradoException {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equalsIgnoreCase(email) && usuarios.get(i).getContrasena().equals(contrasena)) {
                return usuarios.get(i);
            }
        }
        throw new UsuarioNoEncontradoException("No se encontro un usuario con ese email y contrasena.");
    }

    // Listar todos los usuarios
    public void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        for (int i = 0; i < usuarios.size(); i++) {
            usuarios.get(i).comp();
        }
    }

    // Buscar usuario por email
    public void buscarUsuarioPorEmail(String email) throws UsuarioNoEncontradoException {
        System.out.println("\n--- RESULTADO DE LA BUSQUEDA ---");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getEmail().equalsIgnoreCase(email)) {
                u.comp();
                return;
            }
        }
        throw new UsuarioNoEncontradoException("No existe un usuario registrado con el email '" + email + "'.");
    }

    // Lee una opcion numerica del menu.
    private int leerOpcion() {
        String entrada = scan.nextLine();
        return Integer.parseInt(entrada.trim());
    }

    // MENU DE ACCESO (solo Administrador)

    public void mostrarMenu() {
        boolean salirPrograma = false;

        while (!salirPrograma) {
            Usuario adminActivo = null;

            while (adminActivo == null && !salirPrograma) {
                System.out.println("\n--- ACCESO ADMINISTRADOR ---");
                System.out.println("1- Iniciar Sesion");
                System.out.println("2- Registro Administrador");
                System.out.println("3- Salir");

                try {
                    int opcion = leerOpcion();

                    if (opcion == 1) {
                        adminActivo = menuLoginAdministrador();
                    } else if (opcion == 2) {
                        adminActivo = menuRegistroAdministrador();
                    } else if (opcion == 3) {
                        System.out.println("Saliendo del sistema.");
                        salirPrograma = true;
                    } else {
                        throw new IllegalArgumentException("La opcion " + opcion + " no existe en el menu.");
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Error: debe ingresar un numero. Intente nuevamente.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (UsuarioNoEncontradoException | EmailDuplicadoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (adminActivo != null) {
                boolean cerrarSesion = menuPrincipal(adminActivo);
                if (!cerrarSesion) {
                    salirPrograma = true;
                }
            }
        }

        System.out.println("Sesion finalizada. Hasta luego.");
    }

    private Usuario menuLoginAdministrador() throws UsuarioNoEncontradoException {
        System.out.println("Ingrese email:");
        String email = scan.nextLine();

        System.out.println("Ingrese contrasena:");
        String contrasena = scan.nextLine();

        Usuario resultado = login(email, contrasena);

        if (!(resultado instanceof Administrador)) {
            throw new UsuarioNoEncontradoException("El usuario encontrado no es un Administrador.");
        }

        System.out.println("Login exitoso. Bienvenido " + resultado.getNombre());
        resultado.comp();
        return resultado;
    }

    private Usuario menuRegistroAdministrador() throws EmailDuplicadoException {
        System.out.println("Ingrese nombre:");
        String nombre = scan.nextLine();

        System.out.println("Ingrese apellido:");
        String apellido = scan.nextLine();

        System.out.println("Ingrese email:");
        String email = scan.nextLine();

        System.out.println("Ingrese pais:");
        String pais = scan.nextLine();

        System.out.println("Ingrese contrasena:");
        String contrasena = scan.nextLine();

        validarCampos(nombre, apellido, email, contrasena, pais);

        if (existeEmail(email)) {
            throw new EmailDuplicadoException("El email '" + email + "' ya esta registrado.");
        }

        Administrador nuevoAdmin = new Administrador(nombre, apellido, email, contrasena, pais);
        usuarios.add(nuevoAdmin);
        System.out.println("Administrador registrado con exito.");
        return nuevoAdmin;
    }

    //  MENU PRINCIPAL

    private boolean menuPrincipal(Usuario adminActivo) {
        boolean cerrarSesion = false;
        boolean salir = false;

        while (!cerrarSesion && !salir) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("Sesion iniciada como: " + adminActivo.getNombre() + " " + adminActivo.getApellido());
            System.out.println("1- Registrar Tester");
            System.out.println("2- Buscar Usuario por Email");
            System.out.println("3- Listar Usuarios");
            System.out.println("4- Cerrar Sesion");
            System.out.println("5- Salir");

            try {
                int opcion = leerOpcion();

                if (opcion == 1) {
                    menuRegistroTester();
                } else if (opcion == 2) {
                    System.out.println("Ingrese el email a buscar:");
                    String email = scan.nextLine();
                    buscarUsuarioPorEmail(email);
                } else if (opcion == 3) {
                    listarUsuarios();
                } else if (opcion == 4) {
                    System.out.println("Cerrando sesion...");
                    cerrarSesion = true;
                } else if (opcion == 5) {
                    System.out.println("Saliendo del sistema.");
                    salir = true;
                } else {
                    throw new IllegalArgumentException("La opcion " + opcion + " no existe en el menu.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero. Intente nuevamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (UsuarioNoEncontradoException | EmailDuplicadoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return cerrarSesion;
    }

    private void menuRegistroTester() throws EmailDuplicadoException {
        System.out.println("Ingrese nombre:");
        String nombre = scan.nextLine();

        System.out.println("Ingrese apellido:");
        String apellido = scan.nextLine();

        System.out.println("Ingrese email:");
        String email = scan.nextLine();

        System.out.println("Ingrese pais:");
        String pais = scan.nextLine();

        System.out.println("Ingrese contrasena:");
        String contrasena = scan.nextLine();

        System.out.println("Ingrese rol (Junior / Senior / Lider):");
        String rol = scan.nextLine();

        validarCampos(nombre, apellido, email, contrasena, pais);

        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }

        if (existeEmail(email)) {
            throw new EmailDuplicadoException("El email '" + email + "' ya esta registrado.");
        }

        usuarios.add(new Tester(nombre, apellido, email, contrasena, pais, rol));
        System.out.println("Tester registrado con exito.");
    }
}