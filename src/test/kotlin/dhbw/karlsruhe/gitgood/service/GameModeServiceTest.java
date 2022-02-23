package dhbw.karlsruhe.gitgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameModeServiceTest {

  @Autowired
  private GameModeService gameModeService;

  @Test
  public void getAllGameModesContainsAll() {
    List<GameMode> allGameModes = List.of(GameMode.FIVEHUNDREDONE, GameMode.THREEHUNDREDONE,
        GameMode.CRICKET,
        GameMode.SHANGHAI,
        GameMode.ROUNDTHECLOCK, GameMode.ONEHUNDREDTWENTYDOWNANDUPWARDS);
    assertTrue(
        CollectionUtils.isEqualCollection(gameModeService.getAllGameModes(), allGameModes));
  }

  @Test
  public void findAvailableGameModeByName() {
    //given
    String gameModeName = "CRICKET";

    //then
    assertTrue(gameModeService.getGameModeByName(gameModeName).isPresent());
    assertEquals(gameModeService.getGameModeByName(gameModeName).get(), GameMode.CRICKET);
  }

  @Test
  public void findNoAvailableGameModeByName() {
    String gameModeName = "NotAvailableGameMode";
    assertTrue(gameModeService.getGameModeByName(gameModeName).isEmpty());
  }


}