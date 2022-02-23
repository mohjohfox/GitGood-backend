package dhbw.karlsruhe.gitgood;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.List;
import java.util.UUID;

public abstract class TestSupport {

    protected Game createGame() {
        return new Game(UUID.randomUUID().toString(), List.of(), GameMode.FIVEHUNDREDONE);
    }

}
