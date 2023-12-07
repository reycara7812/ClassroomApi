package ClassroomApi.classroomApi.service;

import ClassroomApi.classroomApi.handler.exceptions.ResourceNotFoundException;
import ClassroomApi.classroomApi.model.Classroom;
import ClassroomApi.classroomApi.model.Student;
import ClassroomApi.classroomApi.repository.ClassroomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {
@Autowired
    private final ClassroomRepository classroomRepository;


    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> getClassroomById(Long classroomId) {
        return classroomRepository.findById(classroomId);
    }


    public Classroom addStudentToClassroom(Long classroomId, Student student) {
        Optional<Classroom> classroomOptional = classroomRepository.findById(classroomId);
        if (classroomOptional.isEmpty()) {
            throw new ResourceNotFoundException("Classroom with id " + classroomId + " not found");
        }
        Classroom classroom = classroomOptional.get();
        classroom.getStudents().add(student);
        return classroomRepository.save(classroom);
    }

    public Classroom saveClassroom(Classroom classroom) {
        classroom.setClassName(classroom.getClassName());

        return classroomRepository.save(classroom);
    }

    public Classroom updateClassroom(Classroom updatedClassroom, Long classroomId) {
        Optional<Classroom> existingClassroomOptional = classroomRepository.findById(classroomId);



        Classroom existingClassroom = existingClassroomOptional.get();


        existingClassroom.setClassName(updatedClassroom.getClassName());
        existingClassroom.setStudents(updatedClassroom.getStudents());
        existingClassroom.setInstructor(updatedClassroom.getInstructor());


        return existingClassroom;
    }


    public void deleteClassroom(Long classroomId) {
        classroomRepository.deleteById(classroomId);
    }


}