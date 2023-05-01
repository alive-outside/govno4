package ru.newtry.features.delete

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.newtry.database.tokens.TokenDTO
import ru.newtry.database.tokens.Tokens
import ru.newtry.database.users.Users
import java.util.*
import io.ktor.http.*
import ru.newtry.features.addition.AdditionReceiveRemote
import ru.newtry.features.login.LoginResponseRemote

class DeleteController (private val call:ApplicationCall){
    suspend fun deleteWish(){
        var receive = call.receive<DeleteReceiveRemote>()
        var wl = Users.deleteWish(login = receive.login, wishnum = receive.wishnum)
        call.respond(DeleteResponseRemote(wishlistUpd = wl))
    }


}