package io.lpgph.ddd;

import io.lpgph.ddd.order.model.Item;
import io.lpgph.ddd.order.model.Order;
import io.lpgph.ddd.order.model.OrderRepository;
import io.lpgph.ddd.people.model.PeopleId;
import io.lpgph.ddd.people.model.PeopleRepo;
import io.lpgph.ddd.people.model.People;
import io.lpgph.ddd.shopping.model.ShopCard;
import io.lpgph.ddd.shopping.model.ShopCardId;
import io.lpgph.ddd.shopping.model.ShopCardRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@Slf4j
@SpringBootTest
public class UTest {
  @Autowired private PeopleRepo peopleRepo;

  @Autowired private ShopCardRepo shopCardRepo;

  @Autowired private OrderRepository orderRepo;

  @Test
  void orderSave() {
    orderRepo.save(Order.create(Set.of(Item.create(1L, 3), Item.create(2L, 5))));
  }

  @Test
  void orderUpdate() {
    orderRepo
        .findById(1L)
        .ifPresent(
            o -> {
              o.changeItems(Set.of(Item.create(8L, 3), Item.create(9L, 5)));
              orderRepo.saveAndFlush(o);
            });
  }

  @Test
  void shopCardSave() {
    shopCardRepo.save(ShopCard.create(ShopCardId.create(1L, 1L, 1L), 20));
  }

  @Test
  void shopCardUpdate() {
    shopCardRepo
        .findById(ShopCardId.create(1L, 1L, 1L))
        .ifPresent(
            o -> {
              o.changeNum(10);
              shopCardRepo.saveAndFlush(o);
            });
  }

  @Test
  public void save() {
    peopleRepo.save(People.create(PeopleId.create(3L), "test2"));
  }

  @Test
  void update() {
    peopleRepo
        .findById(PeopleId.create(2L))
        .ifPresent(
            o -> {
              o.changeName("张三222");
              peopleRepo.save(o);
            });
  }
}
