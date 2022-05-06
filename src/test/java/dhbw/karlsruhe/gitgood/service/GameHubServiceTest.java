package dhbw.karlsruhe.gitgood.service;

import static org.assertj.core.api.Assertions.assertThat;

import dhbw.karlsruhe.gitgood.TestSupport;
import dhbw.karlsruhe.gitgood.model.Game;
import java.util.Optional;
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
        game.getPlayers().get(0).setName(";");
        
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

    @Test
    void deleteAllGames() {
        //given
        Game firstGame = createGame();
        Game secondGame = createGame();
        assertThat(gameHubService.getAllGames()).isEmpty();

        //when
        gameHubService.openNewGame(firstGame);
        gameHubService.openNewGame(secondGame);
        assertThat(gameHubService.getAllGames()).hasSize(2).containsExactly(firstGame, secondGame);
        gameHubService.deleteAllGames();

        //then
        assertThat(gameHubService.getAllGames()).isEmpty();
    }

    @Test
    void deleteGameById() {
        //given
        Game firstGame = createGame();
        Game secondGame = createGame();
        gameHubService.openNewGame(firstGame);
        gameHubService.openNewGame(secondGame);
        assertThat(gameHubService.getAllGames()).hasSize(2).containsExactly(firstGame, secondGame);

        //when
        String gameId = firstGame.getId();
        gameHubService.deleteGameById(gameId);

        //then
        assertThat(gameHubService.getAllGames()).hasSize(1).containsExactly(secondGame);
    }

    @Test
    void getGameById() {
        //given
        Game firstGame = createGame();
        Game secondGame = createGame();
        gameHubService.openNewGame(firstGame);
        gameHubService.openNewGame(secondGame);
        assertThat(gameHubService.getAllGames()).hasSize(2).containsExactly(firstGame, secondGame);

        //when
        String gameId = firstGame.getId();
        Optional<Game> gameOptional = gameHubService.getGameById(gameId);

        //then
        assertThat(gameOptional).isPresent();
        assertThat(gameOptional.get()).isEqualTo(firstGame);
    }
    @Test
    void openNewGame_ValidGame_WithPointInit() {
        //given
        Game game = createGame();
        assertThat(gameHubService.getAllGames()).isEmpty();

        //then
        assertThat(gameHubService.openNewGame(game)).isPresent().get().isEqualTo(game);
        assertThat(gameHubService.getAllGames()).hasSize(1).containsExactly(game);
        assertThat(gameHubService.getGameById(game.getId()).get().getCurrentPlayer().getPoints()).isEqualTo(501);
    }

}
