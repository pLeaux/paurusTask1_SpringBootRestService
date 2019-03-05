This java Spring Boot project is prepared by me for Paurus, for internal use only.
Content: "Task 1", REST web service, there are study Classes and Students that can enroll to Classes.
Spring security is used for user Authentication and Authorization and hashed passwords are saved to databes.

Instructions for testing: 

1. DB server: use mySQL server, just create DB schema; tables are created by Application (file InitDbData.java) <br>
2. download project files (src folder + pom.xml) as ZIP and unzip it to project folder<br>
3. Open project in IDE. I used Spring Boot Toolsuite - if you use Eclipse, than instal Spring Boot plugin <br>
   (I installed "Spring Tools 4 for Spring Boot")<br>
4. Open project file config.properties and adjust parameters for database connection<br>
5. Start project as "Spring Boot application"<br>

Test web service methods with Postman:

* authentication: users are created in file InitDbData.java and saved to table Students
  
  3 users/passwords are created: 
  - janezN/jnPassword (role_admin, plain text password), 
  - borutK/bkPassword (role_user, plain text password), 
  - mojcaN/mnPassword (role_user, hashed password)
  
  To use any Http method, User has to be authenticated. 
  Additionally, some functionallity is enabled only for ROLE_ADMIN (see: StodentController.java) 
  
  Security related source files: files in folder /config + StudentController.java
  
* Connect as user janezN with password jnPassword and execude the following Http requests:

  - Get list of classes: http://localhost:8080/classes, http://localhost:8080/classes/1, http://localhost:8080/classes/name/java  
  - Get list of students: http://localhost:8080/students, http://localhost:8080/students/1, http://localhost:8080/students/name/janez  
  - Get list of enrollments: http://localhost:8080/enrollments  
  
  - Add new Student (and user): POST method, URI: 
    http://localhost:8080/students, Body: {"name":"Igor Kobal", "dbuser":"IgorK", "dbpassword":"ikPassword","dbrole":"ROLE_USER"}  
  - Add new Enrollment:  
    POST method, URI: http://localhost:8080/students, Body: { {"student": { "id":3 }, "studyClass": {"id": 3} }  
  - Delete Enrollment: DELETE method, URI: http://localhost:8080/students/4  
  
* Connect as newly added user  IgorK with password ikPassword and role ROLE_USER, and repeat the above Http requests.
  (BTW, check in table Students: password is sawed as hash) 
  
Leo P.
