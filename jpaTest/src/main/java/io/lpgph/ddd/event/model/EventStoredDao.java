package io.lpgph.ddd.event.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStoredDao extends JpaRepository<EventStored, Long> {}
