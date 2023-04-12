package ru.newtry.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.newtry.database.users.UserDTO
import ru.newtry.database.tokens.TokenDTO
import ru.newtry.database.tokens.Tokens
import ru.newtry.database.users.Users
import java.sql.DriverManager.println
import java.util.*

class RegisterController(private val call :ApplicationCall) {

    suspend fun registerNewUser(){

        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        val userDTO = Users.fetchUser(registerReceiveRemote.login)
        if (userDTO != null){
            call.respond(HttpStatusCode.Conflict, "такой пользователь уже существует")
        } else {
            val token = UUID.randomUUID().toString()
            try {

                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        //email = registerReceiveRemote.email,
                        username = "",
                        wishlist = registerReceiveRemote.wishlist

                    )
                )
            }catch (e:ExposedSQLException){
                call.respond(HttpStatusCode.Conflict, "user already exists")
            }

            Tokens.insert(TokenDTO(
                rowId = UUID.randomUUID().toString(), login = registerReceiveRemote.login,
                token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }
    }
}