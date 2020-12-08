package io.lpgph.ddd.common;

import java.util.List;

public interface DomainEventPush<E extends DomainEvent<R>, R extends AggregationRootId> {

  List<E> domainEvents(R id);

  void callbackMethod();
}
