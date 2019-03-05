package leop.paurustask1.config;

import java.util.Collection;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 

import leop.paurustask1.student.Student;
import leop.paurustask1.student.StudentRepository;

/**
 * 
 * StudentUserDetailsService is responsible for retrieving user data from Database into UserDetails structure,
 * for authentication purposes. It implements UserDetailsService and overrides it's loadUserByUsername method.
 * 
 * @author LeoP
 *
 */
@Service
public class StudentUserDetailsService implements UserDetailsService {
	
    @Autowired
    private StudentRepository studentRepository;
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        Optional<Student> optionalUser = studentRepository.findByDbuser(username);
        if (optionalUser.isPresent()) {
        	Student user = optionalUser.get(); 
        	StudentUserDetails userDetails = new StudentUserDetails();  
        	DozerBeanMapper mapper = new DozerBeanMapper(); 
        	mapper.map(user, userDetails);  
        	return userDetails; 
        } else {
        	throw  new UsernameNotFoundException("Username not found"); 
        } 
    }  
	
	public boolean checkUserHasRole(String role) {
		  Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		  boolean hasRole = false;
		  for (GrantedAuthority authority : authorities) {
		     hasRole = authority.getAuthority().equals(role);
		     if (hasRole) {
		    	 break;
		     }
		  }
		  return hasRole;
	} 
	
	public String getAuthenticatedUserName() {
		String currentUserName = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName(); 
		}
		return currentUserName;
	}
	
	public Integer getAuthenticatedUserId() {
		String currentUserName = getAuthenticatedUserName(); 
		Integer currentUserId = null; 
		if (currentUserName != null && currentUserName != "") {
			Optional<Student> optStudent = studentRepository.findByDbuser(currentUserName); 
			if (optStudent.isPresent()) {
				currentUserId = optStudent.get().getId(); 
			} 
		}
		return currentUserId; 
	}
	
	public boolean checkIf_UserId_AdminOrCurrentUser(Integer id) {
		return (checkUserHasRole("ROLE_ADMIN") || id.equals(getAuthenticatedUserId())); 
	}
	
}
 