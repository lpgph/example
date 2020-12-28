package io.lpgph.ddd;

import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class UtilTest {

  @Test
  void streamReduce() {
    Map<Integer, Set<Integer>> origin =
        Map.of(1, Set.of(2, 3), 4, Set.of(5, 6), 7, Set.of(8, 9, 10));
    int count = origin.values().stream().map(Set::size).reduce((a, b) -> a * b).orElse(0);
    System.out.println("总数 " + count);

    Set<Set<Map<Integer, Integer>>> filterMap =
        origin.keySet().stream()
            .map(
                key ->
                    origin.get(key).stream()
                        .map(
                            value -> {
                              Map<Integer, Integer> map = new HashMap<>();
                              map.put(key, value);
                              return map;
                            })
                        .collect(Collectors.toSet()))
            .collect(Collectors.toSet());
    System.out.println(JsonUtil.toJson(filterMap));

    Set<Map<Integer, Integer>> sku2 = new HashSet<>();
    for (Set<Map<Integer, Integer>> list : filterMap) {
      if (sku2.isEmpty()) {
        sku2 = list;
      } else {
        // java8新特性，stream流
        sku2 =
            sku2.stream()
                .flatMap(
                    item ->
                        list.stream()
                            .map(
                                item2 -> {
                                  Map<Integer, Integer> map = new HashMap<>();
                                  map.putAll(item);
                                  map.putAll(item2);
                                  return map;
                                }))
                .collect(Collectors.toSet());
      }
    }

    log.info(JsonUtil.toJson(sku2));

    HashSet<Map<Integer, Integer>> sku =
        filterMap.stream()
            .reduce(
                new HashSet<>(),
                (maps, maps2) -> {
                  System.out.println();
                  if (maps.isEmpty()) {
                    maps = new HashSet<>(maps2);
                  } else {
                    maps =
                        new HashSet<>(
                            maps.stream()
                                .flatMap(
                                    item ->
                                        maps2.stream()
                                            .map(
                                                item2 -> {
                                                  Map<Integer, Integer> map = new HashMap<>();
                                                  map.putAll(item);
                                                  map.putAll(item2);
                                                  return map;
                                                }))
                                .collect(Collectors.toSet()));
                  }
                  return maps;
                },
                (maps, maps2) -> {
                  maps.addAll(maps2);
                  return maps;
                });
    log.info(JsonUtil.toJson(sku));
  }
}
