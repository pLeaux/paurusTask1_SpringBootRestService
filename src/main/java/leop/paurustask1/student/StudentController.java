package leop.paurustask1.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import leop.paurustask1.config.StudentUserDetailsService;
import leop.paurustask1.config.Utils;
import leop.paurustask1.studyclass.StudyClass;

@RestController
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository; 
	
	@Autowired
	private StudentUserDetailsService studentUserDetailsService; 

	// just as an example 
	@RequestMapping(method=RequestMethod.GET, value="/students") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Student> getAllStudents() {
		List<Student> students = new ArrayList<>();  
		studentRepository.findAll().forEach(students::add); 
		return(students);  
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/students/{id}") 
	public Student getStudent(@PathVariable("id") Integer id) throws NotFoundException {
		Optional<Student> optStudent = studentRepository.findById(id); 
		if (optStudent.isPresent()) {
			if (! studentUserDetailsService.checkIf_UserId_AdminOrCurrentUser(id)) 
				optStudent.get().setDbpassword("******");
		    return(optStudent.get());
		} else {
			throw new NotFoundException(String.format("User with id %n not found", id));
		} 
	}
 
	@RequestMapping(method=RequestMethod.GET, value="/students/name/{name}") 
	public List<Student> getStudentsByName(@PathVariable("name") String name) { 
		List<Student> students = studentRepository.findByName(name); 		
		return (students); 
	} 
	
	@RequestMapping(method=RequestMethod.POST, value="/students") 
	public void addStudent(@RequestBody Student student) {
		student.setDbpassword(Utils.EncryptPassword(student.getDbpassword()));
		if (! studentUserDetailsService.checkUserHasRole("ROLE_ADMIN")) 
			student.setDbrole("ROLE_USER");
		// temp fix; should be replaced with Student.Id table field as auto-generated 
		if (student.getId() == null) { 
			student.setId(studentRepository.getNextId());   
		}
		studentRepository.save(student);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/students/{id}") 
	public void updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
		if (! studentUserDetailsService.checkIf_UserId_AdminOrCurrentUser(id)) {
			throw new BadCredentialsException("You are not authorized to change other people's data"); 		
		} 
		if (! id.equals(student.getId())) {
			throw new BadCredentialsException("Bad request - user id in request body differs from id in the request URI");
		}
		if (! studentUserDetailsService.checkUserHasRole("ROLE_ADMIN")) 
			student.setDbrole("ROLE_USER");
		student.setDbpassword(Utils.EncryptPassword(student.getDbpassword()));
		studentRepository.save(student);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/students/{id}") 
	public void deleteStudent(@PathVariable("id") Integer id) {
		if (! studentUserDetailsService.checkIf_UserId_AdminOrCurrentUser(id)) {
			throw new BadCredentialsException("You are not authorized to delete other people's data"); 		
		}
		studentRepository.deleteById(id);  
	}
  
 
}