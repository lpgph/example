package io.lpgph.ddd.book.model;

import io.lpgph.ddd.auditor.model.BookAuditor;
import io.lpgph.ddd.auditor.model.BookAuditorRepo;
import io.lpgph.ddd.book.constant.e.EventTypeEnum;
import io.lpgph.ddd.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.event.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookRelationalEventListener extends AbstractRelationalEventListener<Book> {

  private final BookAuditorRepo bookAuditorRepo;
  private final BookRepo bookRepo;

  public BookRelationalEventListener(BookAuditorRepo bookAuditorRepo, BookRepo bookRepo) {
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
      // 针对自定义ID
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
        BookAuditor.create(
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
    if (event.getEntity() == null) return;
    book2 = event.getEntity();
    //    log.info("\n\n\nonAfterDelete \n{}\n\n\n\n", JsonUtil.toJson(book2));
  }

  @Override
  protected void onBeforeDelete(BeforeDeleteEvent<Book> event) {
    super.onBeforeDelete(event);
    if (event.getEntity() == null) return;
    Book book = event.getEntity();
    //    log.info("\n\n\nonBeforeSave \n{}\n\n\n\n", JsonUtil.toJson(book));
    bookAuditorRepo.save(
        BookAuditor.create(
            book.getId(),
            EventTypeEnum.DELETE,
            JsonUtil.toJson(book2),
            JsonUtil.toJson(book),
            book.getModifiedBy(),
            book.getGmtModified()));
  }
}
