package dhbw.karlsruhe.gitgood.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameModeControllerTest {

  @LocalServerPort
  private int randomServerPort;
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void findGameModeByName_WithValidGameModeId() {

    String gameMode = "Cricket";
    String baseUrl = "http://localhost:" + randomServerPort + "/gamemode/" + gameMode;

    ResponseEntity<GameMode> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
        GameMode.class);

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(GameMode.CRICKET, response.getBody());
  }

  @Test
  public void findGameModeByName_WithNotValidGameModeId() {

    String gameMode = "Quatsch";
    String baseUrl = "http://localhost:" + randomServerPort + "/gamemode/" + gameMode;

    ResponseEntity<GameMode> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
        GameMode.class);

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    assertNull(response.getBody());
  }

  @Test
  public void findAllGameModes() {

    String baseUrl = "http://localhost:" + randomServerPort + "/allGamemodes";

    ResponseEntity<List<GameMode>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {
        });

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertEquals(List.of(GameMode.FIVEHUNDREDONE, GameMode.THREEHUNDREDONE,
        GameMode.CRICKET,
        GameMode.SHANGHAI,
        GameMode.ROUNDTHECLOCK, GameMode.ONEHUNDREDTWENTYDOWNANDUPWARDS), response.getBody());
  }

}