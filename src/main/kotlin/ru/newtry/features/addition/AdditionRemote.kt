package ru.newtry.features.addition

import kotlinx.serialization.Serializable

@Serializable
data class AdditionReceiveRemote(
    val wish:String,
    val login:String
)

@Serializable
data class AdditionResponseRemote(
    val wishlistUpd: String
)