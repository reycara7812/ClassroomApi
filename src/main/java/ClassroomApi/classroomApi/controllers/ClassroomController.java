package ClassroomApi.classroomApi.controllers;

import ClassroomApi.classroomApi.model.Classroom;
import ClassroomApi.classroomApi.model.Instructor;
import ClassroomApi.classroomApi.model.Student;
import ClassroomApi.classroomApi.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;


    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return new ResponseEntity<>(classrooms, HttpStatus.OK);
    }

    @GetMapping("/{classroomId}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long classroomId) {
        return classroomService.getClassroomById(classroomId)
                .map(classroom -> new ResponseEntity<>(classroom, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Classroom> saveClassroom(@RequestBody Classroom classroom) {
        Classroom savedClassroom = classroomService.saveClassroom(classroom);
        return new ResponseEntity<>(savedClassroom, HttpStatus.CREATED);
    }

    @PutMapping("/{classroomId}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long classroomId, @RequestBody Classroom updatedClassroom) {
        Classroom updated = classroomService.updateClassroom(updatedClassroom, classroomId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long classroomId) {
        classroomService.deleteClassroom(classroomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
@PostMapping("/{classroomId}/students")
    public ResponseEntity<Classroom> addStudentToClassroom(@PathVariable Long classroomId, @RequestBody Student student) {
        Classroom classroom = classroomService.addStudentToClassroom(classroomId, student);
        return new ResponseEntity<>(classroom, HttpStatus.CREATED);
    }

}
