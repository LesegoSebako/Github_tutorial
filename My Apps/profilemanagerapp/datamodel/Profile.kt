package com.example.profilemanagerapp.datamodel

data class Profile(
    val name: String = "",
    val bio: String = "",
    val socialLinks: List<String> = emptyList(),
    val profileImageUrl: String? = null
)