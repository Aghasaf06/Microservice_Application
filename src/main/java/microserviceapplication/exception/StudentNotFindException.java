package microserviceapplication.exception;

public class StudentNotFindException extends RuntimeException {

    public StudentNotFindException(String message) {
        super(message);
    }
}
