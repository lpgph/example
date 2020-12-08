package io.lpgph.ddd.user.model;

import io.lpgph.ddd.common.AbstractDomainEvent;
import io.lpgph.ddd.common.AggregationRootId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserEvent extends AbstractDomainEvent<UserId> {

}
