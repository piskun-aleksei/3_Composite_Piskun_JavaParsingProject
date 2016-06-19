package by.bsuir.parsejavaproj.exception;

/**
 * Created by Алексей on 15.03.2016.
 */
public class ZeroClassesInFileException extends Exception {
    public ZeroClassesInFileException() {
        super();
    }

    public ZeroClassesInFileException(String message) {
        super(message);
    }

    public ZeroClassesInFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZeroClassesInFileException(Throwable cause) {
        super(cause);
    }

    protected ZeroClassesInFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
