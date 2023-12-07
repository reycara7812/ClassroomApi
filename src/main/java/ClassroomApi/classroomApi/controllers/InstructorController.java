package ClassroomApi.classroomApi.controllers;

import ClassroomApi.classroomApi.model.Instructor;
import ClassroomApi.classroomApi.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {;
        return new ResponseEntity<>(instructorService.getAllInstructors(), HttpStatus.OK);
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long instructorId) {
        return new ResponseEntity<>(instructorService.getInstructorById(instructorId).get(), HttpStatus.OK);
    }

    @PostMapping("/{instructorId}")
    public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor) {

        return new ResponseEntity<>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long instructorId, @RequestBody Instructor updatedInstructor) {

        return new ResponseEntity<>(instructorService.updateInstructor(updatedInstructor, instructorId), HttpStatus.OK);
    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
