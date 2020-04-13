package com.example.restassuredwm;

// import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.when;


@SpringBootTest
public class RestassuredWmApplicationTests {

    private static WireMockServer wireMockServer;
    private static final int wmPort = 9091;

    private static final String APPLICATION_JSON = "application/json";

    @BeforeClass
    public static void before() {
        wireMockServer = new WireMockServer(wmPort);
        wireMockServer.start();
        configureFor("localhost", wmPort);


        stubFor(get("/test22").willReturn(
                aResponse().withStatus(200)
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody("'catch': 'twenty 2'")));

        RestAssured.port = wmPort;

    }

    @AfterClass
    public static void after() throws Exception {
        System.out.println("Running: tearDown");
        wireMockServer.stop();
    }


    @Test
    public void contextLoads() throws InterruptedException {
        System.out.println("katitm ukaan");
        Thread.sleep(10000);
    }

/*
    @Test
    public void givenUrl_whenCheckingFloatValuePasses_thenCorrect() {
        get("/events?id=390").then().assertThat()
                .body("odd.ck", equalTo(12.2f));
    }
*/


    @Test
    public void catch22() {
        Response response = when().get("/test22");
        Object bodyO = response.getBody().asString();
        Object headers = response.getHeaders();
        System.out.println();
    }

}
