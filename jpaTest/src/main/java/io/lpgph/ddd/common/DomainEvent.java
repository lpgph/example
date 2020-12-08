package io.lpgph.ddd.common;

public interface DomainEvent<E extends AggregationRootId> {

  E getRootId();

  void setRootId(E rootId);
}
