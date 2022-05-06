package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private HundredOneService hundredOneService;
  @Autowired
  private GameHubService gameHubService;
  public Optional<Game> calculateNewGameState(String gameId, String[] thrownPoints){
    Optional<Game> game = getGame(gameId);

    if (game.isPresent()){
      Game currGame = game.get();
      setThrownPoints(currGame, thrownPoints);
      return calculateCurrentRound(currGame);
    }
    return Optional.empty();
  }

  private Optional<Game> calculateCurrentRound(Game currGame) {
    Optional<CalculationService> calculationService = getCalculationService(currGame);
    if (calculationService.isPresent()){
      Game calculatedGame = calculationService.get().calculateRound(currGame);
      return Optional.of(calculatedGame);
    }
    return Optional.empty();
  }

  private Optional<CalculationService> getCalculationService(Game game) {
    return findCalculationService(game.getGameMode());
  }

  private Optional<Game> getGame(String gameId){
     return gameHubService.getGameById(gameId);
  }

  private Optional<CalculationService> findCalculationService(GameMode gameMode){
    switch (gameMode.getName()){
      case "501":
      case "301":
        return Optional.ofNullable(hundredOneService);
    }

    return Optional.empty();
  }

  private void setThrownPoints(Game game, String[] thrownPoints){
    game.getCurrentPlayer().setThrownPoints(thrownPoints);
  }

}
