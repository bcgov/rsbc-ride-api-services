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

    @BeforeEach
    public void init() throws IOException {
        WireMock.configureFor("localhost", 8085);
        wireMockServer = new WireMockServer(8085);
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    @DisplayName("Test that verifies the response from the Databc API")
    public void addressLookup_EndToEndSuccessTestDataBc() throws IOException {
        String address = "525 Superior St Victoria BC";
        String addressTestEncoded = "525+Superior+St+Victoria+BC";
        String mockFileLocation = "json/requests/data_bc_raw.json";

        mockingDataBcApi(addressTestEncoded, mockFileLocation);
        mockingGoogleApi(addressTestEncoded);

        webTestClient.get()
                .uri("http://localhost:" + port + pathGeocoderSvc +
                        "?address=" + address)
                .header("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString(
                                (securityUsername+":"+securitYPassword).getBytes()))
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(getFileContent("json/responses/data_bc_success.json"), true);
    }

    @Test
    @DisplayName("Test that verifies the response from the Databc API and Google API")
    public void addressLookup_EndToEndSuccessTestGoogleApi() throws IOException {
        String address = "525 Superior St Bictoria BC";
        String addressTestEncoded = "525+Superior+St+Bictoria+BC";
        String mockFileLocation = "json/requests/lower_score.json";

        mockingDataBcApi(addressTestEncoded, mockFileLocation);
        mockingGoogleApi(addressTestEncoded);

        webTestClient.get()
                .uri("http://localhost:" + port + pathGeocoderSvc +
                        "?address=" + address)
                .header("Authorization",
                        "Basic " + Base64.getEncoder().encodeToString(
                                (securityUsername+":"+securitYPassword).getBytes()))
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json(getFileContent("json/responses/google_success.json"), true);
    }

    @NotNull
    private static String getFileContent(String location) throws IOException {
        return new String(Objects.requireNonNull(AddressApiTest.class.getClassLoader()
                .getResourceAsStream(location)).readAllBytes());
    }

    private void mockingDataBcApi(String addressTestEncoded, String fileLocation) throws IOException {
        stubFor(
                get(urlEqualTo("/addresses.json?addressString="+ addressTestEncoded))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(getFileContent(fileLocation))));
    }

    private void mockingGoogleApi(String addressTestEncoded) throws IOException {
        stubFor(
                get(urlEqualTo("/maps/api/geocode/json?key=" + googleApiKey +
                        "&address="+ addressTestEncoded))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(getFileContent("json/requests/google_raw.json"))));
    }
}
