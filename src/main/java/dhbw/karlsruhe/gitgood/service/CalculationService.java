package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.Player;
import java.util.List;
import java.util.Optional;

public abstract class CalculationService {

  abstract Game calculateRound(Game game);

  abstract boolean isFinished(Player player);

  protected void selectNextPlayer(Game game){
    List<Player> players = game.getPlayers();
    int i = players.indexOf(game.getCurrentPlayer());
    game.setCurrentPlayer(players.get((i + 1) % players.size()));
  }

}
