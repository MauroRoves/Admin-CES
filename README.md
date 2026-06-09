Funcionalidades:

Inicio de sesión
Permite acceder al sistema con un usuario.
Datos: email, contraseña

Registrar usuario admin
Permite registrar un usuario
Datos: nombre, apellido, mail, contraseña, pais nacimiento

Ver usuarios
Permite ver todos los usuarios registrados

Reiniciar contraseña
Permite reestablecer la contraseña de un usuario.
Datos: email, contraseña

Alta de cuenta para tester
Registrar usuario tester.
Datos: Nombre, Apellido, Email, Pais, Contraseña, Tipo de Tester (Jr., Sr., Líder)

Ver y editar perfil de usuario admin
Permite ver datos del propio usuario y editarlos.
Eliminar usuario	
Permite eliminar un usuario de tester 


20/5/2026

Se implementaron las funcionalidades basicas
Login
Registro


Validaciones de funcionalidades Login y Registro
Verificar usuario existente
Validar credenciales


6/8/2026

Se crearon las clases :

Usuario
SistemaUsuarios
Main
Tester
Administrador

Se agregaron:

atributos privados
getters/setters
constructor

Se incorporo la herencia en Adminitrador y Tester


Diagrama UML

![2026-06-08 20_48_43-Window.png](../../Desktop/CURSO%20CES/Curso%20programacion/Entrega%203/2026-06-08%2020_48_43-Window.png)

class Main {
+main(String[])
}

class Usuario {
-String nombre
-String apellido
-String email
-String contrasena
-String pais
-String rol

    +Usuario(nombre, apellido, email, contrasena, pais, rol)
    +getNombre() String
    +getApellido() String
    +getEmail() String
    +getContrasena() String
    +getPais() String
    +getRol() String
    +setNombre()
    +setApellido()
    +setEmail()
    +setContrasena()
    +setPais()
    +setRol()
}

class SistemaUsuarios {
-ArrayList~Usuario~ usuarios
-Scanner scan

    -cargarUsuarios() String
    +existeEmail(boolean) String
    +login() String
    +listarUsuarios() String
    +mostrarMenu() String
    +menuLogin() String
    +menuRegistro() String
}

class Tester {
+Tester(nombre, apellido, email, contrasena, pais, rol)
}

class Administrador {
+Administrador(nombre, apellido, email, contrasena, pais, rol)
}