package io.lpgph.res.doc.common.bean.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;

@Schema(description = "分页返回")
@Getter
public class PageResult<T> {

  @Schema(description = "总条数")
  private final Long total;

  @Schema(description = "页码")
  private final Integer page;

  @Schema(description = "显示数量")
  private final Integer pageSize;

  @Schema(description = "返回数据")
  private final Collection<T> data;

  public Long getTotal() {
    return total;
  }

  public Integer getPage() {
    return page;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public Collection<T> getData() {
    return data;
  }

  private PageResult(Long total, Integer page, Integer pageSize, Collection<T> data) {
    this.total = total;
    this.page = page;
    this.pageSize = pageSize;
    this.data = data;
  }

  public static <T> PageResultBuilder<T> builder() {
    return new PageResultBuilder<T>();
  }

  public static <T> PageResultBuilder<T> withDefault() {
    return PageResult.<T>builder().total(0L).data(Collections.emptyList());
  }

  public static class PageResultBuilder<T> {

    private Long total;

    private Integer page;

    private Integer pageSize;

    private Collection<T> data;

    private PageResultBuilder() {}

    public PageResultBuilder<T> total(Long total) {
      this.total = total;
      return this;
    }

    public PageResultBuilder<T> page(Integer page) {
      this.page = page;
      return this;
    }

    public PageResultBuilder<T> pageSize(Integer pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    public PageResultBuilder<T> data(Collection<T> data) {
      this.data = data;
      return this;
    }

    @Override
    public String toString() {
      return "PageResultBuilder(total = "
          + total
          + ", page = "
          + page
          + ", pageSize = "
          + pageSize
          + ", data = "
          + data
          + ")";
    }

    public PageResult<T> build() {
      return new PageResult<T>(total, page, pageSize, data);
    }
  }
}
