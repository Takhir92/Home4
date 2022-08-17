package HomeWork4;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewOdject4 {
    ResponseSpecification responseSpecification = null;
    RequestSpecification requestSpecification = null;
    RequestSpecification requestSpecification2 = null;
    private final String apiKey = "1473275520df4e3ba6c3764f701bcfcd";

    @BeforeEach
    void beforeTest() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
//                .expectHeader("Access-Control-Allow-Credentials", "true")
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("includeNutrition", "false")
                .log(LogDetail.ALL)
                .build();
        requestSpecification2 = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addParam("includeNutrition", "false")
                .addParam("item", "1 package tomato")
                .addParam("aisle", "Banana")
                .addParam("parse", "true")
//                .addQueryParam("hash", "f192dfb51ab2727b5d0b2d725861af887d2df755")
                .log(LogDetail.ALL)
                .build();

    }

    @Test
    void SuperChaynikTest() {
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?minProtein=5&maxCholine=66").prettyPeek()
                .then()
                .spec(responseSpecification);
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?maxSaturatedFat=256&maxVitaminB5=78").prettyPeek()
                .then()
                .spec(responseSpecification);
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?minCalories=478").prettyPeek()
                .then()
                .spec(responseSpecification);
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?sortDirection=vuga").prettyPeek()
                .then()
                .spec(responseSpecification);
        given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch?minIron=-8").prettyPeek()
                .then()
                .spec(responseSpecification);
    }


    @Test
    void SuperChaynikTest2(){
        Response response = given().spec(requestSpecification)
                .when()
                .get("https://api.spoonacular.com/mealplanner/takhir5/shopping-list?hash=9c8768b785fc233053b2783c360e37898d830750").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);

        Response response1 = given().spec(requestSpecification2)
                .when()
                .post("https://api.spoonacular.com/mealplanner/takhir5/shopping-list/items?hash=9c8768b785fc233053b2783c360e37898d830750").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);

        Response response2 = given().spec(requestSpecification2)
                .when()
                .delete("https://api.spoonacular.com/mealplanner/takhir5/shopping-list/items/{id}?hash=9c8768b785fc233053b2783c360e37898d830750").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(Response.class);

    }

}

