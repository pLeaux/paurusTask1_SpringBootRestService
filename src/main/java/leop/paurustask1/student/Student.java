package leop.paurustask1.student;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import leop.paurustask1.config.Utils;

/**
 * 
 * @author LeoP
 * Simplified Student (="user") model, containing just "id" (not auto-generated) and "name" fields and, in adition,
 * security related fields dbuser + dbpassword + role (as string, as there is no extra "role" table).
 * In database, "dbpassword" is saved in encrypted form. 
 *
 */
@Entity
@Table(name="students")
public class Student {
		
	@Id
	@Column(name="id")	
	private Integer id; 
	
	@Column(name="name", nullable=false, length=64)
	private String name; 
	
	@Column(name="dbuser", nullable=true, length=64)
	private String dbuser; 
	
	@Column(name="dbpassword", nullable=true, length=255)
	private String dbpassword; 
	 
	@Column(name="dbrole", nullable=true, length=32)
	private String dbrole;  
	
	public Student() {
		super();
	} 
	
	public Student(Integer id, String name, String dbuser, String dbpassword, String dbrole) {
		super();
		this.id = id;
		this.name = name;
		this.dbuser = dbuser;
		this.dbpassword = dbpassword;
		this.dbrole = dbrole;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword; //  Utils.EncryptPassword(dbpassword);
	}

	public String getDbrole() {
		return dbrole;
	}

	public void setDbrole(String dbrole) {
		this.dbrole = dbrole;
	} 	

}
