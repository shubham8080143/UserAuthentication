package Role_Based.User_Auth.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import Role_Based.User_Auth.Entity.User;

public interface UserService {

	// ✅ Upload Profile Picture
	void uploadProfilePicture(Long userId, MultipartFile file) throws IOException;

	// ✅ Get All Users
	List<User> getAllUsers();

	// ✅ Get User by ID
	User getUserById(Long id);

	// ✅ Create New User
	User createUser(User user);

	// ✅ Update User
	User updateUser(Long id, User updatedUser);

	// ✅ Delete User
	void deleteUser(Long id);

	// ✅ Load User by Username (Required for Spring Security)
	UserDetails loadUserByUsername(String username);
}
