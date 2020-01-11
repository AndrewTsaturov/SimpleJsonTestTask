package com.andrewtsaturov.simplejsontesttask.data.api.response.photo

import com.google.gson.annotations.SerializedName

class PhotoResponse(
    @SerializedName("postId") val postId: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)

//{
//    "albumId": 1,
//    "id": 1,
//    "title": "accusamus beatae ad facilis cum similique qui sunt",
//    "url": "https://via.placeholder.com/600/92c952",
//    "thumbnailUrl": "https://via.placeholder.com/150/92c952"
//}