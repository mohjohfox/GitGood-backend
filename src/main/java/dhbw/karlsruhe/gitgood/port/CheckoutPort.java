package dhbw.karlsruhe.gitgood.port;

import java.util.List;

public interface CheckoutPort {

  List<String> getCheckoutOptions(int rest);

}
