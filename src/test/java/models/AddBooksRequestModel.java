package models;

import lombok.Data;

import java.util.List;

@Data
public class AddBooksRequestModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}