package dhbw.karlsruhe.gitgood.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dhbw.karlsruhe.gitgood.TestSupport;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "local")
public class CheckoutControllerTest extends TestSupport {

  @LocalServerPort
  private int randomServerPort;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void validCheckoutOption() {
    String baseUrl = "http://localhost:" + randomServerPort + "/checkoutoptions/" + 3;

    ResponseEntity<List<String>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {
        });

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(List.of("1", "D1", " "), response.getBody());
  }

  @Test
  public void notValidCheckoutOption() {
    String baseUrl = "http://localhost:" + randomServerPort + "/checkoutoptions/" + 1;

    ResponseEntity<List<String>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {
        });

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(List.of(), response.getBody());
  }

}
