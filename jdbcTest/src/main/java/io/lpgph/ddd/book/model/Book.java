package io.lpgph.ddd.book.model;

import io.lpgph.ddd.book.event.CreateBookEvent;
import io.lpgph.ddd.book.event.RemoveBookEvent;
import io.lpgph.ddd.common.StateEnum;
import io.lpgph.ddd.common.domain.AbstractAggregateRoot;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;
import java.util.stream.Collectors;

/** 产品属性 */
@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
@Builder
@Table("jdbc_book")
public class Book extends AbstractAggregateRoot {

  @Id private final Long id;
  //  private BookId id;

  @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
  private BookInfo info;

  private String name;

  @MappedCollection(idColumn = "book_id")
  private Set<BookAttr> attrs;

  public static Book create(String name) {
    Book book = Book.builder().name(name).attrs(new HashSet<>()).build();
    book.setState(StateEnum.ACTIVATED);
    book.registerEvent(new CreateBookEvent(book.getId(), "people_____" + name));
    return book;
  }

  public void changeInfo(BookInfo info) {
    this.info = info;
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
    this.setState(StateEnum.DEACTIVATED);
  }

  public void activated() {
    this.setState(StateEnum.ACTIVATED);
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
