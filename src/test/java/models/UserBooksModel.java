package models;

import lombok.Data;

import java.util.List;

@Data
public class UserBooksModel {
    String userId;
    String username;
    List<BookModel> books;
}
