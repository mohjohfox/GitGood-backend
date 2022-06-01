package dhbw.karlsruhe.gitgood.adapter.persistence;

import dhbw.karlsruhe.gitgood.port.CheckoutPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CheckoutAdapter implements CheckoutPort {

  @Autowired
  private final JdbcTemplate jdbc;

  public CheckoutAdapter(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public List<String> getCheckoutOptions(int rest) {
    List<Map<String, Object>> maps = jdbc.queryForList(
        "SELECT * FROM checkout c WHERE c.Rest = '" + rest + "'");

    List<String> checkoutOptions = new ArrayList<>();

    if (!maps.isEmpty()) {
      checkoutOptions.add(maps.get(0).get("FirstDart").toString());
      checkoutOptions.add(maps.get(0).get("SecondDart").toString());
      checkoutOptions.add(maps.get(0).get("ThirdDart").toString());
    }
    
    return checkoutOptions;
  }
}
