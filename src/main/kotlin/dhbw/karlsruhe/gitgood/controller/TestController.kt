package dhbw.karlsruhe.gitgood.controller

import dhbw.karlsruhe.gitgood.database.DatabaseConnection
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/")
    fun test(): String {
            val rs = DatabaseConnection.getStatement().executeQuery("SELECT Name FROM Test;")
            while (rs.next()) {
                println(rs.getString("Name"))
            }
        return "Hello World"
    }

    @GetMapping("/hello")
    fun test2(): String {
        return "Hello"
    }
}