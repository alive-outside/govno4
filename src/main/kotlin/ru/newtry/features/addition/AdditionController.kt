package ru.newtry.features.addition

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.newtry.database.tokens.TokenDTO
import ru.newtry.database.tokens.Tokens
import ru.newtry.database.users.Users
import java.util.*
import io.ktor.http.*
import ru.newtry.features.delete.DeleteResponseRemote

class AdditionController (private val call:ApplicationCall){
    suspend fun addWish(){
        var receive = call.receive<AdditionReceiveRemote>()
        //Users.addWish(login = receive.login, wish = receive.wish)
        var wl = Users.addWish(login = receive.login, wish = receive.wish)
        call.respond(AdditionResponseRemote(wishlistUpd = wl))
    }


}