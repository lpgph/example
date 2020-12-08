package io.lpgph.ddd.people.representation;

import lombok.Data;

@Data
public class PeopleBookResult {
    private Long peopleId;
    private String peopleName;
    private Long bookId;
    private String bookName;
}
