package dhbw.karlsruhe.gitgood.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  public boolean isPlayerNameValid(String playerName){
    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(playerName);
    return m.find();
  }

  public boolean arePlayerNamesValid(List<String> playerNames){
    return playerNames.stream().allMatch(this::isPlayerNameValid);
  }

}
