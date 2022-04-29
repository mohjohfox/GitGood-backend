package dhbw.karlsruhe.gitgood.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dhbw.karlsruhe.gitgood.TestSupport;
import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.Player;
import dhbw.karlsruhe.gitgood.service.GameHubService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameHubControllerTest extends TestSupport {

    @LocalServerPort
    private int randomServerPort;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameHubService gameHubService;

    @Test
    public void startGame_WithValidGameParameter() {

        Game game = createGame();
        String baseUrl = "http://localhost:" + randomServerPort + "/game/start";

        HttpEntity request = new HttpEntity(game);
        ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
            Game.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void startGame_WithNotValidGameParameter() {

        Game game = createGame();
        String baseUrl = "http://localhost:" + randomServerPort + "/game/start";

        game.setPlayers(List.of(new Player("Id", "$§")));
        HttpEntity request = new HttpEntity(game);
        ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request,
            Game.class);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    }

    @Test
    public void findAllOpenGames() {
        String baseUrl = "http://localhost:" + randomServerPort + "/allGames";
        Game game = createGame();
        gameHubService.openNewGame(game);

        ResponseEntity<List<Game>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
            new ParameterizedTypeReference<>() {
            });

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(List.of(game), response.getBody());
    }

    @Test
    public void findAllOpenGames_NoOpenGames() {
        String baseUrl = "http://localhost:" + randomServerPort + "/allGames";

        ResponseEntity<List<Game>> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
            new ParameterizedTypeReference<>() {
            });

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(List.of(), response.getBody());
    }

}
