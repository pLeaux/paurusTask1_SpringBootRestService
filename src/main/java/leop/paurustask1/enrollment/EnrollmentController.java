package leop.paurustask1.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import leop.paurustask1.student.Student;
import leop.paurustask1.student.StudentRepository;

@RestController
public class EnrollmentController {

	@Autowired
	private EnrollmentRepository enrollmentRepository; 
	
	@RequestMapping(method=RequestMethod.GET, value="/enrollments") 
	public List<Enrollment> getAllEnrollments() {
		List<Enrollment> enrollments = new ArrayList<>();  
		enrollmentRepository.findAll().forEach(enrollments::add); 
		return(enrollments);  
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/enrollments") 
	public void addEnrollment(@RequestBody Enrollment enrollment) {
		enrollmentRepository.save(enrollment);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/enrollments/{id}") 
	public void deleteStudent(@PathVariable("id") Integer id) {
		enrollmentRepository.deleteById(id);  
	}
}





 