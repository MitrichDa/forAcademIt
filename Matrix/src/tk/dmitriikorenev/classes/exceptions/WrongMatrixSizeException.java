package tk.dmitriikorenev.classes.exceptions;

public class WrongMatrixSizeException extends RuntimeException {
    public WrongMatrixSizeException(String message) {
        super(message);
    }
}
