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
    List<GameMode> allGameModes = List.of(new GameMode("501", "Test description"), new GameMode("301", "Test description"),
        new GameMode("Cricket", "Test description"), new GameMode("Shanghai", "Test description"),
        new GameMode("Round the clock", "Test description"), new GameMode("120 - runter und rauf", "Test description"));
    assertTrue(
        CollectionUtils.isEqualCollection(gameModeService.getAllGameModes(), allGameModes));
  }

  @Test
  public void findAvailableGameModeByName() {
    //given
    String gameModeName = "CRICKET";

    //then
    assertTrue(gameModeService.getGameModeByName(gameModeName).isPresent());
    assertEquals(gameModeService.getGameModeByName(gameModeName).get(), new GameMode("Cricket", "Test description"));
  }

  @Test
  public void findNoAvailableGameModeByName() {
    String gameModeName = "NotAvailableGameMode";
    assertTrue(gameModeService.getGameModeByName(gameModeName).isEmpty());
  }


}
