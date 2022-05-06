package dhbw.karlsruhe.gitgood.model;

import lombok.Data;

@Data
public class Player {

  private String id;
  private String name;
  private int points;
  private String[] thrownPoints;

  public Player(String id, String name) {
    this.id = id;
    this.name = name;
  }

}
