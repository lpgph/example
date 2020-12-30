package io.lpgph.ddd.book.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/** 产品属性 */
@Getter
@Builder
@Table("jdbc_book")
public class Book {

  @Id private final Long id;

  private String name;

  @MappedCollection(idColumn = "book_id")
  private Set<BookAttr> attrs;


  /** 创建时间 */
  @CreatedDate private final LocalDateTime gmtCreate;

  /** 创建人 */
  @CreatedBy private final Long createdBy;

  /** 最后修改时间 */
  @LastModifiedDate private final LocalDateTime gmtModified;

  /** 修改入 */
  @LastModifiedBy private final Long modifiedBy;

  @Version private final Long version;

  @Transient private final transient List<BookEvent> domainEvents = new ArrayList<>();

  public static Book create(String name) {
    Book book = Book.builder().name(name).attrs(new HashSet<>()).build();
    book.domainEvents.add(new CreateBookEvent(book.getId(), "people_____" + name));
    return book;
  }

  public void changeName(String name) {
    this.name = name;
  }



  public void addAttr(Long propId, String name, Set<Long> valueIds) {
    if (this.attrs == null) this.attrs = new HashSet<>();
    this.attrs.add(
        new BookAttr(
            null,
            propId,
            name,
            valueIds.stream().map(BookAttrValue::create).collect(Collectors.toSet())));
  }

  public void changeAttr(Long attrId, Long propId, String name, Set<Long> valueIds) {
    this.remove(attrId);
    this.attrs.add(
        new BookAttr(
            attrId,
            propId,
            name,
            valueIds.stream().map(BookAttrValue::create).collect(Collectors.toSet())));
  }

  public void remove(Long... attrIds) {
    Set<Long> values = Arrays.stream(attrIds).collect(Collectors.toSet());
    this.attrs.removeIf(item -> values.contains(item.getId()));
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
