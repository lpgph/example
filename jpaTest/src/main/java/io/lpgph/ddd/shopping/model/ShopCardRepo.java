package io.lpgph.ddd.shopping.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCardRepo extends JpaRepository<ShopCard, ShopCardId> {

  @Query("select s from ShopCard s where s.id.userId = :userId")
  List<ShopCard> findAllByUserId(@Param("userId") long userId);
}
