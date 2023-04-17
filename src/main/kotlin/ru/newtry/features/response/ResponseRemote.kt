package ru.newtry.features.response

import kotlinx.serialization.Serializable


@Serializable
data class ResponseRemote(
    val login: String
)

@Serializable
data class ResponseRemoteResponse(
    val wishlist: String
)