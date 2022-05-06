package dhbw.karlsruhe.gitgood.adapter.controller;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.service.GameService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

  @Autowired
  private GameService gameService;

  @PostMapping(value = "/game/{gameId}/round/submit", consumes = MediaType.ALL_VALUE)
  public ResponseEntity<Game> startGame(@PathVariable String gameId, @RequestBody String[] thrownPoints) {
    Optional<Game> optionalGame = gameService.calculateRound(gameId, thrownPoints);
    return optionalGame.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
  }

}
