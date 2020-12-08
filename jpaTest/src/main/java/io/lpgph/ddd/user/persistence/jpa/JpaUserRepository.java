package io.lpgph.ddd.user.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {

}
