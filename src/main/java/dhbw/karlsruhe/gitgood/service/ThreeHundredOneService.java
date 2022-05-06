package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreeHundredOneService extends CalculationService {

  @Override
  public Game calculateRound(Game game){
    Player currentPlayer = game.getCurrentPlayer();
    int sumOfThrownPoints = findSumUsingStream(currentPlayer.getThrownPoints());
    int oldPoints = currentPlayer.getPoints();
    int newPoints = oldPoints - sumOfThrownPoints;

    if (newPoints > 0){
      currentPlayer.setPoints(newPoints);
    }

    game.setFinished(isFinished(currentPlayer));
    selectNextPlayer(game);

    return game;
  }

  private int findSumUsingStream(String[] array) {
    return Arrays.stream(array).map(Integer::parseInt)
        .mapToInt(Integer::intValue)
        .sum();
  }


}
