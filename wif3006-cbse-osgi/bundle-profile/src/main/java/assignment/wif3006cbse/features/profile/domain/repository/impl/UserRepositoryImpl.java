package assignment.wif3006cbse.features.profile.domain.repository.impl;

import assignment.wif3006cbse.features.profile.domain.entity.User;
import assignment.wif3006cbse.features.profile.domain.repository.UserRepository;
import assignment.wif3006cbse.shared.spi.FileBasedRepository;
import org.osgi.service.component.annotations.Component;

import java.util.Optional;

@Component(service = UserRepository.class)
public class UserRepositoryImpl extends FileBasedRepository<User, String> implements
    UserRepository {

    public UserRepositoryImpl() {
        super("users.dat", User::getId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return getStore().values().stream()
            .filter(u -> u.getEmail() != null && u.getEmail().equals(email))
            .findFirst();
    }
}
