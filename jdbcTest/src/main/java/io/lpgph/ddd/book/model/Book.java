package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.common.StateEnum;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/** 产品属性 */
@EqualsAndHashCode(of = "id")
@Getter
@Builder
@Table("jdbc_book")
public class Book {

  @Id private final Long id;
  //  private BookId id;

  private String name;

  //  @Column("is_deleted")
  private StateEnum state;

  @MappedCollection(idColumn = "book_id")
  private Set<BookAttr> attrs;

  /** 创建时间 */
  @CreatedDate private LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private Long modifiedBy;

  @Version private Long version;

  public static Book create(String name) {
    Book book = Book.builder().name(name).state(StateEnum.ACTIVATED).attrs(new HashSet<>()).build();
    book.registerEvent(new CreateBookEvent(book.getId(), "people_____" + name));
    return book;
  }

  private final transient @Transient List<DomainEvent> domainEvents = new ArrayList<>();

  protected void registerEvent(DomainEvent event) {
    Assert.notNull(event, "Domain event must not be null!");
    this.domainEvents.add(event);
  }

  @DomainEvents
  protected Collection<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  @AfterDomainEventPublication
  protected void clearDomainEvents() {
    domainEvents.clear();
  }

  public void remove() {
    this.registerEvent(new RemoveBookEvent(this.id));
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void addAttr(Long propId, String name, Set<Long> valueIds) {
    if (this.attrs == null) this.attrs = new HashSet<>();
    this.attrs.add(
        new BookAttr(
            propId,
            //            new PropId(propId),
            name,
            valueIds.stream()
                //                .map(PropValueId::new)
                .map(BookAttrValue::new)
                .collect(Collectors.toSet())));
  }

  public void changeAttr(Long propId, String name, Set<Long> valueIds) {
    this.remove(propId);
    this.attrs.add(
        new BookAttr(
            propId,
            //            new PropId(propId),
            name,
            valueIds.stream()
                //                .map(PropValueId::new)
                .map(BookAttrValue::new)
                .collect(Collectors.toSet())));
  }

  public void remove(Long... propIds) {
    Set<Long> values = Arrays.stream(propIds).collect(Collectors.toSet());
    this.attrs.removeIf(item -> values.contains(item.getPropId()));
  }

  public void deactivated() {
    //    this.state = StateEnum.DEACTIVATED;
  }

  public void activated() {
    //    this.state = StateEnum.ACTIVATED;
  }

  //  @Transient private final transient List<BookEvent> domainEvents = new ArrayList<>();
  //
  //  @DomainEvents
  //  protected Collection<DomainEvent> domainEvents() {
  //    domainEvents.forEach(event -> event.setBookId(this.getId()));
  //    return Collections.unmodifiableList(domainEvents);
  //  }
  //
  //  @AfterDomainEventPublication
  //  protected void clearDomainEvents() {
  //    domainEvents.clear();
  //  }
}
