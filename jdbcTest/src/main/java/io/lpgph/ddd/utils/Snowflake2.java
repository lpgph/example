package io.lpgph.ddd.utils;

/**
 * Twitter_Snowflake<br>
 * SnowFlake的结构如下(每部分用-分开):<br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 * 得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T =
 * (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 * 10位的数据机器位，可以部署在1024个节点，包括5位dataCenterId和5位workerId<br>
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 * 加起来刚好64位，为一个Long型。<br>
 * Snowflake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * 默认情况下41bit的时间戳可以支持该算法使用到2082年，10bit的工作机器id可以支持1023台机器，序列号支持1毫秒产生4095个自增序列id
 *
 * <p>如果需要分布式使用可以考虑美团 https://github.com/Meituan-Dianping/Leaf
 */
public class Snowflake2 {
  // ==============================Fields===========================================
  /** 开始时间截 (2018-07-03) */

  /** 起始的时间戳 */
  private static final long START_TIMESTAMP = 1530607760000L;

  /** 每一部分占用的位数 */
  private static final long SEQUENCE_BIT = 12; // 序列号占用的位数

  private static final long MACHINE_BIT = 5; // 机器标识占用的位数
  private static final long DATA_CENTER_BIT = 5; // 数据中心占用的位数

  /** 每一部分的最大值 */
  private static final long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);

  private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
  private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

  //    private final static long MAX_DATA_CENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
  //    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
  //    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

  /** 每一部分向左的位移 */
  private static final long MACHINE_LEFT = SEQUENCE_BIT;

  private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
  private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

  /** 数据中心 */
  private final long dataCenterId;
  /** 机器标识 */
  private final long machineId;
  /** 毫秒内序列(0~4095) */
  private long sequence = 0L;

  /** 上次生成ID的时间截 */
  private long lastTimestamp = -1L;

  /**
   * 构造函数
   *
   * @param dataCenterId 数据中心ID (0~31)
   * @param machineId 机器标识 (0~31)
   */
  public Snowflake2(long dataCenterId, long machineId) {
    if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
      throw new IllegalArgumentException(
          "dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException(
          "machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    this.dataCenterId = dataCenterId;
    this.machineId = machineId;
  }

  /**
   * 获得下一个ID (该方法是线程安全的)
   *
   * @return SnowflakeId
   */
  public synchronized long nextId() {
    long timestamp = timeGen();
    // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
    if (timestamp < lastTimestamp) {
      throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
    }
    // 如果是同一时间生成的，则进行毫秒内序列
    if (timestamp == lastTimestamp) {
      // 相同毫秒内，序列号自增
      sequence = (sequence + 1) & MAX_SEQUENCE;
      // 同一毫秒的序列数已经达到最大
      if (sequence == 0L) {
        // 阻塞到下一个毫秒,获得新的时间戳
        timestamp = getNextMill();
      }
    } else {
      // 不同毫秒内，序列号置为0
      sequence = 0L;
    }

    lastTimestamp = timestamp;

    // 移位并通过或运算拼到一起组成64位的ID
    return (timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT // 时间戳部分
        | dataCenterId << DATA_CENTER_LEFT // 数据中心部分
        | machineId << MACHINE_LEFT // 机器标识部分
        | sequence; // 序列号部分
  }

  /**
   * 阻塞到下一个毫秒，直到获得新的时间戳
   *
   * @return 当前时间戳
   */
  private long getNextMill() {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  /**
   * 返回以毫秒为单位的当前时间
   *
   * @return 当前时间(毫秒)
   */
  private long timeGen() {
    return System.currentTimeMillis();
  }

  public static void main(String[] args) {
    Snowflake2 snowFlake = new Snowflake2(1, 2);
    //    LocalDate localDate = LocalDate.now();
    //    String date = localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    long start = System.currentTimeMillis();
    for (int i = 0; i < 1000; i++) {
      //      System.out.println(date + snowFlake.nextId());
      System.out.println(snowFlake.nextId());
    }
    System.out.println(System.currentTimeMillis() - start);
  }
}
