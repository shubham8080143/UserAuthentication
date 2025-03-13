package Role_Based.User_Auth.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Role_Based.User_Auth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService {
	private final UserRepository userRepository;

	public UserDetails loaUserByUserName(String username) {
		return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
