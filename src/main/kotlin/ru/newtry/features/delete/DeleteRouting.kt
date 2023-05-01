package ru.newtry.features.delete

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import ru.newtry.features.addition.AdditionController
import ru.newtry.features.login.LoginController

fun Application.configureDeleteRouting(){
    routing {
        post("/delete") {
            val deleteController = DeleteController(call)
            deleteController.deleteWish()

        }
    }
}