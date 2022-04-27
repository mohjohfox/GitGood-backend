package dhbw.karlsruhe.gitgood.adapter.controller;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.service.GameHubService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameHubController {

    @Autowired
    private GameHubService gameHubService;

    @PostMapping(value = "/game/start", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Game> startGame(@RequestBody Game game) {
        Optional<Game> optionalGame = gameHubService.openNewGame(game);
        return optionalGame.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(value = "/allGames", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<Game>> getAllOpenGames() {
        return new ResponseEntity<>(gameHubService.getAllGames(), HttpStatus.OK);
    }

}
