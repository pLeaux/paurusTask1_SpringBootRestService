/**
 * 
 */
package leop.paurustask1.studyclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LeoP
 *
 */

@Entity
@Table(name="StudyClasses")
public class StudyClass {
	
	@Id
	@Column(name="id")
	private int id; 
	@Column(name="name", nullable=false)
	private String name; 
	
	public StudyClass() {
		
	}
	
	public StudyClass(int id, String name) {
		this.id = id; 
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 	

}
