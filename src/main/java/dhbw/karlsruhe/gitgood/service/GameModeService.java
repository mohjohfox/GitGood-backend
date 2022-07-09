package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.port.GameModePort;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameModeService {

  @Autowired
  private GameModePort gameModePort;

  public List<GameMode> getAllGameModes() {
    return gameModePort.getAllGameModes();
  }

  public Optional<GameMode> getGameModeByName(String gameModeName) {
      return gameModePort.getGameModeByName(gameModeName);
  }
}
