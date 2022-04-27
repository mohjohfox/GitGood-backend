package dhbw.karlsruhe.gitgood.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {

    private String gameId;
    private List<Player> players;
    private GameMode gameMode;

}
