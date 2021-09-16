package io.lpgph.ddd.user;

import io.lpgph.ddd.user.command.*;
import io.lpgph.ddd.user.exception.UserNameIsExistException;
import io.lpgph.ddd.user.exception.UserNotFoundException;
import io.lpgph.ddd.common.exception.ParamsNotEmptyException;
import io.lpgph.ddd.user.model.User;
import io.lpgph.ddd.user.model.UserProp;
import io.lpgph.ddd.user.model.UserRepository;
import io.lpgph.ddd.user.model.UserTag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserApplicationService {

  private final UserRepository userRepository;

  public void create(CreateUserCommand command) {
    if (userRepository.existsByName(command.getName())) {
      throw new UserNameIsExistException(command.getName());
    }
    User user =
        User.create(
            command.getName(),
            command.getTags().stream().map(UserTag::new).collect(Collectors.toSet()),
            new UserProp(command.getVip(), command.getLevel()),
            new HashSet<>());
    userRepository.save(user);
  }

  public void change(long id, ChangeUserCommand command) {
    if (userRepository.existsByNameAndIdNot(command.getName(), id)) {
      throw new UserNameIsExistException(command.getName());
    }
    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    user.change(
        command.getName(),
        command.getTags().stream().map(UserTag::new).collect(Collectors.toSet()),
        new UserProp(command.getVip(), command.getLevel()),
        new HashSet<>());
    userRepository.save(user);
  }

  private Iterable<User> findAllById(Collection<Long> ids) {
    Set<Long> params =
        Optional.ofNullable(ids).orElse(new HashSet<>()).stream()
            .filter(Objects::nonNull)
            .filter(item -> item != 0)
            .collect(Collectors.toSet());
    if (params.isEmpty()) {
      throw new ParamsNotEmptyException();
    }
    Iterable<User> entities = userRepository.findAllById(params);
    if (!entities.iterator().hasNext()) throw new UserNotFoundException();
    return entities;
  }
}
