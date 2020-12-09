package io.lpgph.ddd.user.model;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


// CrudRepository
// 如果xml存在则优先执行xml中的
public interface UserRepository extends ReactiveCrudRepository<User, UserId> {


  Mono<User> findByName(String name);
//
//  @Modifying
//  @Query(value = "update jdbc_user set is_deleted=true where id = (:userId.id)")
//  void delete(@Param("userId") UserId id);
//
//  @Modifying
//  @Query(value = "update jdbc_user set is_deleted=false where id = (:userId.id)")
//  void recovery(@Param("userId") UserId id);
}
