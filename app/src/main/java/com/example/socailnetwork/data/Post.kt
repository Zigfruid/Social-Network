package com.example.socailnetwork.data

data class Post(
    var username: String =" ",
    var like: Int = 0,
    var dislike: Int = 0,
    var theme: String = "",
    var text: String = "",
    var userId: String =" "
    )
