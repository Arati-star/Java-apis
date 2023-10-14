package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class User {
	private String fullname;
	private String email;
	private Long mobile;
	private String gender;
	private LocalDate Dob;
	private Long ssn;
	

}
