package leop.paurustask1.config;

import java.util.Collection;


public class Utils {
	
	public static String EncryptPassword(String pwd) {
		org.springframework.security.crypto.password.PasswordEncoder encoder;  
		String pwdHash; 
		encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		pwdHash = encoder.encode(pwd); 
		return(pwdHash); 
	}
	
	public static boolean CheckPwdWithHash(String pwd, String pwdHash) {
		org.springframework.security.crypto.password.PasswordEncoder encoder;  
		encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
		return(encoder.matches(pwd, pwdHash));
	}
	  
	public static String ShowPwdHashCompareResult(String pwd, String pwdHash) {
		if (CheckPwdWithHash(pwd, pwdHash)) {
			return ("Password and Hash match!");
		} else {
			return ("Password and Hash do not match!");
		}
		 
	} 

}
