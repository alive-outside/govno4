package ru.newtry.features.delete

import kotlinx.serialization.Serializable

@Serializable
data class DeleteReceiveRemote(
    val wishnum: String,
    val login: String
)

@Serializable
data class DeleteResponseRemote(
    val wishlistUpd: String
)