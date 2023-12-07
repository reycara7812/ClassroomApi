package ClassroomApi.classroomApi.controllers;



import ClassroomApi.classroomApi.model.Student;
import ClassroomApi.classroomApi.service.StudentService;
import ClassroomApi.classroomApi.utility.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{instructorId}/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student,@PathVariable Long instructorId) {

        return new ResponseEntity<>(studentService.saveStudent(student,instructorId), HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {

        return new ResponseEntity<>(studentService.updateStudent(updatedStudent, studentId), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   /* @GetMapping("/classrooms/{classroomId}/students")
    public ResponseEntity<List<Student>> getAllStudentsInClassroom(@PathVariable Long classroomId) {
        List<Student> students = studentService.getAllStudentsInClassroom(classroomId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    */

    @GetMapping("/studentsByGroup/{group}")
    public ResponseEntity<List<Student>> getStudentsByGroup(@PathVariable Group group) {
        List<Student> students = studentService.getStudentsByGroup(group);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


}
