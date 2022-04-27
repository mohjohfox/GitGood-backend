package dhbw.karlsruhe.gitgood.model;

public enum GameModeEnum {

  FIVEHUNDREDONE("501", "Fivehundredone gamerules"),
  THREEHUNDREDONE("301", "301 gamerules"),
  CRICKET("Cricket", "This is cricket"),
  SHANGHAI("Shanghai", "I am from shanghai"),
  ROUNDTHECLOCK("Round the clock", "the clock is ticking"),
  ONEHUNDREDTWENTYDOWNANDUPWARDS("120 - runter und rauf", "up and down");
  private final String name;
  private final String description;

  GameModeEnum(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
