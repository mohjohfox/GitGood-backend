package dhbw.karlsruhe.gitgood.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameHub;

@Service
public class GameHubService {

    private GameHub gameHub;

    public GameHubService() {
        gameHub = GameHub.getInstance();
    }

    public void openNewGame(Game game) {
        if (game != null) {
            gameHub.addGame(game);
        }
    }

    public List<Game> getAllGames() {
        return gameHub.getOpenGames();
    }

}
