package ru.newtry.database.tokens

import ch.qos.logback.core.subst.Token
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.newtry.database.users.UserDTO
import ru.newtry.database.users.Users


object Tokens: Table("tkns") {
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 50)
    private val token = Tokens.varchar("token", 75)

    //CRUD - create / read / update / delete
    fun insert(tokenDTO: TokenDTO){
        transaction {
            Tokens.insert {
                it[id] = tokenDTO.rowId
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token

            }
        }
    }
    /*

    */
}