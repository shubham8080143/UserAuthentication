package Role_Based.User_Auth.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Role_Based.User_Auth.Entity.User; // ✅ Correct import
import Role_Based.User_Auth.Services.AuthenticationService;
import Role_Based.User_Auth.dto.JwtAuthenticationResponse;
import Role_Based.User_Auth.dto.SignInRequest;
import Role_Based.User_Auth.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController { // ✅ Renamed to follow Java naming conventions

	private final AuthenticationService authenticationService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
		System.out.println("Signup API called with: " + signUpRequest.getEmail());

		User createdUser = authenticationService.signup(signUpRequest); // ✅ Ensure correct return type
		return ResponseEntity.ok(createdUser);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignInRequest request) {
		JwtAuthenticationResponse jwtResponse = authenticationService.signin(request); // This returns
																						// JwtAuthenticationResponse
		return ResponseEntity.ok(jwtResponse); // Return JwtAuthenticationResponse in the response
	}

}
