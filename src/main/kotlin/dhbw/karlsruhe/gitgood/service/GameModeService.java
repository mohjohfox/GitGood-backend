package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class GameModeService {

  public List<GameMode> getAllGameModes() {
    return Stream.of(GameMode.values()).collect(Collectors.toList());
  }

  public GameMode getGameModeByName(String gameModeName) {
    return GameMode.valueOf(gameModeName);
  }


}
