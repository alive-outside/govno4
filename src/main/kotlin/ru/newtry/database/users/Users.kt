package ru.newtry.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import ru.newtry.database.users.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.lang.Exception.*

object Users: Table("projusers") {
    private val login = Users.varchar("login", 50)
    private val password = Users.varchar("password", 50)
    private val wishlist = Users.varchar("wishlist", 500)

    fun insert(UserDTO:UserDTO){
            transaction {
                Users.insert {
                    it[login] = UserDTO.login
                    it[password] = UserDTO.password
                    it[wishlist] = UserDTO.wishlist
                }
            }



    }
    fun fetchUser(login:String):UserDTO?{
        return try{
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    wishlist = userModel[wishlist]
                )
            }
        } catch (e:Exception){
            null
        }
    }
    fun addWish(wish:String, login: String):Int{
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                Users.update ({ Users.login eq login }) {
                    it[wishlist] = userModel[Users.wishlist] + "\n" + wish
                }
            }
        return 0;


    }


}