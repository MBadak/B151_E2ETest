package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojos.Roompojo;

import static base_url.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ApiRoomStepDefs {
    Response response;
    Roompojo expectedData;
    @Given("GET Request gönderilir")
    public void get_request_gönderilir() {

        //Set the Url --> https://medunna.com/api/rooms/72401
        spec.pathParams("first","api","second","rooms", "third", 72401);

        // Set the expected Data
        expectedData= new Roompojo(571571,"TWIN",false,571.00, "Schönes Raum");


        //Send the Request and get the response
        response= given(spec).when().get("{first}/{second}/{third}");


    }

    @Then("Body dogrulanir")
    public void body_dogrulanir() throws JsonProcessingException {
        Roompojo actualData = new ObjectMapper().readValue(response.asString(), Roompojo.class);
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualData.getRoomType());
        assertEquals(expectedData.isStatus(), actualData.isStatus());
        assertEquals(expectedData.getPrice(), actualData.getPrice());
        assertEquals(expectedData.getDescription(), actualData.getDescription());



    }
}