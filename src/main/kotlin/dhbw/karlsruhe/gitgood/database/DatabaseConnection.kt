package dhbw.karlsruhe.gitgood.database

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

object DatabaseConnection {

    lateinit var connection : Connection

    fun initConnection(connectionUrl: String, username: String, pw: String) {

        val properties = Properties()

        // Populate the properties file with user name and password
        with(properties) {
            put("user", username)
            put("password", pw)
        }

        // Open a connection to the database
        connection = DriverManager.getConnection(connectionUrl, properties)
        println("Database connection sucessfull!")
    }

}