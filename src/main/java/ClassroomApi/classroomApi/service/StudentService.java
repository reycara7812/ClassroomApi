package ClassroomApi.classroomApi.service;

import ClassroomApi.classroomApi.handler.exceptions.InstructorNotFoundException;
import ClassroomApi.classroomApi.model.Instructor;
import ClassroomApi.classroomApi.model.Student; // Assuming Student is your updated entity name
import ClassroomApi.classroomApi.repository.InstructorRepository;
import ClassroomApi.classroomApi.repository.StudentRepository;
import ClassroomApi.classroomApi.utility.Group;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
@Autowired
private InstructorRepository    instructorRepository;
private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        logger.info("All Students retrieved");
        return studentRepository.findAll();
    }
    /* List<Student> getAllStudentsInClassroom(Long classroomId) {

        return studentRepository.findByClassroomId(classroomId);
    }

     */

    public List<Student> getStudentsByGroup(Group group) {
        return studentRepository.findByGroup(group);
    }
    public Optional<Student> getStudentById(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            logger.error("Student with id " + studentId + " not found");
            throw new InstructorNotFoundException("Student with id " + studentId + " not found");
        }
        logger.info("Student retrieved successfully");
        return studentRepository.findById(studentId);
    }

    public Student saveStudent(Student student, Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() ->{
            logger.error("Instructor with id " + instructorId + " not found");
            throw new InstructorNotFoundException("Instructor with id " + instructorId + " not found");
        });
        student.setAge(student.getAge());
        student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        student.setSpecialty(student.getSpecialty());
        student.setGroup(student.getGroup());
        student.setInstructor(instructor);
        logger.info("Student saved successfully");

        return studentRepository.save(student);


    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            logger.error("Student with id " + studentId + " not found");
            throw new InstructorNotFoundException("Student with id " + studentId + " not found");
        }
        studentRepository.deleteById(studentId);
        logger.info("Student deleted successfully");
    }


    public Student updateStudent(Student updatedStudent, Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            logger.error("Student with id " + studentId + " not found");
            throw new InstructorNotFoundException("Student with id " + studentId + " not found");
        }
        Optional<Student> studentOptional = studentRepository.findById(studentId);



        Student existingStudent = studentOptional.get();

        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setSpecialty(updatedStudent.getSpecialty());
        existingStudent.setGroup(updatedStudent.getGroup());


    studentRepository.save(existingStudent);
        logger.info("Student updated successfully");

        return existingStudent;
    }



    // Add additional methods as needed for your business logic
}

