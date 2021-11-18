package dhbw.karlsruhe.gitgood.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/")
    fun test(): String {
        return "Hello World"
    }

    @GetMapping("/hello")
    fun test2(): String {
        return "Hello"
    }
}
