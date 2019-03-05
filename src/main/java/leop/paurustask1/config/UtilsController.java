package leop.paurustask1.config;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import leop.paurustask1.studyclass.StudyClass;

@RestController
public class UtilsController {
	
	/**
	 * Returns hashed password, that can be saved as Spring user password to Database (table Students, field dbpassword)
	 * Usage, example: Within browser, for password "admin", execute URI: "localhost:8080/utils/encryptpassword/admin".
	 * To execute this method, user must be authenticated and have the "ADMIN" role (database table Students, dbrole="ROLE_ADMIN")
	 * @param pwd: plain text, not encrypted password
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/utils/encryptpassword/{pwd}") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String GetEncryptedPassword (@PathVariable("pwd") String pwd) {
		return (Utils.EncryptPassword(pwd));
	}
 
	private static class PwdWithHash {
		public String pwd; 
		public String pwdHash; 
	}	
	/**
	 * Compares plain text password with a hash and returns a plain text message result
	 * Can be tested with Postman: POST method, with plain and encrypted password sent within the message body
	 * To execute this method, user must be authenticated and have the "ADMIN" role (database table Students, dbrole="ROLE_ADMIN")
	 * @param pwdWithHash (JSON object, containing 2 string fields: "pwd" and "pwdHash")
	 * @return Plain text message "Password and Hash match!" or "... do not match!" 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/utils/checkpwdhash") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String CheckPwdWithHash (@RequestBody PwdWithHash pwdWithHash) { 
		return (Utils.ShowPwdHashCompareResult(pwdWithHash.pwd, pwdWithHash.pwdHash));
	}
 
	
}
