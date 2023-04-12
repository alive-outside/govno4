package ru.newtry.features.login

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.newtry.database.tokens.TokenDTO
import ru.newtry.database.tokens.Tokens
import ru.newtry.database.users.Users
import java.util.*
import io.ktor.http.*


class LoginController(private val call:ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginReceiveRemote>()

        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "Такого пользователя нет")
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()

                Tokens.insert(
                    TokenDTO(
                    rowId = UUID.randomUUID().toString(), login = receive.login,
                    token = token)
                )
                //
                print("-----------${userDTO.login}")
                //
                call.respond(LoginResponseRemote(token = token))
            }
             else {
                call.respond(HttpStatusCode.BadRequest, "неправильный пароль")
            }
        }
    }
}