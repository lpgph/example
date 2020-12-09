package io.lpgph.ddd.event.model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoredRepository extends ReactiveCrudRepository<EventStored, Long> {}
