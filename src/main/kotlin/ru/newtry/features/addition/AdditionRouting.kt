package ru.newtry.features.addition
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import ru.newtry.features.login.LoginController

fun Application.configureAdditionRouting(){
    routing {
        post("/addition") {
            val additionController = AdditionController(call)
            additionController.addWish()

        }
    }
}