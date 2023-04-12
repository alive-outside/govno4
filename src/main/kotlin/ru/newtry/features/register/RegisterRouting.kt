package ru.newtry.features.register

import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import java.util.*
import io.ktor.http.*
import ru.newtry.utils.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
            val registerController = RegisterController(call)
            println(registerController)
            println("123123123123123123")
            registerController.registerNewUser()
        }
    }
}

