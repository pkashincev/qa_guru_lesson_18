package api;

import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.DemoqaSpecs.*;

public class BooksApi {

    public static void deleteAllBooks(LoginResponseModel loginResponse) {
        given(noBodyRequestSpec)
                .param("UserId", loginResponse.getUserId())
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpecWithStatusCode204);
    }

    public static BooksModel getAllBooks() {
        return
                given(noBodyRequestSpec)
                        .when()
                        .get("/BookStore/v1/Books")
                        .then()
                        .spec(responseSpecWithStatusCode200)
                        .extract().as(BooksModel.class);
    }

    public static void addBooks(LoginResponseModel loginResponse, IsbnModel... isbn) {
        AddBooksRequestModel request = new AddBooksRequestModel();
        request.setUserId(loginResponse.getUserId());
        request.setCollectionOfIsbns(new ArrayList<>(Arrays.asList(isbn)));
        given(withBodyRequestSpec)
                .body(request)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpecWithStatusCode201);
    }

    public static List<BookModel> getUserBooks(LoginResponseModel loginResponse) {
        return
                given(noBodyRequestSpec)
                        .header("Authorization", "Bearer " + loginResponse.getToken())
                        .when()
                        .get("/Account/v1/User/" + loginResponse.getUserId())
                        .then()
                        .spec(responseSpecWithStatusCode200)
                        .extract().as(UserBooksModel.class).getBooks();
    }
}