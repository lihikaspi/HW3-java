public class StackException extends Exception{

    public StackException() {
    }

    public StackException(String message) {
        super(message);
    }

    public StackException(String message, Throwable cause) {
        super(message, cause);
    }
}

class NegativeCapacityException extends StackException {
    public NegativeCapacityException() {

    }

    public NegativeCapacityException(String message) {
        super(message);
    }

    public NegativeCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}

class StackOverflowException extends StackException {
    public StackOverflowException() {

    }

    public StackOverflowException(String message) {
        super(message);
    }

    public StackOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}

class EmptyStackException extends StackException {
    public EmptyStackException() {

    }
    public EmptyStackException(String message) {
        super(message);
    }

    public EmptyStackException(String message, Throwable cause) {
        super(message, cause);
    }
}
