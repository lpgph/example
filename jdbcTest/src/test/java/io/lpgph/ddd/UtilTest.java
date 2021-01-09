package io.lpgph.ddd;

import com.hankcs.hanlp.HanLP;
import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.user.model.CreateUserEvent;
import io.lpgph.ddd.user.model.UserId;
import io.lpgph.ddd.utils.json.JsonUtil;
import io.lpgph.ddd.utils.json.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
public class UtilTest {

  @Test
  public void testAry() {
    Set<Cat> cats = new HashSet<>();
    Collections.addAll(cats, new Cat(1, "aaaa"), new Cat(2, "bbbb"), new Cat(3, "cccc"));
    log.info("\n\n{}\n\n", JsonUtil.toJson(cats));
    Cat c2 =
        cats.stream()
            .filter(item -> item.getId().equals(2))
            .findFirst()
            .orElseThrow(() -> new RuntimeException(""));
    c2.change("张三");
    log.info("\n\n{}\n\n", JsonUtil.toJson(cats));
  }

  public List<Integer> getDistinctRandom(int minNum, int maxMum, int numRange) {
    if (numRange <= 0 || numRange > (maxMum - minNum + 1) || maxMum - minNum < 0) {
      System.out.println("传入数字不正确");
      return null;
    }
    // 返回的结果集合
    List<Integer> result = new ArrayList<>();
    // 过滤的数据集合最小数到最大数的数字集合
    List<Integer> list = new ArrayList<>();
    for (int i = minNum; i < maxMum + 1; i++) {
      list.add(i);
    }
    // 取出不重复的随机数
    int forNum = 0;
    for (int i = 0; i < numRange; i++) {
      int num = new Random().nextInt(list.size());
      result.add(list.remove(num));
      ++forNum;
    }
    return result;
  }

  @Test
  void genId2() {
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < 50; i++) {
      //      log.info("{}", getDistinctRandom(1000, 100000, 1).get(0));
      log.info("{}", random.nextInt(100000));
    }
  }

  @Test
  void genId() {
    log.info("{}", System.currentTimeMillis());
    log.info("{}", Instant.now().toEpochMilli());
    log.info("{}", Instant.now().getEpochSecond());
    log.info("{}", LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    log.info("{}", LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
    String millis = String.valueOf(System.currentTimeMillis());
    log.info(millis.substring(4));
    log.info("{}", LocalDateTime.now().getNano());
    log.info("{}", LocalDateTime.now().getLong(ChronoField.MILLI_OF_DAY));
    log.info("{}", LocalDateTime.now().getLong(ChronoField.SECOND_OF_DAY));
    log.info("{}", LocalDateTime.now().getLong(ChronoField.MICRO_OF_SECOND));
  }

  @Test
  void jsonTest2() throws ClassNotFoundException {
    DomainEvent event = new CreateUserEvent(UserId.create(1L), "ttttt");
    String jsonStr = JsonUtil.toJson(event);
    log.info("\njsonStr {}\n", jsonStr);
    String clsName = event.getClass().getName();
    log.info("\nclassName {}\n", clsName);

    //    DomainEvent e1 = JsonUtil.fromJson(jsonStr, CreateUserEvent.class);
    DomainEvent e1 = (DomainEvent) JsonUtil.fromJson(jsonStr, Class.forName(clsName));
    assert e1 != null;
    log.info("\ne1\n{}\n", JsonUtil.toJson(e1));

    CreateUserEvent e2 = (CreateUserEvent) JsonUtil.fromJson(jsonStr, Class.forName(clsName));
    //    CreateUserEvent e2 = JsonUtil.fromJson(jsonStr, CreateUserEvent.class);
    assert e2 != null;
    log.info("\ne2\n{}\n", JsonUtil.toJson(e2));
  }

  @Test
  void test() {
    List<String> sentence =
        List.of("回力官方旗舰店男女鞋2020秋冬潮流休闲跑步鞋网面透气情侣运动鞋", "FreeTie经典帆布鞋 小米休闲鞋子 中帮 低帮 男款 女款 小白鞋");
    sentence.forEach(
        str -> {
          System.out.println(JsonUtil.toJson(HanLP.segment(str)));
        });
  }

  @Test
  void jsonTest() {
    String str = "[1,2,3,4,5,3,3,3,6,7,8,9,10,11,1,12]";
    Set<Integer> ary = JsonUtil.fromJson(str, new TypeReference<>() {});
    assert ary != null;
    ary.forEach(item -> log.info("{}", item));

    LinkedHashSet<Integer> ary2 = JsonUtil.fromJson(str, new TypeReference<>() {});
    assert ary2 != null;
    ary2.forEach(item -> log.info("{}", item));
  }

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

  @Test
  void testEquals() {
    Set<Map<String, Integer>> m1 = Set.of(Map.of("t", 1, "a", 3));
    Set<Map<String, Integer>> m2 = Set.of(Map.of("a", 3, "t", 1));
    System.out.println(Objects.equals(m1, m2));
  }
}
