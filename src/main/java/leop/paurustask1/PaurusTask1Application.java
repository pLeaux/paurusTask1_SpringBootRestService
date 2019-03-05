package leop.paurustask1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.prepost.PreAuthorize;

import leop.paurustask1.student.StudentRepository;
import leop.paurustask1.studyclass.StudyClassRepository;

/**
 * 
 * Application is the solution for the 1st task, given to me by Paurus: there are study Classes and Students that can enroll to Classes.
 * REST API is prepared for most of the 5 CRUD operations on StudyClasses, Students (students are also users) and Enrollments.
 * Also, upon first execution, database schema (tables: Students, StudyClasses, Enrollment) and test data are initialized (see: .config/InitDbData for details).
 * Authentication is enabled, passwords in database are hashed by default (for new users), but un-hashed passwords are also accepted (see: .config/SecurityConfig class) 
 * Authorization is just tested: execution of URI "/students" is allowed only for users with the role "ROLE_ADMIN" 
 * 
 */
/**
 * 
 * @author LeoP 
 * 
 */
/**
 *
 * <b>URI Examples:</b> <br>
 *
 * Get list of classes: http://localhost:8080/classes, http://localhost:8080/classes/1, http://localhost:8080/classes/name/java <br>
 * Get list of students: http://localhost:8080/students, http://localhost:8080/students/1, http://localhost:8080/students/name/janez <br>
 * Get list of enrollments: http://localhost:8080/enrollments <br>
 * <br>
 * Add new Student (and user): POST method, URI: http://localhost:8080/students, Body: {"name":"Igor Kobal", "dbuser":"IgorK", "dbpassword":"ikPassword","dbrole":"ROLE_USER"} <br>
 * Add new Enrollment:  POST method, URI: http://localhost:8080/students, Body: { {"student": { "id":3 }, "studyClass": {"id": 3} } <br>
 * Delete Enrollment: DELETE method, URI: http://localhost:8080/students/4 <br>
 * 
 */
@SpringBootApplication
@ComponentScan("leop.paurustask1") 
// @EnableJpaRepositories(basePackageClasses = { StudyClassRepository.class, StudentRepository.class })
@EnableJpaRepositories("leop.paurustask1")
public class PaurusTask1Application {

	public static void main(String[] args) {
		System.out.println("Main() BEFORE RUN ! >>>>>>>>>>>>>>>>>");
		SpringApplication.run(PaurusTask1Application.class, args);
		System.out.println("Main() AFTER RUN ! >>>>>>>>>>>>>>>>>");
	}

}
