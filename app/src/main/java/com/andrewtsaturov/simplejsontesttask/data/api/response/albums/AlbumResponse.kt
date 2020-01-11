package com.andrewtsaturov.simplejsontesttask.data.api.response.albums

import com.google.gson.annotations.SerializedName

class AlbumResponse(
    @SerializedName("userId") val userId: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String
)

