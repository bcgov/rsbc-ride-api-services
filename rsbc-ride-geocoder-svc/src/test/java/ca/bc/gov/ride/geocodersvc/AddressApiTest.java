package ca.bc.gov.ride.geocodersvc;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressApiTest {

    @LocalServerPort
    Integer port;

    @Autowired
    WebTestClient webTestClient;

    @Value("${application.path.geocodersvc}")
    private String pathGeocoderSvc;

    @Value("${application.security.username}")
    private String securityUsername;

    @Value("${application.security.password}")
    private String securitYPassword;

    @Value("${application.googleApiKey}")
    private String googleApiKey;


    private WireMockServer wireMockServer;
    private final String ADDRESS_TEST = "123 Main Street, Ottawa, ON K1P 1J1, Canada";

    @BeforeEach
    public void init() throws IOException {
        String AddressTestEncoded = "123+Main+Street%252C+Ottawa%252C+ON+K1P+1J1%252C+Canada";
        WireMock.configureFor("localhost", 8085);
        wireMockServer = new WireMockServer(8085);
        wireMockServer.start();
        mockingDataBcApi(AddressTestEncoded);
        mockingGoogleApi(AddressTestEncoded);
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Test that verifies the response from the Databc API")
    public void addressLookup_EndToEndSuccessTest() throws IOException {

        webTestClient.get()
                .uri("http://localhost:" + port + pathGeocoderSvc +
                        "?address=" + ADDRESS_TEST)
                .header("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString(
                                (securityUsername+":"+securitYPassword).getBytes()))
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(getFileContent("json/responses/success.json"), true);
    }

    @NotNull
    private static String getFileContent(String location) throws IOException {
        return new String(Objects.requireNonNull(AddressApiTest.class.getClassLoader()
                .getResourceAsStream(location)).readAllBytes());
    }

    private void mockingDataBcApi(String AddressTestEncoded) throws IOException {
        stubFor(
                get(urlEqualTo("/addresses.geojson?address="+ AddressTestEncoded))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(getFileContent("json/requests/data_bc_raw.json"))));
    }

    private void mockingGoogleApi(String AddressTestEncoded) throws IOException {
        stubFor(
                get(urlEqualTo("/maps/api/geocode/json?key=" + googleApiKey +
                        "&address="+ AddressTestEncoded))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(getFileContent("json/requests/google_raw.json"))));
    }
}
