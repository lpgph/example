package io.lpgph.ddd.user.model;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

  void save(User user);

  Optional<User> findById(UserId id);

  void remove(UserId id);

  void removeByIds(Collection<Long> collection);

  void recovery(UserId id);

  void recoveryByIds(Collection<Long> collection);
}
