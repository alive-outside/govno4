package ru.newtry.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
        val login:String,
        val wishlist:String,
        val password: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)