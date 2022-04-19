package dhbw.karlsruhe.gitgood.model;

public enum GameMode {

  FIVEHUNDREDONE("501", "Test description"),
  THREEHUNDREDONE("301", "Test description"),
  CRICKET("Cricket", "Test description"),
  SHANGHAI("Shanghai", "Test description"),
  ROUNDTHECLOCK("Round the clock", "Test description"),
  ONEHUNDREDTWENTYDOWNANDUPWARDS("120 - runter und rauf", "Test description");

  private final String name;
  private final String description;

  GameMode(String name, String description) {
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
