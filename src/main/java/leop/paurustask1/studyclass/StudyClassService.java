package leop.paurustask1.studyclass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javassist.NotFoundException; 

@Service 
public class StudyClassService {
	
	@Autowired
	private StudyClassRepository studyClassRepository; 
	
	
	public List<StudyClass> getAllStudyClasses(){
		List<StudyClass> studyClasses = new ArrayList<>();  
		studyClassRepository.findAll().forEach(studyClasses::add); 
		return(studyClasses); 
	}
	
	public List<StudyClass> getStudyClassesByName(String name){
		List<StudyClass> studyClasses = new ArrayList<>();  
		studyClasses = studyClassRepository.findByNameIgnoreCaseContaining(name); 
		// studyClassRepository.findByNameIgnoreCaseContaining(name).forEach(studyClasses::add); 
		return(studyClasses); 
	}
	
	public StudyClass getStudyClass(int id) throws NotFoundException {  
		Optional<StudyClass> optStudyClass = studyClassRepository.findById(id);
		if (optStudyClass.isPresent()){
		    return(optStudyClass.get()); 
		}
		else {
		   throw new NotFoundException(String.format("User with id %n not found", id));
		}
	}
	
	public void addStudyClass(StudyClass studyClass) { 
		studyClassRepository.save(studyClass); 
	}
	
	public void updateStudyClass(String id, StudyClass studyClass_new) { 
		studyClassRepository.save(studyClass_new); 
	}
	
	public void deleteStudyClass(int id) {  
		studyClassRepository.deleteById(id); 
	}
}
