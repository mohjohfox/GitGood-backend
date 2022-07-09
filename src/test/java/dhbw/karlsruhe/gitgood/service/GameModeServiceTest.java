package dhbw.karlsruhe.gitgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import dhbw.karlsruhe.gitgood.adapter.persistence.GameModeAdapter;
import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.List;
import java.util.Optional;

import dhbw.karlsruhe.gitgood.port.GameModePort;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class GameModeServiceTest {

  @Autowired
  private GameModeService gameModeService;

  @MockBean
  private GameModePort gameModePort;

  @Test
  public void getAllGameModesContainsAll() {
    List<GameMode> allGameModes = List.of(new GameMode("501", "Fivehundredone gamerules"), new GameMode("301", "301 gamerules"),
        new GameMode("Cricket", "This is cricket"), new GameMode("Shanghai", "I am from shanghai"),
        new GameMode("Round the clock", "the clock is ticking"), new GameMode("120 - runter und rauf", "up and down"));

    when(gameModePort.getAllGameModes()).thenReturn(allGameModes);

    assertTrue(CollectionUtils.isEqualCollection(gameModeService.getAllGameModes(), allGameModes));
  }

  @Test
  public void findAvailableGameModeByName() {
    //given
    String gameModeName = "CRICKET";
    GameMode cricket = new GameMode("Cricket", "This is cricket");

    //when
    when(gameModePort.getGameModeByName(gameModeName)).thenReturn(Optional.of(cricket));

    //then
    assertTrue(gameModeService.getGameModeByName(gameModeName).isPresent());
    assertEquals(gameModeService.getGameModeByName(gameModeName).get(), cricket);
  }

  @Test
  public void findNoAvailableGameModeByName() {
    String gameModeName = "NotAvailableGameMode";

    //when
    when(gameModePort.getGameModeByName(gameModeName)).thenReturn(Optional.empty());

    assertTrue(gameModeService.getGameModeByName(gameModeName).isEmpty());
  }


}
