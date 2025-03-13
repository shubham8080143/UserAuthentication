package Role_Based.User_Auth.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Role_Based.User_Auth.Entity.User;
import Role_Based.User_Auth.Repository.UserRepository;
import Role_Based.User_Auth.dto.JwtAuthenticationResponse;
import Role_Based.User_Auth.dto.SignInRequest;
import Role_Based.User_Auth.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	// ✅ Signup Method
	public User signup(SignUpRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setName(request.getName());
		user.setRole(request.getRole());

		return userRepository.save(user);
	}

	// ✅ Authenticate User & Generate Token
	public JwtAuthenticationResponse signin(SignInRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		UserDetails user = (UserDetails) authentication.getPrincipal(); // ✅ Fix user casting issue
		String jwtToken = jwtService.generateToken(user);
		// String refreshToken = jwtService.generateRefreshToken(user);

		return new JwtAuthenticationResponse(jwtToken);
	}
}
