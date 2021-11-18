package dhbw.karlsruhe.gitgood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GitGoodApplication

fun main(args: Array<String>) {
    runApplication<GitGoodApplication>(*args)
}
