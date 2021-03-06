package io.lpgph.ddd.book.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 产品属性 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Book {
  //     public class Book extends AbstractAggregateRoot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Embedded private BookInfo bookInfo;

  //  @ManyToMany(fetch = FetchType.LAZY)
  //  @JoinTable(
  //      name = "book_peoples",
  //      joinColumns =
  //          @JoinColumn(name = "book_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
  //      inverseJoinColumns =
  //          @JoinColumn(name = "people_id", foreignKey =
  // @ForeignKey(ConstraintMode.NO_CONSTRAINT)),
  //      foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
  //  private List<People> peoples;

  // private List<Long> peoples;
  @ElementCollection
  @CollectionTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "book_id", nullable = false),
      foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
  //  @Column(name = "people_id", nullable = false)
  private Set<Author> authors;

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;

  @Version private Long version;

  //  public Book(String name) {
  //    this.name = name;
  //    registerEvent(new CreateBookEvent(this.getId(), "people_____" + this.name));
  //  }

  @Transient private final transient List<BookEvent> domainEvents = new ArrayList<>();

  public Book(String name, String remark, String tags) {
    this.name = name;
    this.bookInfo = new BookInfo(remark, tags);
    domainEvents.add(new CreateBookEvent(this.getId(), "people_____" + this.name));
  }

  public void borrow(long peopleId) {
    if (this.authors == null) this.authors = new HashSet<>();
    this.authors.add(Author.create(peopleId));
  }

  //  public void borrow(PeopleId peopleId) {
  //    if (this.peoples == null) this.peoples = new HashSet<>();
  //    this.peoples.add(peopleId);
  //  }

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
