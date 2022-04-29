package dhbw.karlsruhe.gitgood.service;

import static org.assertj.core.api.Assertions.assertThat;

import dhbw.karlsruhe.gitgood.TestSupport;
import dhbw.karlsruhe.gitgood.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameHubServiceTest extends TestSupport {

    @Autowired
    private GameHubService gameHubService;

    @BeforeEach
    void setUp() {
        gameHubService.getAllGames().clear();
    }

    @Test
    void openNewGame_ValidGame() {
        //given
        Game game = createGame();
        assertThat(gameHubService.getAllGames()).isEmpty();

        //then
        assertThat(gameHubService.openNewGame(game)).isPresent().get().isEqualTo(game);
        assertThat(gameHubService.getAllGames()).hasSize(1).containsExactly(game);
    }

    @Test
    void openNewGame_NotValidGame() {
        //given
        assertThat(gameHubService.getAllGames()).isEmpty();

        //then
        assertThat(gameHubService.openNewGame(null)).isEmpty();
        assertThat(gameHubService.getAllGames()).isEmpty();
    }

    @Test
    void openNewGame_NotValidGame_InvalidPlayerName() {
        //given
        Game game = createGame();
        assertThat(gameHubService.getAllGames()).isEmpty();

        //when
        game.getPlayers().get(0).setPlayerName(";");
        
        //then
        assertThat(gameHubService.openNewGame(game)).isEmpty();
        assertThat(gameHubService.getAllGames()).isEmpty();
    }

    @Test
    void getAllGames() {
        //given
        Game firstGame = createGame();
        Game secondGame = createGame();
        assertThat(gameHubService.getAllGames()).isEmpty();

        //when
        gameHubService.openNewGame(firstGame);
        gameHubService.openNewGame(null);
        gameHubService.openNewGame(secondGame);

        //then
        assertThat(gameHubService.getAllGames()).hasSize(2).containsExactly(firstGame, secondGame);
    }

}
