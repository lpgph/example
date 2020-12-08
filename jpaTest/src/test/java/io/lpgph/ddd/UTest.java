package io.lpgph.ddd;

import io.lpgph.ddd.people.model.PeopleRepo;
import io.lpgph.ddd.people.model.People;
import io.lpgph.ddd.people.persistence.PeopleRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UTest {
  @Autowired private PeopleRepo peopleRepo;

  @Test
  public void save() {
    peopleRepo.save(new People("test"));
  }

}
