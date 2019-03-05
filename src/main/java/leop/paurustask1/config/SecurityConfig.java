package leop.paurustask1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import leop.paurustask1.student.StudentController;
import leop.paurustask1.student.StudentRepository;


/**
 * 
 * This class sets StudentUserDetailsService as the one, that would be responsible for retrieving of user data from Database,
 * for authentication purposes.
 * Notes: for secured Http POST method to work, CSRF checking had to be disabled and http "content-type" set correctly to "application/json" (http GET works without these changes)
 * 
 * @author LeoP
 *  
 */
@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = StudentRepository.class) 
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private StudentUserDetailsService studentUserDetailsService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(studentUserDetailsService) 
        .passwordEncoder(getPasswordEncoder()); 
	}
 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       super.configure(http); 
       http.csrf().disable(); 
    }
	
	/**
	 * 
	 * 2DO: For easier testing, un-encrypted passwords are also allowed in Database (check 1st tries with hashed pwd, and than with plain text password id the 1st attempt fails)
	 * @author LeoP 
	 *  
	 */
	
	private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
            	System.out.println(">>>>>>>>>>>>>>>>> getPasswordEncoder.encode(): charSequence = "+ charSequence.toString()); 
            	return Utils.EncryptPassword(charSequence.toString());   // return charSequence.toString();  
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	boolean pwdOK;  
            	pwdOK = Utils.CheckPwdWithHash(charSequence.toString(), s); // for hashed password
            	if (! pwdOK) {
            		System.out.println(">>>>>>>>>>>>>>>>> getPasswordEncoder.matches(): with assumed hashed password, login unsuccessfull - will try under assumption, that plain password is saved in Database");
            		pwdOK = charSequence.toString().equals(s);  
            	}       
            	System.out.println(String.format(">>>>>>>>>>>>>>>>> getPasswordEncoder.matches(): entered pwd=%s, pwd in DB=%s, matchResult=%s", charSequence.toString(), s, String.valueOf(pwdOK)));
            	return pwdOK;   
                
            }
        };
    }

}

