package dhbw.karlsruhe.gitgood.adapter.controller;

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

import dhbw.karlsruhe.gitgood.TestSupport;
import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.service.GameHubService;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void findSessionById_WithValidId() throws Exception {

        Game game = createGame();
        String baseUrl = "http://localhost:" + randomServerPort + "/game/start";

        HttpEntity request = new HttpEntity(game);
        ResponseEntity<Game> response = restTemplate.exchange(baseUrl, HttpMethod.POST, request, Game.class);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }
    
}