package io.lpgph.ddd.user.model;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// CrudRepository
// 如果xml存在则优先执行xml中的
public interface UserRepository extends Repository<User, UserId> {


  Optional<User> findByName(String name);

  void save(User user);

  @Modifying
  @Query(value = "update jdbc_user set is_deleted=true where id = (:userId.id)")
  void delete(@Param("userId") UserId id);

  @Modifying
  @Query(value = "update jdbc_user set is_deleted=false where id = (:userId.id)")
  void recovery(@Param("id") Long id);
}
