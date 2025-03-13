package Role_Based.User_Auth.dto;

import Role_Based.User_Auth.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
	private String name;
	private String email;
	private String password;
	private Role role; // e.g., "USER" or "ADMIN"
}
