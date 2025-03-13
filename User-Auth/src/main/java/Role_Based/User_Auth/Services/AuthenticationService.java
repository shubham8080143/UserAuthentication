package Role_Based.User_Auth.Services;

import Role_Based.User_Auth.dto.JwtAuthenticationResponse;
import Role_Based.User_Auth.dto.SignInRequest;
import Role_Based.User_Auth.dto.SignUpRequest;

public interface AuthenticationService {
	public Role_Based.User_Auth.Entity.User signup(SignUpRequest signuprequest);

	public JwtAuthenticationResponse signin(SignInRequest request);
}
