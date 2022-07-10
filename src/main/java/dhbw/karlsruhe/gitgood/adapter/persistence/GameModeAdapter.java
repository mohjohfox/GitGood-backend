package dhbw.karlsruhe.gitgood.adapter.persistence;

import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.port.GameModePort;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameModeAdapter implements GameModePort {

    @Autowired
    private final JdbcTemplate jdbc;


    public GameModeAdapter(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<GameMode> getGameModeByName(String gameModeName) {

        List<Map<String, Object>> databaseEntries = jdbc.queryForList(
            "SELECT * FROM gamemode c WHERE c.name = '" + gameModeName + "'");

        return databaseEntries.isEmpty() ? Optional.empty() : Optional.of(new GameMode(
            databaseEntries.get(0).get("shortName").toString(),
            databaseEntries.get(0).get("description").toString()));
    }

    @Override
    public List<GameMode> getAllGameModes() {

        List<GameMode> allGameModes = new ArrayList<>();

        List<Map<String, Object>> databaseEntries = jdbc.queryForList("SELECT * FROM gamemode");

        if (!databaseEntries.isEmpty()) {
            databaseEntries.forEach(entry -> {
                allGameModes.add(new GameMode(entry.get("shortName").toString(),
                    entry.get("description").toString()));
            });
            return allGameModes;
        }
        return List.of();
    }
}
