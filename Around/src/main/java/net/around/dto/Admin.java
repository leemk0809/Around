package net.around.dto;


/*@Data
@AllArgsConstructor
@NoArgsConstructor*/
public class Admin {
	String adminId;
	String password;
	public Admin() {
		this.adminId = "123";
		this.password = "123";		
	}
	public String getAdminId() {
		return adminId;
	}
	public String getPassword() {
		return password;
	}
}
