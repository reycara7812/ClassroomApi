package ClassroomApi.classroomApi.handler.exceptions;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message) {
        super(message);
    }
}
