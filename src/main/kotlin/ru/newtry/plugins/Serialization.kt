package ru.newtry.plugins
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*

fun Application.configureSerialization(){
    install(ContentNegotiation){
        json()
    }

}