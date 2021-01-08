package io.lpgph.ddd.member.model;

import io.lpgph.ddd.user.model.CreateUserEvent;

public final class MemberFactory {

  public static Member create(MemberId id, String name) {
    Member member = Member.builder().id(id).name(name).build();
    member.registerEvent(new CreateUserEvent("user_____" + name));
    return member;
  }
}
