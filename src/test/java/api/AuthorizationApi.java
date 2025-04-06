package api;

import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.DemoqaSpecs.*;
import static utils.TestData.*;

public class AuthorizationApi {

    public static LoginResponseModel login() {
        LoginRequestModel request = new LoginRequestModel(DEMOQA_LOGIN, DEMOQA_PASSWORD);
        return
                given(withBodyRequestSpec)
                        .body(request)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(getResponseSpecByStatusCode(200))
                        .extract().as(LoginResponseModel.class);
    }
}
