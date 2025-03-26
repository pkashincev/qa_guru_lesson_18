package tests;

import api.AuthorizationApi;
import api.BooksApi;
import com.codeborne.selenide.Selenide;
import helpers.WithLogin;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.RandomUtils.getRandomInt;

public class DemoqaBooksShopTests extends BaseTest {

    @Test
    @WithLogin
    @DisplayName("Удаление книги из профиля")
    void deleteBookTest() {
        LoginResponseModel loginResponse = step("Авторизоваться по API ", () ->
                AuthorizationApi.login());
        step("Удалить по API все книги из профиля пользователя", () ->
                BooksApi.deleteAllBooks(loginResponse));
        List<BookModel> books = step("Получить по API список доступных книг", () ->
                BooksApi.getAllBooks().getBooks());
        BookModel book = step("Выбрать произвольную книгу из полученного списка", () ->
                books.get(getRandomInt(0, books.size() - 1)));
        step("Добавить по API выбранную книгу в профиль пользователя", () ->
                BooksApi.addBooks(loginResponse, new IsbnModel(book.getIsbn())));
        step("Открыть браузер и удалить книгу из профиля через UI", () -> {
            open("/profile");
            $("#delete-record-undefined").click();
            $("#closeSmallModal-ok").click();
            Selenide.confirm("Book deleted.");
        });
        step("Проверить через UI, что удаленная книга отсутствует в профиле", () -> {
            $(".ReactTable").shouldNotHave(text(book.getTitle()));
        });
        step("Проверить через API, что у пользователя нет ни одной книги", () -> {
            assertThat(BooksApi.getUserBooks(loginResponse)).isEmpty();
        });
    }
}