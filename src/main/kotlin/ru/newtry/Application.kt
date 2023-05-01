package ru.newtry

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import io.ktor.server.application.*
import ru.newtry.features.addition.configureAdditionRouting
import ru.newtry.features.delete.configureDeleteRouting

import ru.newtry.plugins.*


import ru.newtry.features.login.configureLoginRouting
import ru.newtry.features.register.configureRegisterRouting
import ru.newtry.features.response.configureResponseRouting

fun main() {

    //Database.connect("jdbc:postgresql://localhost:5432/dbtest", driver = "org.postgresql.Driver",
    //    password = "hhhh", user = "postgres")

    Database.connect("jdbc:postgresql://containers-us-west-54.railway.app:6408/railway", driver = "org.postgresql.Driver"
        , password = "0hXEfCShG9A1PHEMrzxm", user = "postgres")

    embeddedServer(CIO, port = 6408, host = "0.0.0.0", module = Application::module)
        .start(wait = true)




}

fun Application.module() {

    configureRouting()
    configureRegisterRouting()
    configureLoginRouting()
    configureSerialization()
    configureResponseRouting()
    configureAdditionRouting()
    configureDeleteRouting()
}
