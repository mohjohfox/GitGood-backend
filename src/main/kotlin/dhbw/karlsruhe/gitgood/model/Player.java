package dhbw.karlsruhe.gitgood.model;

import lombok.Data;

@Data
public class Player {

  private String playerId;
  private String playerName;
  private String points;

  public Player(String playerId, String playerName) {
    this.playerId = playerId;
    this.playerName = playerName;
  }
}
