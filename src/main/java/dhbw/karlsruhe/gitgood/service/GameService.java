package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.model.Game;
import dhbw.karlsruhe.gitgood.model.GameMode;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  @Autowired
  private XXX_HundredOneService xxx_hundredOneService;
  @Autowired
  private GameHubService gameHubService;
  public Optional<Game> calculateRound(String gameId, String[] thrownPoints){
    Optional<Game> game = gameHubService.getGameById(gameId);

    if (game.isPresent()){
      setThrownPoints(game.get(), thrownPoints);
      GameMode gameMode = game.get().getGameMode();
      Optional<CalculationService> calculationService = findCalculationService(gameMode);

      if (calculationService.isPresent()){
        Game calculatedGame = calculationService.get().calculateRound(game.get());
        return Optional.of(calculatedGame);
      }

    } else {
      return Optional.empty();
    }

    return Optional.empty();

  }

  private Optional<CalculationService> findCalculationService(GameMode gameMode){
    switch (gameMode.getName()){
      case "501":
      case "301":
        return Optional.ofNullable(xxx_hundredOneService);
    }

    return Optional.empty();
  }

  private void setThrownPoints(Game game, String[] thrownPoints){
    game.getCurrentPlayer().setThrownPoints(thrownPoints);
  }

}
