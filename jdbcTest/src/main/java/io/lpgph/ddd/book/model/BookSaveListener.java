package io.lpgph.ddd.book.model;

import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.event.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookSaveListener extends AbstractRelationalEventListener<Book> {

  private final BookAuditorRepo bookAuditorRepo;
  private final BookRepo bookRepo;

  public BookSaveListener(BookAuditorRepo bookAuditorRepo, BookRepo bookRepo) {
    this.bookAuditorRepo = bookAuditorRepo;
    this.bookRepo = bookRepo;
  }

  private Book book2;
  private Boolean isNew = false;

  @Override
  protected void onBeforeSave(BeforeSaveEvent<Book> event) {
    super.onBeforeSave(event);
    if (event.getEntity() == null) return;
    book2 = event.getEntity();
    if (book2.getId() == null) {
      isNew = true;
    } else {
      bookRepo.findById(book2.getId()).ifPresent(o -> isNew = false);
    }
  }

  @Override
  protected void onAfterSave(AfterSaveEvent<Book> event) {
    super.onAfterSave(event);
    if (event.getEntity() == null) return;

    Book book = event.getEntity();
    log.info("\n\n{}\n\n{}\n\n\n\n", JsonUtil.toJson(book2), JsonUtil.toJson(book));
    bookAuditorRepo.save(
        new BookAuditor(
            book.getId(),
            isNew ? EventTypeEnum.SAVE : EventTypeEnum.UPDATE,
            JsonUtil.toJson(book2),
            JsonUtil.toJson(book),
            book.getModifiedBy(),
            book.getGmtModified()));
  }

  @Override
  protected void onAfterDelete(AfterDeleteEvent<Book> event) {
    super.onAfterDelete(event);
  }

  @Override
  protected void onBeforeDelete(BeforeDeleteEvent<Book> event) {
    super.onBeforeDelete(event);
    Book book = event.getEntity();
    log.info("\n\n\nonBeforeSave \n{}\n\n\n\n", JsonUtil.toJson(book));
  }
}
