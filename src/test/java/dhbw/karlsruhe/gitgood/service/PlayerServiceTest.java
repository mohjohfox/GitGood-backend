package dhbw.karlsruhe.gitgood.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlayerServiceTest {

  @Autowired
  private PlayerService playerService;

  @Test
  void checkSinglePlayerName_ValidName() {
    String playerName = "Anton";

    assertTrue(playerService.isPlayerNameValid(playerName));
  }

  @Test
  void checkSinglePlayerName_NotValidName_Underscore() {

    String playerName = "An_t";

    assertFalse(playerService.isPlayerNameValid(playerName));
  }

  @Test
  void checkSinglePlayerName_NotValidName_Star() {

    String playerName = "A*Ü*t";

    assertFalse(playerService.isPlayerNameValid(playerName));
  }

  @Test
  void checkSinglePlayerName_NotValidName_Slash() {

    String playerName = "An/t";

    assertFalse(playerService.isPlayerNameValid(playerName));
  }

  @Test
  void checkPlayerNames_ValidNames() {

    List<String> playerNames = List.of("TestUser", "TestUser0815");

    assertTrue(playerService.arePlayerNamesValid(playerNames));
  }

  @Test
  void checkPlayerNames_InvalidNames() {

    List<String> playerNames = List.of("TestUser.", "TestUser0815");

    assertFalse(playerService.arePlayerNamesValid(playerNames));
  }


}
