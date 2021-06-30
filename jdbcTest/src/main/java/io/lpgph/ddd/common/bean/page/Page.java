package io.lpgph.ddd.common.bean.page;

/***
 * pageNo pageSize
 * limit offset
 */
public class Page {
  private static final int maxLimit = 100;

  private static final int defaultLimit = 20;

  private static final int defaultPage = 1;

  private static final int defaultOffset = 0;

  private final int offset;

  private final int limit;

  public static Page create(final Integer page, final Integer size) {
    int limit = (size == null || size < 1) ? defaultLimit : Math.min(size, maxLimit);
    int offset = (((page == null || page < 1) ? defaultPage : page) - 1) * limit;
    return new Page(offset, limit);
  }

  public static Page create2(final Integer offset, final Integer limit) {
    int limit2 = (limit == null || limit < 1) ? defaultLimit : Math.min(limit, maxLimit);
    int offset2 = ((offset == null || offset < 0) ? defaultOffset : offset) * limit2;
    return new Page(offset2, limit2);
  }

  private Page(int offset, int limit) {
    this.offset = offset;
    this.limit = limit;
  }

  public int getPage() {
    return this.offset / this.limit + 1;
  }

  public int getPageSize() {
    return this.limit;
  }

  public int getOffset() {
    return this.offset;
  }

  public int getLimit() {
    return this.limit;
  }
}
