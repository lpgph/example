package io.lpgph.ddd.user.model;


public class UserId {

  private final Long id;

  protected UserId(Long id) {
    this.id = id;
  }

  public static UserId create(Long id) {
    return new UserId(id);
  }

  public Long getId() {
    return id;
  }
}
