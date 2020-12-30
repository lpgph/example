package io.lpgph.ddd.event.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventStoredRepository extends CrudRepository<EventStored, Long> {

  List<EventStored> findAllByStatus(Integer... status);
}
