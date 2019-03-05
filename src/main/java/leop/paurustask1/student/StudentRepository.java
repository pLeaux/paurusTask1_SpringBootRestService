package leop.paurustask1.student;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import leop.paurustask1.student.Student;

/** 
 * @author LeoP
 * StudentRepository, which extends CrudRepository interface, implements generic CRUD operations on Student data model.
 * Actual CRUD methods are created authomatically by Spring in runtime, according to Table/Field naming conventions
 * (find, findAll, findBy#paramName#, ...) 
 */

@Component
public interface StudentRepository extends JpaRepository<Student, Integer>{ // class + PK-type
	
	public List<Student> findByName(String name); 
	
	public Optional<Student> findByDbuser(String dbuser);
	
    @Query("select coalesce(max(S.id), 0) + 1  as nextId FROM Student S")
	public Integer getNextId(); 
 
}