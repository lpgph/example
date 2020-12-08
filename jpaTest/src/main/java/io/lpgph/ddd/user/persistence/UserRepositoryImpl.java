package io.lpgph.ddd.user.persistence;

import io.lpgph.ddd.user.model.User;
import io.lpgph.ddd.user.model.UserId;
import io.lpgph.ddd.user.model.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public void save(User user) {

    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.empty();
    }

    @Override
    public void remove(UserId id) {

    }

    @Override
    public void removeByIds(Collection<Long> collection) {

    }

    @Override
    public void recovery(UserId id) {

    }

    @Override
    public void recoveryByIds(Collection<Long> collection) {

    }
}
