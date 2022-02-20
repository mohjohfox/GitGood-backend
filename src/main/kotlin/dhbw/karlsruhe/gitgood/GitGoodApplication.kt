package dhbw.karlsruhe.gitgood

import dhbw.karlsruhe.gitgood.database.DatabaseConnection
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GitGoodApplication

fun main(args: Array<String>) {
    runApplication<GitGoodApplication>(*args)
   DatabaseConnection.initConnection(args[0],
           args[1], args[2])
}
