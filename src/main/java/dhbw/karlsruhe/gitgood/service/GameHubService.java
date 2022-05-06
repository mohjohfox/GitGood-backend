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

    public Optional<Game> getGameById(String gameId){
        return getAllGames().stream().filter(v -> v.getId().equals(gameId)).findFirst();
    }
    public void deleteGameById(String gameId){
        Optional<Game> gameOptional = getGameById(gameId);
        gameOptional.ifPresent(game -> getAllGames().remove(game));
    }
    public void deleteAllGames() {
        getAllGames().clear();
    }

    private List<String> getAllPlayerNamesFromGame(Game game){
        return game.getPlayers().stream().map(Player::getName).collect(Collectors.toList());
    }
}
