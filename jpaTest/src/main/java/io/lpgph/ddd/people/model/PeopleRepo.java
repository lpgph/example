package io.lpgph.ddd.people.model;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PeopleRepo extends Repository<People, PeopleId> {
  // public interface PeopleRepo extends JpaRepository<People, PeopleId> {
  People save(People p);

  Optional<People> findById(PeopleId id);
}
