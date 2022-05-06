package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.Player;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class HundredOneService extends CalculationService {

  @Override
  public Game calculateRound(Game game){
    Player currentPlayer = game.getCurrentPlayer();

    calculateNewPointsForCurrPlayer(currentPlayer, findSumUsingStream(currentPlayer.getThrownPoints()));

    game.setFinished(isFinished(currentPlayer));

    selectNextPlayer(game);

    return game;
  }

  private void calculateNewPointsForCurrPlayer(Player currPlayer, int sumOfThrownPoints){
    int newPoints = currPlayer.getPoints() - sumOfThrownPoints;

    if (newPoints >= 0){
      currPlayer.setPoints(newPoints);
    }
  }

  protected boolean isFinished(Player player) {
    return player.getPoints() == 0;
  }

  private int findSumUsingStream(String[] array) {
    return Arrays.stream(array).map(Integer::parseInt)
        .mapToInt(Integer::intValue)
        .sum();
  }

}
