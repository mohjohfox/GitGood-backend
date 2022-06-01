package dhbw.karlsruhe.gitgood.adapter.controller;

import dhbw.karlsruhe.gitgood.service.CheckoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

  @Autowired
  private CheckoutService checkoutService;

  @GetMapping(value = "/checkoutoptions/{rest}")
  public ResponseEntity<List<String>> getAllOpenGames(@PathVariable int rest) {
    return new ResponseEntity<>(checkoutService.getCheckoutOptions(rest), HttpStatus.OK);
  }

}
