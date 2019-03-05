package leop.paurustask1.studyclass; 
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

/**
 * 
 * @author LeoP
 * REST Web Service controller, that handles HTTP requests for StudyClasses.
 * It uses StudyClassService/StudyClassRepository for database access.
 * 
 */
@RestController
public class StudyClassController {
	
	@Autowired
	private StudyClassService studyClassService; 

	@RequestMapping("/classes") 
	public List<StudyClass> getAllStudyClasses() {
		return(studyClassService.getAllStudyClasses());  
	}
	
	@RequestMapping("/classes/{id}") 
	public StudyClass getStudyClass(@PathVariable("id") Integer id) throws NotFoundException {
		return(studyClassService.getStudyClass(id));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/classes/name/{name}") 
	public List<StudyClass> getStudyClass(@PathVariable("name") String name) {
		return (studyClassService.getStudyClassesByName(name)); 
	} 
	
	@RequestMapping(method=RequestMethod.POST, value="/classes") 
	public void addStudyClass(@RequestBody StudyClass studyClass) {
		studyClassService.addStudyClass(studyClass);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/classes/{id}") 
	public void updateStudyClass(@PathVariable("id") String id, @RequestBody StudyClass studyClass) {
		studyClassService.updateStudyClass(id, studyClass);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/classes/{id}") 
	public void deleteTopic(@PathVariable("id") Integer id) {
		studyClassService.deleteStudyClass(id);
	}
 	
 
}