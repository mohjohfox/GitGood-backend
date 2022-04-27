package dhbw.karlsruhe.gitgood.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameHub {

    private static GameHub gameHub = null;
    private List<Game> openGames = new ArrayList<>();

    public static GameHub getInstance() {
        if (gameHub == null) {
            gameHub = new GameHub();
        }
        return gameHub;
    }

    public void addGame(Game game) {
        String generatedGameId = UUID.randomUUID().toString();
        game.setGameId(generatedGameId);
        game.setPlayer(game.getPlayers().get(0));
        openGames.add(game);
    }

    public List<Game> getOpenGames() {
        return openGames;
    }
}
