package io.lpgph.ddd.people.persistence;

import io.lpgph.ddd.people.model.People;
import io.lpgph.ddd.people.model.PeopleId;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PeopleRepoImpl {
  private final EntityManager em;

  public PeopleRepoImpl(EntityManager em) {
    this.em = em;
  }

  public void save(People people) {
    if (people.getId() == null) {
      em.persist(people);
    } else {
      em.merge(people);
    }
    em.flush();
  }

  public People findById(PeopleId peopleId) {
    return em.find(People.class, peopleId.getId());
  }
}
