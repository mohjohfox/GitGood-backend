package dhbw.karlsruhe.gitgood.util;

import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.model.GameModeEnum;

public class GameModeMapper {

  public static GameMode mapGameModeEnumToGameModeModel(GameModeEnum gameModeEnum){
    return new GameMode(gameModeEnum.getName(), gameModeEnum.getDescription());
  }

}
