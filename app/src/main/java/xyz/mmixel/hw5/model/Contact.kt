package xyz.mmixel.hw5.model

import java.io.Serializable

data class Contact(
    val id: Int,
    var name: String,
    var surname: String,
    var phone: String,
    var phoneType: Int,
    var email: String
) : Serializable

