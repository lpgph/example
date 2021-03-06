package io.lpgph.ddd.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 产品属性 */
@NoArgsConstructor
@Setter
@Getter
@Table("jdbc_book")
public class Book {

  @Id
  private Long id;

  private String name;

  @MappedCollection(idColumn = "book_id")
  private Set<UserItem> users = new HashSet<>();

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;

  @Version private Long version;

  @Transient private final transient List<BookEvent> domainEvents = new ArrayList<>();

  public Book(String name) {
    this.name = name;
    domainEvents.add(new CreateBookEvent(this.getId(), "people_____" + this.name));
  }

  public void borrow(UserItem user) {
    if (this.users == null) this.users = new HashSet<>();
    this.users.add(user);
  }

  @DomainEvents
  private List<BookEvent> domainEvents() {
    domainEvents.forEach(event -> event.setBookId(this.id));
    return domainEvents;
  }

  @AfterDomainEventPublication
  private void callbackMethod() {
    domainEvents.clear();
  }
}
