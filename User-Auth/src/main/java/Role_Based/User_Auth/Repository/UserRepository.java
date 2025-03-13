package Role_Based.User_Auth.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Role_Based.User_Auth.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
