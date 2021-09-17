package io.lpgph.ddd.user.model;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// CrudRepository
// 如果xml存在则优先执行xml中的
public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByName(String name);

  List<User> findAllByProp(UserProp prop);

  boolean existsByName(String name);

  boolean existsByNameAndIdNot(String name, long id);

  //  @Query(value = "update User u set is_deleted=true where u.id = :#{#user.id}")
  @Transactional
  @Modifying
  @Query(value = "update jdbc_user  set is_deleted=true where id = :#{#user.id}")
  void remove(User user);

  @Transactional
  @Modifying
  @Query(value = "update jdbc_user set is_deleted=false where user_id = :id.id")
  void recovery(Long id);

  //  @Modifying
  //  @Transactional
  //  @Query(value = "update user set is_deleted=false where id = (:id)")
  //  void recoveryById(@Param("id") Long id);
  //
  //  @Modifying
  //  @Transactional
  //  @Query(value = "update user set is_deleted=false where id in (:ids)")
  //  void recoveryAllById(@Param("collection") Collection<Long> ids);
  //
  //  @Modifying
  //  @Transactional
  //  @Query(value = "update user set is_deleted=true where id = (:id)")
  //  void removeById(@Param("id") Long id);
  //
  //  @Transactional
  //  @Query("update Book set is_deleted = 1 where id =:id")
  //  void remove(Book book);
  //
  //  @Modifying
  //  @Transactional
  //  @Query(value = "update user set is_deleted=true where id in (:ids)")
  //  void removeAllById(@Param("collection") Collection<Long> ids);
}
