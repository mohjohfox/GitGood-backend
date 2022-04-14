package dhbw.karlsruhe.gitgood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @GetMapping("/test2")
  public String test() {
   return jdbcTemplate.queryForObject("SELECT Name FROM Test;", String.class);
  }

}
