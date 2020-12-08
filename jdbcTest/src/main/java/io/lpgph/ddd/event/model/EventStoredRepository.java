package io.lpgph.ddd.event.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventStoredRepository extends CrudRepository<EventStored, Long> {}
