package leop.paurustask1.enrollment;

import java.io.Serializable;

import javax.annotation.*;
import javax.persistence.*;

import leop.paurustask1.student.Student;
import leop.paurustask1.studyclass.StudyClass;

@Entity (name = "enrollment")
@Table(name="enrollment", uniqueConstraints = {@UniqueConstraint(columnNames = {"studentid", "studyclassId"})})
public class Enrollment implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
    @ManyToOne
    @JoinColumn(name="studentid", referencedColumnName="id")  
	private Student student;  
    
    @ManyToOne
    @JoinColumn(name="studyclassid", referencedColumnName="id")
	private StudyClass studyClass;

	public Enrollment() {
		super();
	}

	public Enrollment(Student student, StudyClass studyClass) {
		super();
		this.student = student;
		this.studyClass = studyClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public StudyClass getStudyClass() {
		return studyClass;
	}

	public void setStudyClass(StudyClass studyClass) {
		this.studyClass = studyClass;
	}
	
 
}
