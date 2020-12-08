package io.lpgph.ddd.people.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PeopleRepo extends JpaRepository<People, Long> {
  // public interface PeopleRepo extends JpaRepository<People, PeopleId> {

  @Modifying
  @Transactional
  @Query("update People p set p.deleted=true where p.id=:id")
  void deleteById(Long id);

  @Modifying
  @Transactional
  @Query("update People p set p.deleted=false where p.id=:id")
  void recoveryById(@Param("id") Long id);
}
