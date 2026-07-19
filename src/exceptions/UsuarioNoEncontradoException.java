package exceptions;

// Se lanza cuando un login o busqueda no encuentra el usuario esperado.

public class UsuarioNoEncontradoException extends Exception {
    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
}