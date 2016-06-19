package by.bsuir.parsejavaproj.exception;

/**
 * Created by Алексей on 13.03.2016.
 */
public class JavaFileReadingException extends Exception {
    public JavaFileReadingException() {
        super();
    }

    public JavaFileReadingException(String message) {
        super(message);
    }

    public JavaFileReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JavaFileReadingException(Throwable cause) {
        super(cause);
    }

    protected JavaFileReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
