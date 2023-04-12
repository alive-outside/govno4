package ru.newtry.cache

import ru.newtry.features.register.RegisterReceiveRemote
import java.util.*

data class TokenCache(
    val login:String,
    val token: String
)


object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()

}