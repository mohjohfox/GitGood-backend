package dhbw.karlsruhe.gitgood.port;

import dhbw.karlsruhe.gitgood.model.GameMode;

import java.util.List;
import java.util.Optional;

public interface GameModePort {

    Optional<GameMode> getGameModeByName(String gameModeName);

    List<GameMode> getAllGameModes();

}
