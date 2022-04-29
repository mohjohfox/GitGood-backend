package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameHub;
import dhbw.karlsruhe.gitgood.model.Player;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameHubService {

    @Autowired
    private PlayerService playerService;
    private GameHub gameHub;

    public GameHubService() {
        gameHub = GameHub.getInstance();
    }

    public Optional<Game> openNewGame(Game game) throws IllegalArgumentException {
        if (game != null && playerService.arePlayerNamesValid(getAllPlayerNamesFromGame(game))) {
            gameHub.addGame(game);
            return Optional.of(game);
        }
        return Optional.empty();
    }

    public List<Game> getAllGames() {
        return gameHub.getOpenGames();
    }

    private List<String> getAllPlayerNamesFromGame(Game game){
        return game.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList());
    }
}
