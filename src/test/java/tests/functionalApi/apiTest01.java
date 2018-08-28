package tests.functionalApi;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;




public class apiTest01 {


    String apiId = "c4df8ce23747b22b9c1f9b1fc84fc2a4";

    @Test
    public void testWeatherApi01(){

        RestAssured.defaultParser = Parser.JSON;

        Response response = RestAssured.get("http://api.openweathermap.org/data/2.5/weather?q=Montreal&appid=" + apiId);
        int responseCode = response.getStatusCode();

        Assertions.assertEquals("200", String.valueOf(responseCode), "Response code is Ok");

    }

    @Test
    public void testWeatherApi02(){

        Response response = RestAssured.get("http://api.openweathermap.org/data/2.5/weather?q=Montreal" + "ERROR" + "&appid=" + apiId);
        int responseCode = response.getStatusCode();

        Assertions.assertEquals("404", String.valueOf(responseCode), "Response code is Ok");

    }

    @Test
    public void testWeatherApi03(){

        Response response = RestAssured.get("http://api.openweathermap.org/data/2.5/weather?q=Montreal&appid=" + apiId);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        String coordinates = "[lon:-73.61, lat:45.5]";

        Assertions.assertEquals("Montreal", jsonPath.getString("name"), "Name of city is Ok");
        Assertions.assertEquals(coordinates, jsonPath.getString("coord"), "Coordinates is Ok");


    }


    @Test
    public void testWeatherApi04(){

        //Response response = RestAssured.get("http://api.openweathermap.org/data/2.5/weather?q=Montreal" + "ERROR" + "&appid=" + apiId);

        given().
                when().get("http://api.openweathermap.org/data/2.5/weather?q=Montreal&appid=" + apiId).
                then().assertThat().body("sys.id", equalTo(3829))
                .assertThat().body("name", equalTo("Montreal"))
                .assertThat().body("sys.country", equalTo("CA"))
                .assertThat().body("id", equalTo(6077243));


    }

    @Test
    public void testWeatherApi05(){

        given().
                when().get("http://api.openweathermap.org/data/2.5/weather?q=Montreal&appid=" + apiId).
                then().assertThat().body("main.temp", is(notNullValue()))
                .assertThat().body("main.temp", is(notNullValue()))
                .assertThat().body("main.pressure", is(notNullValue()))
                .assertThat().body("main.humidity", is(notNullValue()))
                .assertThat().body("main.temp_min", is(notNullValue()))
                .assertThat().body("main.temp_max", is(notNullValue()))
                .assertThat().body("visibility", is(notNullValue()))
                .assertThat().body("wind.speed", is(notNullValue()))
                .assertThat().body("wind.deg", is(notNullValue()));






    }
}
