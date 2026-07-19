package exceptions;

// Se lanza cuando se intenta registrar un usuario con un email que ya existe en el sistema.

public class EmailDuplicadoException extends Exception {
    public EmailDuplicadoException(String message) {
        super(message);
    }
}