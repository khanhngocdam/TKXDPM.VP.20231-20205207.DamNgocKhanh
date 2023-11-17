package exception;

public class MediaNotAvailableException extends  RuntimeException{
    public MediaNotAvailableException(String message) {
        super(message);
    }
}
