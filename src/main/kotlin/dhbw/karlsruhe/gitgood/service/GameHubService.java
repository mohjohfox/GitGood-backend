package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Player;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameHub;

@Service
public class GameHubService {

    @Autowired
    private PlayerService playerService;
    private GameHub gameHub;

    public GameHubService() {
        gameHub = GameHub.getInstance();
    }

    public void openNewGame(Game game) {
        if (game != null && playerService.arePlayerNamesValid(getAllPlayerNamesFromGame(game))) {
            gameHub.addGame(game);
        }
    }

    public List<Game> getAllGames() {
        return gameHub.getOpenGames();
    }

    private List<String> getAllPlayerNamesFromGame(Game game){
        return game.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList());
    }
}
