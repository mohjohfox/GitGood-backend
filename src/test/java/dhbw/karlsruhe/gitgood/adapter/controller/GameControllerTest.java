package dhbw.karlsruhe.gitgood.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dhbw.karlsruhe.gitgood.TestSupport;
import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.service.GameHubService;
import java.util.Objects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTest extends TestSupport {

  @LocalServerPort
  private int randomServerPort;
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private GameHubService gameHubService;

  @Test
  public void calculateGame_WithValidGameParameter() {
    Game game = createGame();
    gameHubService.openNewGame(game);

    String baseUrl = "http://localhost:" + randomServerPort + "/game/"+game.getId()+"/round/submit";

    HttpEntity request = new HttpEntity(new String[]{"60", "60", "0"});
    ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
        Game.class);

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
  }

  @Test
  public void calculateGame_WithNotValidGameParameter() {
    Game game = createGame();
    gameHubService.openNewGame(game);

    String baseUrl = "http://localhost:" + randomServerPort + "/game/dasdasdadas/round/submit";

    HttpEntity request = new HttpEntity(new String[]{"60", "60", "0"});
    ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
        Game.class);

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
  }

  @Test
  public void calculateGame_WithValidGameParameter_NotFinished() {
    Game game = createGame();
    gameHubService.openNewGame(game);

    String baseUrl = "http://localhost:" + randomServerPort + "/game/"+game.getId()+"/round/submit";

    HttpEntity request = new HttpEntity(new String[]{"60", "60", "0"});
    ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
        Game.class);

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertFalse(Objects.requireNonNull(response.getBody()).isFinished());
  }

  @Test
  public void calculateGame_WithValidGameParameter_Finished() {
    Game game = createGame();
    gameHubService.openNewGame(game);

    String baseUrl = "http://localhost:" + randomServerPort + "/game/"+game.getId()+"/round/submit";

    HttpEntity request = new HttpEntity(new String[]{"60", "441", "0"});
    ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
        Game.class);

    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    assertTrue(Objects.requireNonNull(response.getBody()).isFinished());
  }
}
