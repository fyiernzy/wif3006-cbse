package assignment.wif3006cbse.features.profile.domain.repository;

import assignment.wif3006cbse.features.profile.domain.entity.User;
import assignment.wif3006cbse.shared.spi.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(String email);
}
