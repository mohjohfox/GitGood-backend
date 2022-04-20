package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.model.GameModeEnum;
import dhbw.karlsruhe.gitgood.util.GameModeMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameModeService {

  public List<GameMode> getAllGameModes() {
    return Stream.of(GameModeEnum.values()).map(GameModeMapper::mapGameModeEnumToGameModeModel).collect(Collectors.toList());
  }

  public Optional<GameMode> getGameModeByName(String gameModeName) {
    try {
      return Optional.of(GameModeMapper.mapGameModeEnumToGameModeModel(GameModeEnum.valueOf(gameModeName)));
    } catch (IllegalArgumentException e) {
      log.warn(e.getMessage());
      return Optional.empty();
    }
  }


}
