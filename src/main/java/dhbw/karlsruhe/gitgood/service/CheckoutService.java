package dhbw.karlsruhe.gitgood.service;

import dhbw.karlsruhe.gitgood.port.CheckoutPort;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

  @Autowired
  private CheckoutPort checkoutPort;

  public List<String> getCheckoutOptions(int rest) {
    return checkoutPort.getCheckoutOptions(rest);
  }

}
