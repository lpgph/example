package io.lpgph.ddd.user.representation;

import lombok.Data;

@Data
public class UserBookResult {
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookName;
}
