package Role_Based.User_Auth.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Role_Based.User_Auth.Entity.User;
import Role_Based.User_Auth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private static final String UPLOAD_DIR = "uploads/"; // ✅ Define the upload directory

	// ✅ Corrected method name for Spring Security
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(Long id, User updatedUser) {
		User existingUser = getUserById(id);
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setRole(updatedUser.getRole());
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		User user = getUserById(id);
		userRepository.delete(user);
	}

	// ✅ Upload profile picture correctly
	@Override
	public void uploadProfilePicture(Long userId, MultipartFile file) throws IOException {
		User user = getUserById(userId);

		// Create uploads directory if it does not exist
		Path uploadPath = Paths.get(UPLOAD_DIR);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		// Save file
		String fileName = "user_" + userId + "_" + file.getOriginalFilename();
		Path filePath = uploadPath.resolve(fileName);
		Files.write(filePath, file.getBytes());

		// Update user profile picture URL
		user.setProfilePictureUrl("/uploads/" + fileName);
		userRepository.save(user);
	}
}
