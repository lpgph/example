package io.lpgph.ddd.event.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventStoredRepository extends CrudRepository<EventStored, Long> {

  List<EventStored> findAllByStatusIn(Set<Integer> status);
}
