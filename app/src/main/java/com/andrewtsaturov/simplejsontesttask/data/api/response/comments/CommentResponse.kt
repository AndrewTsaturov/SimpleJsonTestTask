package com.andrewtsaturov.simplejsontesttask.data.api.response.comments

import com.google.gson.annotations.SerializedName

class CommentResponse(
    @SerializedName("postId") val postId: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: Long,
    @SerializedName("body") val body: Long
)

//{
//    "postId": 1,
//    "id": 1,
//    "name": "id labore ex et quam laborum",
//    "email": "Eliseo@gardner.biz",
//    "body": "laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"
//}