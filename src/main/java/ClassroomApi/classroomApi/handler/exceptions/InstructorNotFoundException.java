package ClassroomApi.classroomApi.handler.exceptions;

public class InstructorNotFoundException extends RuntimeException{
    public InstructorNotFoundException(String message) {
        super(message);
    }

}
