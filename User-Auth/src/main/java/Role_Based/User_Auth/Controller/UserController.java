package Role_Based.User_Auth.Controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Role_Based.User_Auth.Entity.User;
import Role_Based.User_Auth.Services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// ✅ 1. Get user profile
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	// ✅ 2. Update user details
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(userId, user));
	}

	// ✅ 3. Upload profile picture
	@PostMapping("/{userId}/upload-profile-picture")
	public ResponseEntity<String> uploadProfilePicture(@PathVariable Long userId,
			@RequestParam("file") MultipartFile file) {
		try {
			userService.uploadProfilePicture(userId, file);
			return ResponseEntity.ok("Profile picture uploaded successfully!");
		} catch (IOException e) {
			return ResponseEntity.internalServerError().body("Error uploading profile picture: " + e.getMessage());
		}
	}
}
