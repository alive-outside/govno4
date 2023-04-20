package ru.newtry.features.response

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.newtry.database.tokens.Tokens
import ru.newtry.database.users.Users
import ru.newtry.features.login.LoginReceiveRemote
import ru.newtry.features.register.RegisterReceiveRemote



class ResponseController(private val call :ApplicationCall ){

    suspend fun fetchData(){
        val receive = call.receive<ResponseRemote>()
        val userDTO = Users.fetchUser(receive.login)
        var wishlist = userDTO?.wishlist.toString()
        call.respond(ResponseRemoteResponse(wishlist = wishlist))





    }
}
