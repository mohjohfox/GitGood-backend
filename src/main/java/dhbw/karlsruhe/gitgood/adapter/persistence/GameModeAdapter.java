package dhbw.karlsruhe.gitgood.adapter.persistence;

import dhbw.karlsruhe.gitgood.model.GameMode;
import dhbw.karlsruhe.gitgood.port.GameModePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class GameModeAdapter implements GameModePort {

    @Autowired
    private final JdbcTemplate jdbc;


    public GameModeAdapter(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<GameMode> getGameModeByName(String gameModeName) {

        List<Map<String, Object>> maps = jdbc.queryForList(
                "SELECT * FROM gamemode c WHERE c.name = '" + gameModeName + "'");


        return maps.isEmpty() ? Optional.empty() : Optional.of(new GameMode(
                maps.get(0).get("shortName").toString(),
                maps.get(0).get("description").toString()));
    }

    @Override
    public List<GameMode> getAllGameModes() {

        List<GameMode> allGameModes = new ArrayList<>();

        List<Map<String, Object>> maps = jdbc.queryForList("SELECT * FROM gamemode");

        if (!maps.isEmpty()){
            maps.forEach( item -> {
                allGameModes.add(new GameMode( item.get("shortName").toString(), item.get("description").toString() ));
            });
            return allGameModes;
        }
        return List.of();
    }
}
