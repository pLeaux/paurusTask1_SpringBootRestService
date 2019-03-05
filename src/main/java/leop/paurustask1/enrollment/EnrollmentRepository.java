package leop.paurustask1.enrollment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/** 
 * @author LeoP
 * EnrollmentRepository, which extends JpaRepository interface, implements generic CRUD operations on Enrollment data model.
 * Actual CRUD methods are created authomatically by Spring in runtime, according to Table/Field naming conventions
 * (find, findAll, findBy#entityName#paramName, ...) 
 */
@Component
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	public List<Enrollment> findByStudentName(String name);  
	public List<Enrollment> findByStudentId(String id);  
	public List<Enrollment> findByStudyClassName(String name);  
	public List<Enrollment> findByStudyClassId(String id);  
}


 