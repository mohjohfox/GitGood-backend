package dhbw.karlsruhe.gitgood.adapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.service.GameHubService;

@RestController
public class GameController {

    @Autowired
    private GameHubService gameHubService;

    @PostMapping(value = "/game/start", consumes = MediaType.ALL_VALUE)
    public ResponseEntity startGame(@RequestBody Game game) {
        gameHubService.openNewGame(game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
