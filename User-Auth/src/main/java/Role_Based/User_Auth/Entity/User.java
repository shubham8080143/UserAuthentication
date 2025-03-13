package Role_Based.User_Auth.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String email;
	private String password;
	private String profilePictureUrl;
	@Enumerated(EnumType.STRING) // Ensures role is stored as a string in DB
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Change this based on your logic
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Change this based on your logic
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Change this based on your logic
	}

	@Override
	public boolean isEnabled() {
		return true; // Change this based on your logic
	}
}
