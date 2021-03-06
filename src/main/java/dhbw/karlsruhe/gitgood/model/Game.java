package dhbw.karlsruhe.gitgood.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Game {

    private String id;
    private List<Player> players;
    private Player currentPlayer;
    private GameMode gameMode;
    private boolean isFinished;

}
