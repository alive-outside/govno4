package ru.newtry.features.response

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import java.util.*
import io.ktor.http.*



fun Application.configureResponseRouting(){
    routing {
        post ("/getData"){
            val rspController = ResponseController(call)
            rspController.fetchData()
        }
    }

}