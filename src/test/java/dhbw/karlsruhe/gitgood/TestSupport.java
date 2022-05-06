package dhbw.karlsruhe.gitgood;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.model.Player;
import java.util.List;
import java.util.UUID;

public abstract class TestSupport {

    protected Game createGame() {
        return new Game(UUID.randomUUID().toString(), generatePlayers(), null,
            new GameMode("501", "Test description"), false);
    }

    private List<Player> generatePlayers(){
        return List.of(generateSinglePlayer("TestUser"), generateSinglePlayer("TestName"));
    }

    private Player generateSinglePlayer(String playerName){
        return new Player(UUID.randomUUID().toString(), playerName);
    }

}
