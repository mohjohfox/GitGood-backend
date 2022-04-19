package dhbw.karlsruhe.gitgood.adapter.controller;

import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.service.GameModeService;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameModeController {

  @Autowired
  private GameModeService gameModeService;

  @GetMapping(value = "/gamemode/{gamemodename}")
  public ResponseEntity<GameMode> getGameModeByName(@PathVariable String gamemodename) {
    Optional<GameMode> gameMode = gameModeService.getGameModeByName(
        StringUtils.upperCase(gamemodename));

    return gameMode.map(mode -> new ResponseEntity<>(mode, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
  }

  @GetMapping(value = "/allGamemodes")
  public ResponseEntity<List<GameMode>> getAllGameModes() {
    return new ResponseEntity<>(gameModeService.getAllGameModes(), HttpStatus.OK);
  }


}
