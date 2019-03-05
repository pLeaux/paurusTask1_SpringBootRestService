package leop.paurustask1.config;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import leop.paurustask1.student.Student;
import leop.paurustask1.student.StudentRepository;

@Service
public class StudentUserDetails extends Student implements UserDetails {
	
    @Autowired
    private StudentRepository studentRepository;

    public StudentUserDetails() {
        super();
    }
    
    public StudentUserDetails (int id, String name, String dbuser, String dbpassword, String dbrole) {
        super(id, name, dbuser, dbpassword, dbrole);
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(); 
        list.add(new SimpleGrantedAuthority(getDbrole())); // note: role name is in database already saved with "ROLE_" prefix ("ROLE_ADMIN","ROLE_USER") 
        return list; 
	}

	@Override
	public String getPassword() { 
		return getDbpassword();
	}

	@Override
	public String getUsername() { 
		return getDbuser();
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
