package dhbw.karlsruhe.gitgood.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameHub {

    private static GameHub gameHub = null;
    private final List<Game> openGames = new ArrayList<>();

    public static GameHub getInstance() {
        if (gameHub == null) {
            gameHub = new GameHub();
        }
        return gameHub;
    }

    public void addGame(Game game) {
        String generatedGameId = UUID.randomUUID().toString();
        game.setId(generatedGameId);
        game.setCurrentPlayer(game.getPlayers().get(0));
        initializePoints(game);
        openGames.add(game);
    }

    public List<Game> getOpenGames() {
        return openGames;
    }

    private void initializePoints(Game game){
        switch (game.getGameMode().getName()){
            case "501":
                game.getPlayers().forEach(player -> player.setPoints(501));
                break;

            case "301":
                game.getPlayers().forEach(player -> player.setPoints(301));
                break;
        }
    }
}
