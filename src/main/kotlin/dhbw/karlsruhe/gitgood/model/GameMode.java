package dhbw.karlsruhe.gitgood.model;

import lombok.Data;

@Data
public class GameMode {

  private final String name;
  private final String description;

  public GameMode(String name, String description) {
    this.name = name;
    this.description = description;
  }

}
