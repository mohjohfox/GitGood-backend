package dhbw.karlsruhe.gitgood;

import java.util.List;
import java.util.UUID;

import dhbw.karlsruhe.gitgood.model.Game;

public abstract class TestSupport {

    protected Game createGame() {
        return new Game(UUID.randomUUID().toString(), List.of());
    }

}
