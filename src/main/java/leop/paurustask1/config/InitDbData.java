package leop.paurustask1.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import leop.paurustask1.enrollment.Enrollment;
import leop.paurustask1.enrollment.EnrollmentRepository;
import leop.paurustask1.student.Student;
import leop.paurustask1.student.StudentRepository;
import leop.paurustask1.studyclass.StudyClass;
import leop.paurustask1.studyclass.StudyClassRepository;


/**
 * 
 * Class InitDbData executes automatically upon application start and initializes database tables with the test data. New data is not inserted only if tables are not empty.
 * 3 users are created, 2 with plain text passwords ("JanezN"/"jnPassword", "BorutK"/"bkPassword") and 1 with hashed password ("MojcaN", "mnPassword")
 * Also 3 classes are created and some student enrollments
 * 
 * @author LeoP
 *
 */
@Configuration
@Component
public class InitDbData implements CommandLineRunner {
 
	@Autowired
	StudyClassRepository scr; 
	
	@Autowired
	StudentRepository sr;
	
	@Autowired
	EnrollmentRepository er; 
 
	@Override
	public void run(String... args) throws Exception {

		System.out.println(">>>>>>>>>>>>>>>>>>> InitDbData start!");
		
		List<StudyClass> studyClasses = new ArrayList<StudyClass>() {{ 
			add (new StudyClass(1, "Java basics")); 
		    add (new StudyClass(2, "Java advanced programming")); 
		    add (new StudyClass(3, "Networking"));
		}};
	
		List<Student> students = new ArrayList<Student>() {{ 
				add (new Student(1, "Janez Novak", "JanezN", "jnPassword", "ROLE_ADMIN")); 
			    add (new Student(2, "Borut Klinc", "BorutK", "bkPassword", "ROLE_USER")); 
			    add (new Student(3, "Mojca Novak", "MojcaN", Utils.EncryptPassword("mnPassword"), "ROLE_USER"));
		}};
		
		// insert some study classes
		if (scr.count() == 0) { 
			scr.save(studyClasses.get(0));
			scr.save(studyClasses.get(1));
			scr.save(studyClasses.get(2)); 
		};
		// insert some students
		if (sr.count() == 0) {
			sr.save(students.get(0));
			sr.save(students.get(1));
			sr.save(students.get(2)); 
		};
		// insert some enrollments
		if (er.count() == 0) {
			er.save(new Enrollment (students.get(0), studyClasses.get(0)));  
			er.save(new Enrollment (students.get(0), studyClasses.get(1))); 
			er.save(new Enrollment (students.get(1), studyClasses.get(2)));  
			er.save(new Enrollment (students.get(2), studyClasses.get(0)));  
			er.save(new Enrollment (students.get(2), studyClasses.get(1))); 
			er.save(new Enrollment (students.get(2), studyClasses.get(2)));   
		};		
				
		System.out.println(">>>>>>>>>>>>>>>>>>> InitDbData end!");
		
	}

}
