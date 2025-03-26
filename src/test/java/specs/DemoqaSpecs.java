package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.emptyOrNullString;

public class DemoqaSpecs {
    public static RequestSpecification withBodyRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static RequestSpecification noBodyRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().headers();

    public static ResponseSpecification responseSpecWithStatusCode200 = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpecWithStatusCode201 = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification responseSpecWithStatusCode204 = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(204)
            .expectBody(emptyOrNullString())
            .build();
}