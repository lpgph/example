package io.lpgph.ddd.book.model;

import io.lpgph.ddd.common.DomainEvent;
import io.lpgph.ddd.common.domain.AggregateRoot;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;
import java.util.stream.Collectors;

/** 产品属性 */
@EqualsAndHashCode(callSuper = false, of = "name")
@Getter
@Builder
@Table("jdbc_book")
public class Book extends AggregateRoot {

  @Embedded(prefix = "book_", onEmpty = Embedded.OnEmpty.USE_NULL)
  private BookId bookId;

  private String name;

  @MappedCollection(idColumn = "book_id")
  private Set<BookAttr> attrs;

  public static Book create(String name) {
    Book book = Book.builder().name(name).attrs(new HashSet<>()).build();
    book.registerEvent(new CreateBookEvent(book.getId(), "people_____" + name));
    return book;
  }

  public static Book create(BookId bookId, String name) {
    Book book = Book.builder().bookId(bookId).name(name).attrs(new HashSet<>()).build();
    book.registerEvent(new CreateBookEvent(book.getId(), "people_____" + name));
    return book;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void addAttr(Long propId, String name, Set<Long> valueIds) {
    if (this.attrs == null) this.attrs = new HashSet<>();
    this.attrs.add(
        new BookAttr(
            new PropId(propId),
            name,
            valueIds.stream()
                .map(PropValueId::new)
                .map(BookAttrValue::create)
                .collect(Collectors.toSet())));
  }

  public void changeAttr(Long propId, String name, Set<Long> valueIds) {
    this.remove(propId);
    this.attrs.add(
        new BookAttr(
            new PropId(propId),
            name,
            valueIds.stream()
                .map(PropValueId::new)
                .map(BookAttrValue::create)
                .collect(Collectors.toSet())));
  }

  public void remove(Long... propIds) {
    Set<Long> values = Arrays.stream(propIds).collect(Collectors.toSet());
    this.attrs.removeIf(item -> values.contains(item.getPropId().getId()));
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
