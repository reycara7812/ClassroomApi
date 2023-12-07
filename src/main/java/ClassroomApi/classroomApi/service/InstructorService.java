package ClassroomApi.classroomApi.service;

import ClassroomApi.classroomApi.handler.exceptions.InstructorNotFoundException;
import ClassroomApi.classroomApi.model.Instructor;
import ClassroomApi.classroomApi.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAllInstructors() {

        logger.info("All Instructors retrieved");
    return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long instructorId) {
        if (!instructorRepository.existsById(instructorId)) {
            logger.error("Instructor with id " + instructorId + " not found");
            throw new InstructorNotFoundException("Instructor with id " + instructorId + " not found");
        }
        return instructorRepository.findById(instructorId);
    }

    public Instructor saveInstructor(Instructor instructor) {
        instructor.setCatchPhrase(instructor.getCatchPhrase());
        instructor.setFirstName(instructor.getFirstName());
        instructor.setLastName(instructor.getLastName());

logger.info("Instructor saved successfully");
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Instructor updatedInstructor, Long instructorId) {
        if (!instructorRepository.existsById(instructorId)) {
            logger.error("Instructor with id " + instructorId + " not found");
            throw new InstructorNotFoundException("Instructor with id " + instructorId + " not found");
        }
        Optional<Instructor> existingInstructorOptional = instructorRepository.findById(instructorId);



        Instructor existingInstructor = existingInstructorOptional.get();

        // Update the fields you want to allow modification
        existingInstructor.setFirstName(updatedInstructor.getFirstName());
        existingInstructor.setLastName(updatedInstructor.getLastName());
        existingInstructor.setCatchPhrase(updatedInstructor.getCatchPhrase());

        // Add any additional update logic

        // Save the updated instructor
        logger.info("Instructor updated successfully");

        instructorRepository.save(existingInstructor);
        return existingInstructor;
    }

    public void deleteInstructor(Long instructorId) {
        logger.info("Instructor deleted successfully");
        instructorRepository.deleteById(instructorId);
    }


    }


