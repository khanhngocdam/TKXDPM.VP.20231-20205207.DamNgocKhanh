package exception;

public class InvalidDeliveryInfoException extends  RuntimeException {
    public InvalidDeliveryInfoException(String message) {
        super(message);
    }
}
