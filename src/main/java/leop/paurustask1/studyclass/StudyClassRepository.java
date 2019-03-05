package leop.paurustask1.studyclass; 

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/** 
 * 
 * @author LeoP
 * StudyClassRepository, which extends CrudRepository interface, implements generic CRUD operations on StudyClass data model.
 * Actual CRUD methods are created automatically by Springboot in runtime, according to Table/Field naming conventions
 * (find, findAll, findBy#paramName#, ...: "findByMyName (string myName)") 
 * 
 */

@Component
public interface StudyClassRepository extends CrudRepository<StudyClass, Integer>{ 

	public List<StudyClass> findByName(String name); 
	public List<StudyClass> findByNameIgnoreCaseContaining(String name); 
}
